package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppUIController implements Initializable {

    public BorderPane menu_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().selectedMenuProperty().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "AddWord" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getAddWordView());
                case "Search" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getSearchView());
                case "GoogleTranslate" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getAPIView());
                case "Game" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getGameView());
            }
        });
    }
}
