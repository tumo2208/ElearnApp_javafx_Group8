package dictionary.elearnapp_javafx_group8.Controller;


import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;

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
            numberLabel.setText(indexOfWordSave + 1 + "/" + Model.getInstance().getSaveList().size());
            isFrontCard = true;
            flashCard.setStyle("\n" +
                    "    -fx-background-color: #d0ffc5;\n" +
                    "    -fx-alignment: center;\n" +
                    "    -fx-background-radius: 20;\n" +
                    "    -fx-border-radius: 20;\n" +
                    "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
        });

        rightButton.setOnMouseClicked(mouseEvent -> {
            if (++indexOfWordSave >= Model.getInstance().getSaveList().size())
                indexOfWordSave -= Model.getInstance().getSaveList().size();
            cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
            numberLabel.setText(indexOfWordSave + 1 + "/" + Model.getInstance().getSaveList().size());
            isFrontCard = true;
            flashCard.setStyle("\n" +
                    "    -fx-background-color: #d0ffc5;\n" +
                    "    -fx-alignment: center;\n" +
                    "    -fx-background-radius: 20;\n" +
                    "    -fx-border-radius: 20;\n" +
                    "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
        });
    }

    private void rotate() {
        RotateTransition cardRotate1 = new RotateTransition(Duration.millis(300), flashCard);
        cardRotate1.setAxis(Rotate.Y_AXIS);
        cardRotate1.setFromAngle(0);
        cardRotate1.setToAngle(90);
        cardRotate1.setInterpolator(Interpolator.EASE_OUT);
        cardRotate1.play();
        isFrontCard = !isFrontCard;
        cardRotate1.setOnFinished(actionEvent -> {
            if (isFrontCard) {
                cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
            } else {
                cardLabel.setText(trimMeaning());
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #dacfff;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(159,204,136,0.53), 15, 0, 15, 15);");
            }
            RotateTransition cardRotate2 = new RotateTransition(Duration.millis(300), flashCard);
            cardRotate2.setAxis(Rotate.Y_AXIS);
            cardRotate2.setFromAngle(270);
            cardRotate2.setToAngle(360);
            cardRotate2.setInterpolator(Interpolator.EASE_IN);
            cardRotate2.play();
        });

    }

    private void resetAllSave() {
        indexOfWordSave = 0;
        isFrontCard = true;
        numberLabel.setText(indexOfWordSave + 1 + "/" + Model.getInstance().getSaveList().size());
        cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
    }

    private String trimMeaning() {
        String res = "";
        String tmp = Model.getInstance().getSaveList().get(indexOfWordSave).getWordExplain();
        Stream<String> tmpLines = tmp.lines();
        ArrayList<String> lines = new ArrayList<>();
        tmpLines.forEach(lines::add);
        for (String line : lines) {
            res += (line + "\n");
            for (int i = 0; i < line.length(); ++i) {
                if ((line.charAt(0) == '-' && line.charAt(i) == ')' && i + 2 <line.length()) ||
                        (line.charAt(0) == '-' && line.charAt(i) != '(')) {
                    return res;
                }
            }
        }
        return res;
    }

    private boolean isFrontCard = true;
    private int indexOfWordSave = 0;
}