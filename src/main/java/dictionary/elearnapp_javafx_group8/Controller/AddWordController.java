package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AddWordController implements Initializable {

    public Button addNewWordButton;
    public TextArea newDefinitionArea;
    public TextField newWordField;
    public Label notiLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNewWordButton.setDisable(newWordField.getText().isEmpty() || newDefinitionArea.getText().isEmpty());
        notiLabel.setVisible(false);

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
        Word newWord = new Word(newWordField.getText().trim(),  newDefinitionArea.getText().trim());
        if (Model.getInstance().getDictionary().Searcher(Model.getInstance().getWordList(), newWord.getWordTarget()) == -1) {
            Model.getInstance().getDictionary().addWord(newWord);
            Model.getInstance().getWordList().add(newWord);
            Model.getInstance().getDictionary().setTrie(Model.getInstance().getWordList());
            Model.getInstance().getWordList().sort(Comparator.comparing(Word::getWordTarget));
            notiLabel.setText("Add word Successfully");
            notiLabel.setStyle("-fx-text-fill: #098000;" +
                    "-fx-font-family: \"Calibri Light\";" +
                    "-fx-font-size: 15px;");
        } else {
            notiLabel.setText("Error: word is already exists");
            notiLabel.setStyle("-fx-text-fill: #ff0000;" +
                    "-fx-font-family: \"Calibri Light\";" +
                    "-fx-font-size: 15px;");
        }
        notiLabel.setVisible(true);
        newWordField.clear();
        newDefinitionArea.clear();
    }
}
