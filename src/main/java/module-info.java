module com.example.pnvstudentmanage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.furnituresmanage to javafx.fxml;
    exports com.example.furnituresmanage;
}