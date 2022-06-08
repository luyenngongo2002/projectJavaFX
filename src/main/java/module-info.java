module com.example.pnvstudentmanage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.pnvstudentmanage to javafx.fxml;
    exports com.example.pnvstudentmanage;
}