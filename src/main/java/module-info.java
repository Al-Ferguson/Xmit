module com.bytezone.xmit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;


    opens com.bytezone.xmit to javafx.fxml;
    exports com.bytezone.xmit;
}