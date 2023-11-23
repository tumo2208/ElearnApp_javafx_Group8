package dictionary.elearnapp_javafx_group8.Controller;


import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.animation.*;
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
    public VBox flashCard1;
    public VBox flashCard2;
    public Label cardLabel;
    public Label cardLabel1;
    public Label cardLabel2;
    public Button leftButton;
    public Button rightButton;
    public Label numberLabel;
    private boolean isFrontCard = true;
    private boolean isFrontCard1 = true;
    private boolean isFrontCard2 = true;
    private int indexOfWordSave = 0;
    private boolean fistTime = true;
    private int isShowing=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetAllSave();
        flashCard.setOnMouseClicked(mouseEvent -> rotate());
        flashCard1.setOnMouseClicked(mouseEvent -> rotate1());
        flashCard2.setOnMouseClicked(mouseEvent -> rotate2());

        leftButton.setOnMouseClicked(mouseEvent -> {
            if (!isFrontCard) {
                cardLabel.setStyle("-fx-text-fill: #fff");
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #dacfff;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(159,204,136,0.53), 15, 0, 15, 15);");
                cardLabel.setText(trimMeaning());
            }
            move(true);
        });

        rightButton.setOnMouseClicked(mouseEvent -> {
            if (!isFrontCard) {
                cardLabel.setStyle("-fx-text-fill: #fff");
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #dacfff;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(159,204,136,0.53), 15, 0, 15, 15);");
                cardLabel.setText(trimMeaning());
            }
            move(false);
        });
    }

    /**
     * Di chuyển flash card theo chỉ định mũi tên.
     * @param isLeft nếu bấm mũi tên trái thì isLeft=true
     */
    private void move(boolean isLeft) {

        Timeline timeline = new Timeline();


        if (isLeft) {
            flashCard2.setOpacity(0);
            flashCard1.setOpacity(0);
            flashCard.setOpacity(1);
            if (isFrontCard1 && !fistTime) {
                cardLabel.setStyle("-fx-text-fill: #000");
                cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");

            } else if (!isFrontCard1 && !fistTime) {
                cardLabel.setStyle("-fx-text-fill: #fff");
                cardLabel.setText(trimMeaning());
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #dacfff;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(159,204,136,0.53), 15, 0, 15, 15);");
                flashCard1.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
                isFrontCard1 = !isFrontCard1;
            }
            if (fistTime) {
                fistTime = false;
            }

            if (--indexOfWordSave < 0) {
                indexOfWordSave += Model.getInstance().getSaveList().size();
            }

            cardLabel1.setStyle("-fx-text-fill: #000");
            cardLabel1.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
            numberLabel.setText(indexOfWordSave + 1 + "/" + Model.getInstance().getSaveList().size());

            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), new KeyValue(flashCard.translateXProperty(), 0));
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard.translateXProperty(), 600));
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0), new KeyValue(flashCard1.translateXProperty(), 0));
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard1.translateXProperty(), 628));
            KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(0), new KeyValue(flashCard2.translateXProperty(), 0));
            KeyFrame keyFrame6 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard2.translateXProperty(), 600));
            KeyFrame keyFrame7 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard1.opacityProperty(), 1));
            KeyFrame keyFrame8 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard.opacityProperty(), 0));
            timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5, keyFrame6, keyFrame7, keyFrame8);


        } else {
            flashCard1.setOpacity(0);
            flashCard2.setOpacity(0);
            flashCard.setOpacity(1);
            if (isFrontCard2 && !fistTime) {
                cardLabel.setStyle("-fx-text-fill: #000");
                cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");

            } else if (!isFrontCard2 && !fistTime) {
                cardLabel.setStyle("-fx-text-fill: #fff");
                cardLabel.setText(trimMeaning());
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #dacfff;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(159,204,136,0.53), 15, 0, 15, 15);");
                flashCard2.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
                isFrontCard2 = !isFrontCard2;
            }
            if (fistTime) {
                fistTime = false;
            }

            if (++indexOfWordSave >= Model.getInstance().getSaveList().size()) {
                indexOfWordSave -= Model.getInstance().getSaveList().size();
            }

            cardLabel2.setStyle("-fx-text-fill: #000");
            cardLabel2.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
            numberLabel.setText(indexOfWordSave + 1 + "/" + Model.getInstance().getSaveList().size());

            KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), new KeyValue(flashCard.translateXProperty(), 0));
            KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard.translateXProperty(), -600));
            KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(0), new KeyValue(flashCard2.translateXProperty(), 0));
            KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard2.translateXProperty(), -628));
            KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(0), new KeyValue(flashCard1.translateXProperty(), 0));
            KeyFrame keyFrame6 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard1.translateXProperty(), -600));
            KeyFrame keyFrame7 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard2.opacityProperty(), 1));
            KeyFrame keyFrame8 = new KeyFrame(Duration.seconds(0.7), new KeyValue(flashCard.opacityProperty(), 0));
            timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5, keyFrame6, keyFrame7, keyFrame8);

        }

        timeline.play();
    }

    /**
     * animation lật card số 1.
     */
    private void rotate1() {
        RotateTransition cardRotate1 = new RotateTransition(Duration.millis(300), flashCard1);
        cardRotate1.setAxis(Rotate.Y_AXIS);
        cardRotate1.setFromAngle(0);
        cardRotate1.setToAngle(90);
        cardRotate1.setInterpolator(Interpolator.EASE_OUT);
        cardRotate1.play();
        isFrontCard1 = !isFrontCard1;
        cardRotate1.setOnFinished(actionEvent -> {
            if (isFrontCard1) {
                cardLabel1.setStyle("-fx-text-fill: #000");
                cardLabel1.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
                flashCard1.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
            } else {
                cardLabel1.setStyle("-fx-text-fill: #fff");
                cardLabel1.setText(trimMeaning());
                flashCard1.setStyle("\n" +
                        "    -fx-background-color: #dacfff;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(159,204,136,0.53), 15, 0, 15, 15);");
            }
            RotateTransition cardRotate2 = new RotateTransition(Duration.millis(300), flashCard1);
            cardRotate2.setAxis(Rotate.Y_AXIS);
            cardRotate2.setFromAngle(270);
            cardRotate2.setToAngle(360);
            cardRotate2.setInterpolator(Interpolator.EASE_IN);
            cardRotate2.play();
        });

    }

    /**
     * animation lật card số 2.
     */
    private void rotate2() {
        RotateTransition cardRotate1 = new RotateTransition(Duration.millis(300), flashCard2);
        cardRotate1.setAxis(Rotate.Y_AXIS);
        cardRotate1.setFromAngle(0);
        cardRotate1.setToAngle(90);
        cardRotate1.setInterpolator(Interpolator.EASE_OUT);
        cardRotate1.play();
        isFrontCard2 = !isFrontCard2;
        cardRotate1.setOnFinished(actionEvent -> {
            if (isFrontCard2) {
                cardLabel2.setStyle("-fx-text-fill: #000");
                cardLabel2.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
                flashCard2.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
            } else {
                cardLabel2.setStyle("-fx-text-fill: #fff");
                cardLabel2.setText(trimMeaning());
                flashCard2.setStyle("\n" +
                        "    -fx-background-color: #dacfff;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, rgba(159,204,136,0.53), 15, 0, 15, 15);");
            }
            RotateTransition cardRotate2 = new RotateTransition(Duration.millis(300), flashCard2);
            cardRotate2.setAxis(Rotate.Y_AXIS);
            cardRotate2.setFromAngle(270);
            cardRotate2.setToAngle(360);
            cardRotate2.setInterpolator(Interpolator.EASE_IN);
            cardRotate2.play();
        });

    }

    /**
     * animation lật card trung tâm.
     */
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
                cardLabel.setStyle("-fx-text-fill: #000");
                cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
                flashCard.setStyle("\n" +
                        "    -fx-background-color: #d0ffc5;\n" +
                        "    -fx-alignment: center;\n" +
                        "    -fx-background-radius: 20;\n" +
                        "    -fx-border-radius: 20;\n" +
                        "    -fx-effect: dropshadow(three-pass-box, #c8c8, 15, 0, 15, 15);");
            } else {
                cardLabel.setStyle("-fx-text-fill: #fff");
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

    /**
     * reset các thông số để bắt đầu lại.
     */
    private void resetAllSave() {
        indexOfWordSave = 0;
        isFrontCard = true;
        numberLabel.setText(indexOfWordSave + 1 + "/" + Model.getInstance().getSaveList().size());
        cardLabel.setText(Model.getInstance().getSaveList().get(indexOfWordSave).getWordTarget());
        cardLabel.setStyle("-fx-text-fill: #000");
    }

    /**
     * lấy một đoạn nghĩa nhỏ cuả từ cho vừa với kích thước card.
     * @return string
     */
    private String trimMeaning() {
        String res = "";
        String tmp = Model.getInstance().getSaveList().get(indexOfWordSave).getWordExplain();
        Stream<String> tmpLines = tmp.lines();
        ArrayList<String> lines = new ArrayList<>();
        tmpLines.forEach(lines::add);
        for (String line : lines) {
            res += (line + "\n");
            for (int i = 0; i < line.length(); ++i) {
                if ((line.charAt(0) == '-' && line.charAt(i) == ')' && i + 2 < line.length()) ||
                        (line.charAt(0) == '-' && line.charAt(i) != '(')) {
                    return res;
                }
            }
        }
        return res;
    }


}