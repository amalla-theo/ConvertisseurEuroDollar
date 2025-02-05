package fr.btsciel.td_convertisseur_euro_dollar_javafx;

import javafx.event.Event;
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
    public double taux_Euro_Livre = 0.83;
    public double taux_Euro_Yen = 159.24;
    public double taux;
    public TextField tf_Init;
    public TextField tf_Final;
    public ComboBox comboSelection;
    private ArrayList <ModIHM> conversionDevise = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fabriquerDonnees();
        comboSelection.setOnAction(event -> comboSelection(event));

        tf_Final.setEditable(false);
        tf_Final.setDisable(true);
        tf_Init.setEditable(true);
        tf_Init.setDisable(false);

        button_Convertisseur.setOnAction(event -> {Convertir();});

    }

    private void comboSelection(Event event) {
        int index = ((ComboBox)event.getSource()).getSelectionModel().getSelectedIndex();
        conversionDevise.get(index);
        ModIHM monObjetIHM = conversionDevise.get(index);
        taux = conversionDevise.get(index).getTaux();
        System.out.println(taux);
        label_Init.setText(monObjetIHM.getSource());
        label_Final.setText(monObjetIHM.getCible());
    }

    private void fabriquerDonnees(){
        conversionDevise.add(new ModIHM("Euro --> Dollar US", "Euro", "Dollar", taux_Euro_DollarUS));
        conversionDevise.add(new ModIHM("Dollar US --> Euro", "Dollar", "Euro", (1/taux_Euro_DollarUS)));
        conversionDevise.add(new ModIHM("Euro --> Livres", "Euro", "Livres", taux_Euro_Livre));
        conversionDevise.add(new ModIHM("Livres --> Euro", "Livres", "Euro", (1/taux_Euro_Livre)));
        conversionDevise.add(new ModIHM("Euro --> Yen", "Euro", "Yen", taux_Euro_Yen));
        conversionDevise.add(new ModIHM("Yen --> Euro", "Yen", "Euro", (1/taux_Euro_Yen)));


        conversionDevise.forEach(element -> comboSelection.getItems().add(element.getPrompt()));
    }

    private void Convertir() {
        DecimalFormat df = new DecimalFormat("0.00");

                    try {
                        String source = tf_Init.getText().replace(",",".");
                        try {
                            if (source.length()>=1){
                                tf_Final.setText(df.format(Double.parseDouble(source)* taux));
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