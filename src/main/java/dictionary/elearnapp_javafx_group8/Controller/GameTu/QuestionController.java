package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    public ImageView row1;
    public ImageView row2;
    public ImageView row3;
    public ImageView row4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            for (int i = 0; i < 20; ++i) {
                String s = Integer.toString(i % 4 + 1);
                String t;
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
                row[i] = new Image(getClass().getResource("/Images/GameTu/" + t + "/row" + s + ".png").toString());
                rowAnswer[i] = new Image(getClass().getResource("/Images/GameTu/" + t + "/row" + s + "_answer.png").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        row1.setImage(row[ChooseLevelController.currentLevel * 4]);
        row2.setImage(row[ChooseLevelController.currentLevel * 4 + 1]);
        row3.setImage(row[ChooseLevelController.currentLevel * 4 + 2]);
        row4.setImage(row[ChooseLevelController.currentLevel * 4 + 3]);
        submitButton.setDisable(true);
        numLetterLabel.setText("This obstacle has "
                + Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getAnswer().length()
                + " letters");

        answerField.setOnKeyTyped(keyEvent -> submitButton.setDisable(answerField.getText().isEmpty()));

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
                    case 0:
                        angle1.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        row1.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                    case 1:
                        angle2.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        row2.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                    case 2:
                        angle3.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        row3.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                    case 3:
                        angle4.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        row4.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                        break;
                }
            } else {
                wrongAnswer();
            }
        });

        bellButton.setOnMouseClicked(mouseEvent -> onAnswer());
    }

    private void onAnswer() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("AnswerTu");
    }

    private void wrongAnswer() {
        noti.setAlertType(Alert.AlertType.ERROR);
        noti.setHeaderText("Oops, the answer was wrong");
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
        noti.setHeaderText("Congratulation, Your answer is true!\nThe angle "
                + (currentQuestion + 1) + " will be flipped");
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
    private final Image[] imgList = new Image[20];
    private final Image[] row = new Image[20];
    private final Image[] rowAnswer = new Image[20];
    private final Alert noti = new Alert(Alert.AlertType.CONFIRMATION);
}
