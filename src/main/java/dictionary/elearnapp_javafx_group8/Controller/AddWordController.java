package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWordController implements Initializable {

    public Button addNewWordButton;
    public TextArea newDefinitionArea;
    public TextField newWordField;
    public Label notiLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Model.getInstance().getDictionary().insertFromFile(Model.getInstance().getWordList());
        addNewWordButton.setDisable(newWordField.getText().isEmpty() || newDefinitionArea.getText().isEmpty());
        notiLabel.setVisible(false);

        newWordField.setOnKeyTyped(keyEvent -> addNewWordButton.setDisable(newWordField.getText().isEmpty() || newDefinitionArea.getText().isEmpty()));
        newDefinitionArea.setOnKeyTyped(keyEvent -> addNewWordButton.setDisable(newWordField.getText().isEmpty() || newDefinitionArea.getText().isEmpty()));

        addNewWordButton.setOnAction(event -> addWord());
    }

    private void addWord() {
        Word newWord = new Word(newWordField.getText().trim(),  newDefinitionArea.getText().trim());
        if (!Model.getInstance().getWordList().contains(newWord)) {
            Model.getInstance().getWordList().add(newWord);
            Model.getInstance().getDictionary().addWord(newWord);
            notiLabel.setText("Add word Successfully");
        } else {
            notiLabel.setText("Error: word is already exists");
        }
        notiLabel.setVisible(true);
        //notiLabel.setStyleClass()
        newWordField.clear();
        newDefinitionArea.clear();
    }

    //private final List<Word> wordList = new ArrayList<>();
    //private final Dictionary dictionary = new Dictionary();
}