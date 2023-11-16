package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class WinController implements Initializable {
    public Button exitButton;
    public Button homeButton;
    public Button nextButton;
    public ImageView fullImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Media handClapSound = new Media(getClass().getResource("/Sound/GameTu/congratulations.mp3").toString());
            victory = new MediaPlayer(handClapSound);
            img[0] = new Image(getClass().getResource("/Images/GameTu/full/bangCuuChuong.png").toString());
            img[1] = new Image(getClass().getResource("/Images/GameTu/full/storm.png").toString());
            img[2] = new Image(getClass().getResource("/Images/GameTu/full/nhatThuc.png").toString());
            img[3] = new Image(getClass().getResource("/Images/GameTu/full/tiaUV.png").toString());
            img[4] = new Image(getClass().getResource("/Images/GameTu/full/TayBac.png").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        victory.play();
        fullImg.setImage(img[ChooseLevelController.currentLevel]);

        if (ChooseLevelController.currentLevel == 4) {
            nextButton.setDisable(true);
        }

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

    private MediaPlayer victory;
    private final Image[] img = new Image[5];
}
