package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AddWordController implements Initializable {

    public Button addNewWordButton;
    public TextArea newDefinitionArea;
    public TextField newWordField;
    public Label notiLabel;
    public ImageView notiImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            success = new Image(getClass().getResource("/Images/successNoti.png").toString());
            error = new Image(getClass().getResource("/Images/errorNoti.png").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        resetAllAdd();
        notiLabel.getStylesheets().add(getClass().getResource("/Styles/AddWord.css").toString());

        newWordField.setOnKeyTyped(keyEvent -> {
            addNewWordButton.setDisable(newWordField.getText().isEmpty() || newDefinitionArea.getText().isEmpty());
            notiLabel.setVisible(false);
        });
        newDefinitionArea.setOnKeyTyped(keyEvent -> {
            addNewWordButton.setDisable(newWordField.getText().isEmpty() || newDefinitionArea.getText().isEmpty());
            notiLabel.setVisible(false);
        });

        addNewWordButton.setOnAction(event -> addWord());

    }

    private void addWord() {
        String word = newWordField.getText().trim();
        for (int i = 0; i < word.length(); ++i) {
            if (!Character.isAlphabetic(word.charAt(i)) && word.charAt(i) != '-' && word.charAt(i) != ' ' &&  word.charAt(i) != (char) 39) {
                if (word.charAt(i) < '0' && word.charAt(i) > '9') {
                    word = word.replace(Character.toString(word.charAt(i)), "");
                    --i;
                }
            }
        }
        System.out.println(word);
        Word newWord = new Word(word, newDefinitionArea.getText().trim());
        if (Model.getInstance().getDictionary().Searcher(Model.getInstance().getWordList(), newWord.getWordTarget()) == -1) {
            Model.getInstance().getDictionary().addWord(newWord, dbPath);
            Model.getInstance().getWordList().add(newWord);
            Model.getInstance().getDictionary().setTrie(Model.getInstance().getWordList());
            Model.getInstance().getWordList().sort(Comparator.comparing(Word::getWordTarget));
            notiLabel.setText("Add word Successfully");
            notiLabel.getStyleClass().removeAll();
            notiLabel.getStyleClass().setAll("success_notification");
            notiImg.setImage(success);
        } else {
            notiLabel.setText("Error: word is already exists");
            notiLabel.getStyleClass().removeAll();
            notiLabel.getStyleClass().setAll("error_notification");
            notiImg.setImage(error);
        }
        notiLabel.setVisible(true);
        newWordField.clear();
        newDefinitionArea.clear();
    }

    private void resetAllAdd() {
        newWordField.clear();
        newDefinitionArea.clear();
        addNewWordButton.setDisable(true);
        notiLabel.setText("");
        notiLabel.setVisible(false);
    }

    final String dbPath = "src/main/resources/Database/data.txt";
    private Image success;
    private Image error;
}