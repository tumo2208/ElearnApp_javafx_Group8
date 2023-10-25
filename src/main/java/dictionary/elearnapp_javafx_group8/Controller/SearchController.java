package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

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
        //Model.getInstance().getDictionary().insertFromFile(Model.getInstance().getWordList());
        Model.getInstance().getDictionary().setTrie(Model.getInstance().getWordList());
        listViewDefault();
        deleteSearchButton.setVisible(false);
        searchField.setOnKeyTyped(keyEvent -> {
            if (searchField.getText().isEmpty()){
                deleteSearchButton.setVisible(false);
                listViewDefault();
            } else {
                deleteSearchButton.setVisible(true);
                observableList.clear();
                String searchWord = searchField.getText().trim();
                observableList = Model.getInstance().getDictionary().Lookup(searchWord);
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
        for (int i = 0; i < Math.min(20, Model.getInstance().getWordList().size()); i++) {
            observableList.add(Model.getInstance().getWordList().get(i).getWordTarget());
        }
        listView.setItems(observableList);
    }

    //private final List<Word> wordList = new ArrayList<>();
    //private final Dictionary dictionary = new Dictionary();
    private ObservableList<String> observableList = FXCollections.observableArrayList();
}
