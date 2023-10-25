package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Dictionary;
import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppUIController implements Initializable {

    public Button addNewWordButton;
    public TextArea newDefinitionArea;
    public TextField newWordField;

    public TextField searchField;
    public Button deleteSearchButton;
    public ListView<String> listView;
    public Label wordLabel;
    public Button listenButton;
    public Button editWordButton;
    public Button deleteWordButton;
    public TextArea definitionArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionary.insertFromFile(wordList);
        dictionary.setTrie(wordList);
        listViewDefault();
        searchField.setOnKeyTyped(keyEvent -> {
            if (searchField.getText().isEmpty()){
                deleteSearchButton.setVisible(false);
                listViewDefault();
            } else {
                deleteSearchButton.setVisible(true);
                observableList.clear();
                String searchWord = searchField.getText().trim();
                observableList = dictionary.Lookup(searchWord);
                if (observableList.isEmpty()) {
                    listViewDefault();
                } else {
                    listView.setItems(observableList);
                }
            }
        });

        deleteSearchButton.setOnAction(event -> {
            searchField.clear();
            deleteSearchButton.setVisible(false);
            listViewDefault();
        });
    }

    private void listViewDefault(){
        observableList.clear();
        for (int i = 0; i < 20; i++) {
            observableList.add(wordList.get(i).getWordTarget());
        }
        listView.setItems(observableList);
    }

    private List<Word> wordList = new ArrayList<>();
    private Dictionary dictionary = new Dictionary();
    private ObservableList<String> observableList = FXCollections.observableArrayList();
}