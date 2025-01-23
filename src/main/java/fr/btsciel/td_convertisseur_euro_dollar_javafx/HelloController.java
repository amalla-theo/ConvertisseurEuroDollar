package fr.btsciel.td_convertisseur_euro_dollar_javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TextField textField_Euro;
    public TextField textField_Dollar;
    public Button button_Convertisseur;
    public double taux = 1.02;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField_Dollar.setEditable(false);
        textField_Dollar.setDisable(true);
        textField_Euro.setEditable(true);
        textField_Euro.setDisable(false);
        DecimalFormat df = new DecimalFormat("0.00");
        button_Convertisseur.setOnAction(event -> {
            String euro = textField_Euro.getText().replace(",",".");

            if (euro.length()>=1){
                textField_Dollar.setText(df.format(Double.parseDouble(euro)* taux));
            }
        });
    }


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}