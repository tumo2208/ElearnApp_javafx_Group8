package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AnswerController implements Initializable {
    public TextField answerObstacleLabel;
    public Button cfObstacleButton;
    public ImageView angle1Img;
    public ImageView angle2Img;
    public ImageView angle3Img;
    public ImageView angle4Img;
    public Label numLetters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (QuestionController.isTrue1) {
            angle1Img.setImage(QuestionController.imgList[4 * ChooseLevelController.currentLevel]);
        }
        if (QuestionController.isTrue2) {
            angle2Img.setImage(QuestionController.imgList[4 * ChooseLevelController.currentLevel + 1]);
        }
        if (QuestionController.isTrue3) {
            angle3Img.setImage(QuestionController.imgList[4 * ChooseLevelController.currentLevel + 2]);
        }
        if (QuestionController.isTrue4) {
            angle4Img.setImage(QuestionController.imgList[4 * ChooseLevelController.currentLevel + 3]);
        }

        numLetters.setText("This obstacle has "
                + Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getAnswer().length()
                + " letters");
        cfObstacleButton.setDisable(true);

        answerObstacleLabel.setOnKeyTyped(keyEvent -> cfObstacleButton.setDisable(answerObstacleLabel.getText().isEmpty()));

        cfObstacleButton.setOnAction(event -> setConfirmDialog());
    }

    private void setConfirmDialog() {
        Label lb = new Label("Are you sure to submit this answer?");
        confirmDialog.getDialogPane().setContent(new VBox(lb));
        confirmDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        confirmDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> option = confirmDialog.showAndWait();
        if (option.get() == ButtonType.CLOSE || option.get() == ButtonType.CANCEL) {
            confirmDialog.close();
        } else if (option.get() == ButtonType.OK) {
            String answer = answerObstacleLabel.getText().trim();
            answer = answer.replace(" ", "");
            answer = answer.toLowerCase();
            if (answer.equals(Model.getInstance().getQuestionCatchWordList().get(ChooseLevelController.currentLevel).getAnswer())) {
                try {
                    FileReader fileReader = new FileReader("src/main/resources/Database/GameTu/level.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    int numLevel = Integer.parseInt(bufferedReader.readLine());
                    if (numLevel == ChooseLevelController.currentLevel + 1 && numLevel < 5) {
                        numLevel++;
                        FileWriter fileWriter = new FileWriter("src/main/resources/Database/GameTu/level.txt");
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(Integer.toString(numLevel));
                        bufferedWriter.close();
                    }
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                onWin();
            } else {
                onLose();
            }
        }
    }

    private void onWin() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("WinTu");
    }

    private void onLose() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("LoseTu");
    }

    private final Dialog<ButtonType> confirmDialog = new Dialog<>();
}