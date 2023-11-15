package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.animation.RotateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    public ImageView angle1;
    public ImageView angle4;
    public ImageView angle2;
    public ImageView angle3;
    public Label question;
    public TextField answerField;
    public Button submitButton;
    public ImageView bellButton;
    public Label numLetterLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            for (int i = 0; i < 20; ++i) {
                String s = Integer.toString(i % 4 + 1);
                String t = "";
                if (i < 4) {
                    t = "packBangCuuChuong";
                } else if (i < 8) {
                    t = "packBao";
                } else if (i < 12) {
                    t = "packNhatThuc";
                } else if (i < 16) {
                    t = "packTiaUV";
                } else {
                    t = "packTayBac";
                }
                imgList[i] = new Image(getClass().getResource("/Images/GameTu/" + t + "/" + s + ".png").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        submitButton.setDisable(true);
        numLetterLabel.setText("This obstacle has "
                + Integer.toString(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getAnswer().length())
                + " letters");

        answerField.setOnKeyTyped(keyEvent -> {
            if (answerField.getText().isEmpty()) {
                submitButton.setDisable(true);
            } else {
                submitButton.setDisable(false);
            }
        });

        angle1.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer1) {
                currentQuestion = 0;
                answerField.clear();
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer1 = true;
            }
        });

        angle2.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer2) {
                currentQuestion = 1;
                answerField.clear();
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer2 = true;
            }
        });

        angle3.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer3) {
                currentQuestion = 2;
                answerField.clear();
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer3 = true;
            }
        });

        angle4.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer4) {
                currentQuestion = 3;
                answerField.clear();
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer4 = true;
            }
        });

        submitButton.setOnAction(event -> {
            answer = answerField.getText().trim();
            answer = answer.replace(" ", "");
            answer = answer.toLowerCase();
            if (answer.equals(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getAnswer())) {
                trueAnswer();
                switch (currentQuestion) {
                    case 0: angle1.setImage(imgList[4*ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                    case 1: angle2.setImage(imgList[4*ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                    case 2: angle3.setImage(imgList[4*ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                    case 3: angle4.setImage(imgList[4*ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                }
            } else {
                wrongAnswer();
            }
        });

        /*bellButton.setOnMouseEntered(mouseEvent -> {
            for (int i = 0; i < 3; ++i) {
                if (i == 0) {
                    RotateTransition ringBell1 = new RotateTransition(Duration.millis(500), bellButton);
                    ringBell1.setAxis(Rotate.Z_AXIS);
                    ringBell1.setFromAngle(0);
                    ringBell1.setToAngle(50);
                    ringBell1.play();
                } else if (i == 1) {
                    RotateTransition ringBell2 = new RotateTransition(Duration.millis(500), bellButton);
                    ringBell2.setAxis(Rotate.Z_AXIS);
                    ringBell2.setFromAngle(50);
                    ringBell2.setToAngle(-50);
                    ringBell2.play();
                } else {
                    RotateTransition ringBell3 = new RotateTransition(Duration.millis(500), bellButton);
                    ringBell3.setAxis(Rotate.Z_AXIS);
                    ringBell3.setFromAngle(-50);
                    ringBell3.setToAngle(0);
                    ringBell3.play();
                }
            }
        });*/

        bellButton.setOnMouseClicked(mouseEvent -> onAnswer());
    }

    private void onAnswer() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("AnswerTu");
    }

    private void wrongAnswer() {
        noti.setAlertType(Alert.AlertType.ERROR);
        noti.setHeaderText("Your answer is wrong!\nThe angle "
                + Integer.toString(currentQuestion + 1) + " will not be flipped");
        noti.getButtonTypes().clear();
        noti.getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> option = noti.showAndWait();
        noti.show();
        if (option.get() == ButtonType.OK || option.get() == ButtonType.CLOSE) {
            noti.close();
        }
    }

    private void trueAnswer() {
        noti.setAlertType(Alert.AlertType.CONFIRMATION);
        noti.setHeaderText("Your answer is true!\nThe angle "
                + Integer.toString(currentQuestion + 1) + " will be flipped");
        noti.getButtonTypes().clear();
        noti.getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> option = noti.showAndWait();
        noti.show();
        if (option.get() == ButtonType.OK || option.get() == ButtonType.CLOSE) {
            noti.close();
        }
    }

    private String answer = "";
    private int currentQuestion = 0;
    private boolean isAnswer1 = false;
    private boolean isAnswer2 = false;
    private boolean isAnswer3 = false;
    private boolean isAnswer4 = false;
    private Image[] imgList = new Image[20];
    private Alert noti = new Alert(Alert.AlertType.CONFIRMATION);
}
