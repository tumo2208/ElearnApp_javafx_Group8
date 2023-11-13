package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        angle1.setOnMouseClicked(mouseEvent -> {
            currentQuestion = 0;
            question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());

        });
        angle2.setOnMouseClicked(mouseEvent -> {
            currentQuestion = 1;
            question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());

        });
        angle3.setOnMouseClicked(mouseEvent -> {
            currentQuestion = 2;
            question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());

        });
        angle4.setOnMouseClicked(mouseEvent -> {
            currentQuestion = 3;
            question.setText(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getQuestion());

        });
        submitButton.setOnAction(event -> {
            answer = answerField.getText().trim();
            if (answer.equals(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getQanda().get(currentQuestion).getAnswer())) {
                switch (currentQuestion) {
                    case 0: angle1.setImage(ChooseLevelController.level2);
                        break;
                    case 1: angle2.setImage(ChooseLevelController.level2);
                        break;
                    case 2: angle3.setImage(ChooseLevelController.level2);
                        break;
                    case 3: angle4.setImage(ChooseLevelController.level2);
                        break;
                }
            }
        });
    }

    private String answer = "";
    private int currentQuestion = 0;
}
