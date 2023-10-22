module dictionary.elearnapp_javafx_group8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens dictionary.elearnapp_javafx_group8 to javafx.fxml;
    exports dictionary.elearnapp_javafx_group8;
}