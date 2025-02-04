package fr.btsciel.td_convertisseur_euro_dollar_javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Button button_Convertisseur;
    public Label label_Init;
    public Label label_Final;
    public double taux_Euro_DollarUS = 1.04;
    public TextField tf_Init;
    public TextField tf_Final;
    public ComboBox comboSelection;
    private ArrayList <ModIHM> conversionDevise = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fabriquerDonnees();

        tf_Init.setEditable(false);
        tf_Final.setDisable(true);
        tf_Init.setEditable(true);
        tf_Final.setDisable(false);

        button_Convertisseur.setOnAction(event -> {Convertir();});

    }

    private void fabriquerDonnees(){
        conversionDevise.add(new ModIHM("Euro --> Dollar US", "Euro", "Dollar", taux_Euro_DollarUS));



        conversionDevise.forEach(element -> comboSelection.getItems().add(element.getPrompt()));
    }

    private void Convertir() {
        DecimalFormat df = new DecimalFormat("0.00");

        try {
            String euro = tf_Init.getText().replace(",",".");
            try {
                if (euro.length()>=1){
                    tf_Final.setText(df.format(Double.parseDouble(euro)* taux_Euro_DollarUS));
                }
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alerteFormat();
            }
        }catch (NumberFormatException e){}
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