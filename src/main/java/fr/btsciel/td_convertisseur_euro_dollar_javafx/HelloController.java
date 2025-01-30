package fr.btsciel.td_convertisseur_euro_dollar_javafx;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField textField_Euro;
    public TextField textField_Dollar;
    public Button button_Convertisseur;
    public double taux = 1.04;
    public RadioButton rbEuroDollar;
    public RadioButton rbDollarEuro;
    public RotateTransition rotation;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField_Dollar.setEditable(false);
        textField_Dollar.setDisable(true);
        textField_Euro.setEditable(true);
        textField_Euro.setDisable(false);
        rotation = new RotateTransition(Duration.seconds(0.25), button_Convertisseur);

        rbEuroDollar.setOnAction(event ->rbEuroDollar());
        rbDollarEuro.setOnAction(event -> rbDollarEuro());
        button_Convertisseur.setOnAction(event -> {Convertir();});




    }

    private void Convertir() {
        DecimalFormat df = new DecimalFormat("0.00");

        try {
            if (rbEuroDollar.isSelected()) {
                String euro = textField_Euro.getText().replace(",",".");
                try {
                    if (euro.length()>=1){
                        textField_Dollar.setText(df.format(Double.parseDouble(euro)* taux));
                    }
                }catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alerteFormat();
                }

            }else if (rbDollarEuro.isSelected()) {

                String dollar = textField_Dollar.getText().replace(",",".");

                try {
                    if (dollar.length()>=1){
                        textField_Euro.setText(df.format(Double.parseDouble(dollar)/ taux));

                    }
                }catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alerteFormat();
                }


            }
        }catch (NumberFormatException e){}
    }

    private void rbEuroDollar() {
        rotation.setByAngle(-180);
        rotation.play();
        textField_Dollar.setEditable(false);
        textField_Dollar.setDisable(true);
        textField_Euro.setEditable(true);
        textField_Euro.setDisable(false);
        textField_Dollar.clear();
    }

    private void rbDollarEuro() {
        rotation.setByAngle(180);
        rotation.play();
        textField_Dollar.setEditable(true);
        textField_Dollar.setDisable(false);
        textField_Euro.setEditable(false);
        textField_Euro.setDisable(true);
        textField_Euro.clear();
    }

    public void alerteFormat(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir uniquement des chiffres");
        alert.showAndWait();

    }


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}