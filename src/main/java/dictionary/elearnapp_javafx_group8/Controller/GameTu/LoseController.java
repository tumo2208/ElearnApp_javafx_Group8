package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class LoseController implements Initializable {
    public Button exitButton;
    public Button homeButton;
    public Button againButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Media oopsSound = new Media(getClass().getResource("/Sound/GameTu/oops.wav").toString());
            defeat = new MediaPlayer(oopsSound);
        } catch (Exception e) {
            e.printStackTrace();
        }

        defeat.play();

        exitButton.setOnAction(event -> onGame());
        homeButton.setOnAction(event -> onPlay());
        againButton.setOnAction(event -> onAgain());
    }

    private void onGame() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("Game");
    }

    private void onPlay() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("PlayGameTu");
    }

    private void onAgain() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("QuestionTu");
    }

    private MediaPlayer defeat;
}