package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
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
            for (int i = 0; i < 4; ++i) {
                imgFalse[i] = new Image(getClass().getResource("/Images/GameTu/packDefaultFalse/" + (i + 1) + ".png").toString());
            }
            trueIcon = new Image(getClass().getResource("/Images/GameTu/trueAnswer.png").toString());
            wrongIcon = new Image(getClass().getResource("/Images/GameTu/wrongAnswer.png").toString());
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

        answerField.setOnKeyTyped(keyEvent -> submitButton.setDisable(answerField.getText().isEmpty()));

        angle1.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer1 && question.getText().isEmpty()) {
                currentQuestion = 0;
                answerField.setEditable(true);
                submitButton.setDisable(false);
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer1 = true;
            }
        });

        angle2.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer2 && question.getText().isEmpty()) {
                currentQuestion = 1;
                answerField.setEditable(true);
                submitButton.setDisable(false);
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer2 = true;
            }
        });

        angle3.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer3 && question.getText().isEmpty()) {
                currentQuestion = 2;
                answerField.setEditable(true);
                submitButton.setDisable(false);
                question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());
                isAnswer3 = true;
            }
        });

        angle4.setOnMouseClicked(mouseEvent -> {
            if (!isAnswer4 && question.getText().isEmpty()) {
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
            } else {
                wrongAnswer();
            }
            question.setText("");
            answerField.clear();
            answerField.setEditable(false);
            submitButton.setDisable(true);
        });

        bellButton.setOnMouseClicked(mouseEvent -> onAnswer());
    }

    private void onAnswer() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("AnswerTu");
    }

    private void wrongAnswer() {
        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 450, 350);

        ImageView wrongImg = new ImageView(wrongIcon);
        wrongImg.setFitWidth(100);
        wrongImg.setFitHeight(100);
        wrongImg.setLayoutX(175);
        wrongImg.setLayoutY(15);

        Label lb1 = new Label("Oops!");
        Label lb2 = new Label("Your answer is wrong\n      Try your best");
        lb1.setPrefWidth(450);
        lb1.setPrefHeight(30);
        lb1.setLayoutX(0);
        lb1.setLayoutY(130);
        lb2.setPrefWidth(450);
        lb2.setPrefHeight(60);
        lb2.setLayoutX(0);
        lb2.setLayoutY(200);
        lb1.setStyle("-fx-alignment: center;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 3.5em;" +
                "-fx-font-weight: bold");
        lb2.setStyle("-fx-alignment: center;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 1.5em;" +
                "-fx-wrap-text: true");

        Button okBtn = new Button("OK");
        okBtn.setPrefWidth(100);
        okBtn.setPrefHeight(30);
        okBtn.setLayoutX(175);
        okBtn.setLayoutY(290);
        okBtn.setCursor(Cursor.HAND);
        okBtn.setStyle("-fx-text-fill: #FFFFFF;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: #87CEEB");

        okBtn.setOnAction(event -> {
            switch (currentQuestion) {
                case 0:
                    angle1.setImage(imgFalse[currentQuestion]);
                    isTrue1 = false;
                    break;
                case 1:
                    angle2.setImage(imgFalse[currentQuestion]);
                    isTrue2 = false;
                    break;
                case 2:
                    angle3.setImage(imgFalse[currentQuestion]);
                    isTrue3 = false;
                    break;
                case 3:
                    angle4.setImage(imgFalse[currentQuestion]);
                    isTrue4 = false;
                    break;
            }
            if (isAnswer1 && isAnswer2 && isAnswer3 && isAnswer4) {
                onAnswer();
            }
            stage.close();
        });

        anchorPane.getChildren().setAll(wrongImg, lb1, lb2, okBtn);
        anchorPane.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 5");
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Fuck");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private void trueAnswer() {
        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 450, 350);

        ImageView trueImg = new ImageView(trueIcon);
        trueImg.setFitWidth(100);
        trueImg.setFitHeight(100);
        trueImg.setLayoutX(175);
        trueImg.setLayoutY(15);

        Label lb1 = new Label("Congrats!");
        Label lb2 = new Label("      Your answer is true\nThe angle "
                + (currentQuestion + 1) + " will be flipped");
        lb1.setPrefWidth(450);
        lb1.setPrefHeight(30);
        lb1.setLayoutX(0);
        lb1.setLayoutY(130);
        lb2.setPrefWidth(450);
        lb2.setPrefHeight(60);
        lb2.setLayoutX(0);
        lb2.setLayoutY(200);
        lb1.setStyle("-fx-alignment: center;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 3.5em;" +
                "-fx-font-weight: bold");
        lb2.setStyle("-fx-alignment: center;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 1.5em;" +
                "-fx-wrap-text: true");

        Button okBtn = new Button("OK");
        okBtn.setPrefWidth(100);
        okBtn.setPrefHeight(30);
        okBtn.setLayoutX(175);
        okBtn.setLayoutY(290);
        okBtn.setCursor(Cursor.HAND);
        okBtn.setStyle("-fx-text-fill: #FFFFFF;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: #87CEEB");

        okBtn.setOnAction(event -> {
            switch (currentQuestion) {
                case 0:
                    angle1.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    row1.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    isTrue1 = true;
                    break;
                case 1:
                    angle2.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    row2.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    isTrue2 = true;
                    break;
                case 2:
                    angle3.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    row3.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    isTrue3 = true;
                    break;
                case 3:
                    angle4.setImage(imgList[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    row4.setImage(rowAnswer[4 * ChooseLevelController.currentLevel + currentQuestion]);
                    isTrue4 = true;
                    break;
            }
            if (isAnswer1 && isAnswer2 && isAnswer3 && isAnswer4) {
                onAnswer();
            }
            stage.close();
        });

        anchorPane.getChildren().setAll(trueImg, lb1, lb2, okBtn);
        anchorPane.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 5");
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Fuck");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private String answer = "";
    private int currentQuestion = 0;
    private boolean isAnswer1 = false;
    private boolean isAnswer2 = false;
    private boolean isAnswer3 = false;
    private boolean isAnswer4 = false;
    public static boolean isTrue1 = false;
    public static boolean isTrue2 = false;
    public static boolean isTrue3 = false;
    public static boolean isTrue4 = false;
    public static final Image[] imgList = new Image[20];
    private final Image[] row = new Image[20];
    private final Image[] rowAnswer = new Image[20];
    private final Image[] imgFalse = new Image[4];
    private Image trueIcon;
    private Image wrongIcon;
}
