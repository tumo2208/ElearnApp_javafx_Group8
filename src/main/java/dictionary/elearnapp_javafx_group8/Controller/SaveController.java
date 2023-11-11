package dictionary.elearnapp_javafx_group8.Controller;


import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.animation.RotateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SaveController implements Initializable {

    public VBox flashCard;
    public Label cardLabel;
    public Button leftButton;
    public Button rightButton;
    public Label numberLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetAllSave();

        flashCard.setOnMouseClicked(mouseEvent -> rotate());

        leftButton.setOnMouseClicked(mouseEvent -> {
            if (--indexOfWordSave < 0)
                indexOfWordSave += Model.getInstance().getSaveList().size();
            cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
            numberLabel.setText(Integer.toString(indexOfWordSave+1) + "/" + Model.getInstance().getSaveList().size());
            isFrontCard = true;
        });

        rightButton.setOnMouseClicked(mouseEvent -> {
            if (++indexOfWordSave >= Model.getInstance().getSaveList().size())
                indexOfWordSave -= Model.getInstance().getSaveList().size();
            cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
            numberLabel.setText(Integer.toString(indexOfWordSave+1) + "/" + Model.getInstance().getSaveList().size());
            isFrontCard = true;
        });
    }

    private void rotate() {
        cardLabel.setVisible(false);
        RotateTransition cardRotate = new RotateTransition(Duration.millis(1000), flashCard);
        cardRotate.setAxis(Rotate.Y_AXIS);
        if (isFrontCard) {
            cardRotate.setFromAngle(0);
            cardRotate.setToAngle(180);
        } else {
            cardRotate.setFromAngle(180);
            cardRotate.setToAngle(360);
        }
        cardRotate.play();
        RotateTransition labelRotate = new RotateTransition(Duration.millis(1000), cardLabel);
        labelRotate.setAxis(Rotate.Y_AXIS);
        if (isFrontCard) {
            labelRotate.setFromAngle(0);
            labelRotate.setToAngle(180);
        } else {
            labelRotate.setFromAngle(180);
            labelRotate.setToAngle(360);
        }
        labelRotate.play();
        labelRotate.setOnFinished(event -> {
            if (isFrontCard) {
                cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordExplain());
            } else {
                cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
            }
            cardLabel.setVisible(true);
            isFrontCard = !isFrontCard;
        });

    }

    private void resetAllSave() {
        indexOfWordSave = 0;
        isFrontCard = true;
        numberLabel.setText(Integer.toString(indexOfWordSave+1) + "/" + Model.getInstance().getSaveList().size());
        cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
    }

    private boolean isFrontCard = true;
    private int indexOfWordSave = 0;
}
