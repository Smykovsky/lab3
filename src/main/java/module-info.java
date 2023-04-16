module pl.smyk.lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires mysql.connector.java;
    requires java.persistence;
    requires java.naming;

    opens pl.smyk.lab3 to javafx.fxml;
    exports pl.smyk.lab3;
    exports pl.smyk.lab3.controller;
    exports pl.smyk.lab3.utils;
    exports pl.smyk.lab3.model;
    opens pl.smyk.lab3.controller to javafx.fxml;
    opens pl.smyk.lab3.utils to org.hibernate.orm.core;
}