module dictionary.elearnapp_javafx_group8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires jdk.management;
    requires javafx.web;
    requires javafx.media;

    opens dictionary.elearnapp_javafx_group8 to javafx.fxml;
    exports dictionary.elearnapp_javafx_group8;
    exports dictionary.elearnapp_javafx_group8.Controller;
    exports dictionary.elearnapp_javafx_group8.Controller.GameTu;
    exports dictionary.elearnapp_javafx_group8.Controller.GamePhuoc;

}