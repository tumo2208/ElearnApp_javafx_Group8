package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;

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
            ringTheBellSound = new Media(getClass().getResource("/Sound/GameTu/ringTheBell.wav").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        question.setWrapText(true);
        row1.setImage(row[ChooseLevelController.currentLevel * 4]);
        row2.setImage(row[ChooseLevelController.currentLevel * 4 + 1]);
        row3.setImage(row[ChooseLevelController.currentLevel * 4 + 2]);
        row4.setImage(row[ChooseLevelController.currentLevel * 4 + 3]);
        answerField.setEditable(false);
        submitButton.setDisable(true);
        numLetterLabel.setText("This obstacle has "
                + Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getAnswer().length()
                + " letters");
        numLetterLabel.setStyle("-fx-font-size: 18px");
        numLetterLabel.setTextAlignment(TextAlignment.CENTER);
        numLetterLabel.setAlignment(Pos.CENTER);

        answerField.setOnKeyTyped(keyEvent -> submitButton.setDisable(answerField.getText().isEmpty()));

        angle1.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer1) {
                currentQuestion = 0;
                answerField.setEditable(true);
                submitButton.setDisable(false);
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer1 = true;
            }
        });

        angle2.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer2) {
                currentQuestion = 1;
                answerField.setEditable(true);
                submitButton.setDisable(false);
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer2 = true;
            }
        });

        angle3.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer3) {
                currentQuestion = 2;
                answerField.setEditable(true);
                submitButton.setDisable(false);
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer3 = true;
            }
        });

        angle4.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer4) {
                currentQuestion = 3;
                answerField.setEditable(true);
                submitButton.setDisable(false);
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
            question.setText("");
            answerField.clear();
            answerField.setEditable(false);
            submitButton.setDisable(true);
        });

        bellButton.setOnMouseClicked(mouseEvent -> onAnswer());

        bellButton.setOnMouseEntered(mouseEvent -> {
            ringBell = new MediaPlayer(ringTheBellSound);
            ringBell.play();
        });

        bellButton.setOnMouseExited(mouseEvent -> ringBell.pause());
    }

    private void onAnswer() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("AnswerTu");
    }

    private void wrongAnswer() {
        noti = new Dialog<>();
        Label lb = new Label("Oops, the answer was wrong");
        VBox vbox = new VBox(lb);
        noti.getDialogPane().setContent(vbox);
        noti.getDialogPane().getButtonTypes().add(ButtonType.OK);
        noti.initStyle(StageStyle.UNDECORATED);
        vbox.setStyle("-fx-background-color: #F6B6B6");
        lb.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: #ff0000;" +
                "-fx-font-size: 15px");
        Optional<ButtonType> option = noti.showAndWait();
        if (option.get() == ButtonType.OK) {
            noti.close();
        }
    }

    private void trueAnswer() {
        noti = new Dialog<>();
        Label lb = new Label("Congratulation, Your answer is true!\nThe angle "
                + (currentQuestion + 1) + " will be flipped");
        VBox vbox = new VBox(lb);
        noti.getDialogPane().setContent(vbox);
        noti.getDialogPane().getButtonTypes().add(ButtonType.OK);
        noti.initStyle(StageStyle.UNDECORATED);
        vbox.setStyle("-fx-background-color: #CAF7D8");
        lb.setStyle("-fx-font-weight: bold;" +
                "-fx-text-fill: #098000;" +
                "-fx-font-size: 15px");
        Optional<ButtonType> option = noti.showAndWait();
        if (option.get() == ButtonType.OK) {
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
    private Dialog<ButtonType> noti;
    private Media ringTheBellSound;
    private MediaPlayer ringBell;
}