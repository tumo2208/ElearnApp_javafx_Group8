package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CatchTheWordController implements Initializable {
    public AnchorPane catchTheWordBG;
    public ImageView exitButton;
    public ImageView playButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            playIce = new Image(getClass().getResource("/Images/GameTu/Play_Button.png").toString());
            exitIce = new Image(getClass().getResource("/Images/GameTu/Exit_Button.png").toString());
            playFire = new Image(getClass().getResource("/Images/GameTu/Play_Button_Click.png").toString());
            exitFire = new Image(getClass().getResource("/Images/GameTu/Exit_Button_Click.png").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        playButton.setOnMouseEntered(mouseEvent -> playButton.setImage(playFire));

        playButton.setOnMouseExited(mouseEvent -> playButton.setImage(playIce));

        exitButton.setOnMouseEntered(mouseEvent -> exitButton.setImage(exitFire));

        exitButton.setOnMouseExited(mouseEvent -> exitButton.setImage(exitIce));

        exitButton.setOnMouseClicked(mouseEvent -> onGame());
        playButton.setOnMouseClicked(mouseEvent -> onPlay());
    }

    private void onGame() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("Game");
    }

    private void onPlay() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("PlayGameTu");
    }

    private Image playIce;
    private Image exitIce;
    private Image playFire;
    private Image exitFire;
}
