package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class WinController implements Initializable {
    public Button exitButton;
    public Button homeButton;
    public Button nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitButton.setOnAction(event -> onGame());
        homeButton.setOnAction(event -> onPlay());
        nextButton.setOnAction(event -> onNext());
    }

    private void onGame() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("Game");
    }

    private void onPlay() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("PlayGameTu");
    }

    private void onNext() {
        ChooseLevelController.currentLevel++;
        Model.getInstance().getViewFactory().selectedMenuProperty().set("QuestionTu");
    }
}
