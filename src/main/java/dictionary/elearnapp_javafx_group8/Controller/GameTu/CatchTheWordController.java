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
    public ImageView helpButton;
    public ImageView playButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            playIce = new Image(getClass().getResource("/Images/GameTu/Play_Button.png").toString());
            helpIce = new Image(getClass().getResource("/Images/GameTu/Help_Button.png").toString());
            exitIce = new Image(getClass().getResource("/Images/GameTu/Exit_Button.png").toString());
            playFire = new Image(getClass().getResource("/Images/GameTu/Play_Button_Click.png").toString());
            helpFire = new Image(getClass().getResource("/Images/GameTu/Help_Button_Click.png").toString());
            exitFire = new Image(getClass().getResource("/Images/GameTu/Exit_Button_Click.png").toString());
            Image bg = new Image(getClass().getResource("/Images/GameTu/background.png").toString());
            BackgroundImage bgImage = new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
            background = new Background(bgImage);
            catchTheWordBG.setBackground(background);
        } catch (Exception e) {
            e.printStackTrace();
        }

        playButton.setOnMouseEntered(mouseEvent -> playButton.setImage(playFire));

        playButton.setOnMouseExited(mouseEvent -> playButton.setImage(playIce));

        helpButton.setOnMouseEntered(mouseEvent -> helpButton.setImage(helpFire));

        helpButton.setOnMouseExited(mouseEvent -> helpButton.setImage(helpIce));

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

    public static Background background;
    private final BackgroundSize backgroundSize = new BackgroundSize(960, 580, true, true, true, true);
    private Image playIce;
    private Image helpIce;
    private Image exitIce;
    private Image playFire;
    private Image helpFire;
    private Image exitFire;
}
