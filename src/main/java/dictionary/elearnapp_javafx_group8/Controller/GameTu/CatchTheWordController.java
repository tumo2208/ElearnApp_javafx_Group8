package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CatchTheWordController implements Initializable {
    public Label instruction;
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

        instruction.setText("There are total 5 levels in this game. " +
                "Players will have to guess the word the picture describes. " +
                "There are 4 suggested questions equivalent to the 4 corners of the photo. " +
                "Every time a player answers a question correctly, a corner of the photo will be displayed. " +
                "The questions are directly related to the obstacle. " +
                "Players can press the bell to predict obstacles. \n" +
                "Good luck!");
        instruction.setWrapText(true);
        instruction.setAlignment(Pos.TOP_CENTER);
        instruction.setVisible(true);

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