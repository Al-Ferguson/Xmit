module com.bytezone.xmit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bytezone.xmit to javafx.fxml;
    exports com.bytezone.xmit;
}