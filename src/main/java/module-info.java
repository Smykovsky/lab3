module pl.smyk.lab3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens pl.smyk.lab3 to javafx.fxml;
    exports pl.smyk.lab3;
}