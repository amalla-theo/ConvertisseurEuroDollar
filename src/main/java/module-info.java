module fr.btsciel.td_convertisseur_euro_dollar_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fr.btsciel.td_convertisseur_euro_dollar_javafx to javafx.fxml;
    exports fr.btsciel.td_convertisseur_euro_dollar_javafx;
}