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
                case "Save" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getSaveView());
                case "MemoriesMenu" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getMemoriesMenuView());
                case "MemoriesPlay" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getMemoryPlayView());
                case "CatchTheWord" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getCatchTheWordView());
                case "PlayGameTu" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getPlayGameTuView());
                case "QuestionTu" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getQuestionTuView());
                case "AnswerTu" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getAnswerTuView());
                case "LoseTu" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getLoseTuView());
                case "WinTu" -> menu_parent.setCenter(Model.getInstance().getViewFactory().getWinTuView());
            }
        });
    }
}
