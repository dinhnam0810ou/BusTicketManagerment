module com.ndn.busticketmanagerment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.ndn.busticketmanagerment to javafx.fxml;
    exports com.ndn.busticketmanagerment;
}
