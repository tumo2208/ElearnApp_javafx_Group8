module dictionary.elearnapp_javafx_group8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires jdk.management;

    opens dictionary.elearnapp_javafx_group8 to javafx.fxml;
    exports dictionary.elearnapp_javafx_group8;
    exports dictionary.elearnapp_javafx_group8.Controller;

}