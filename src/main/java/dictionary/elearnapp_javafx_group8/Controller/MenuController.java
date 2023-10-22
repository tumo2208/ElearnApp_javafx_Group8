package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Dictionary;
import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class MenuController implements Initializable {

    public Button add_word_btn;
    public Button gg_translate_btn;
    public Button learn_btn;
    public Button game_btn;
    public TextField search_fld;
    public Button delete_text_btn;
    public ListView<String> list_view;

    private final List<Word> wordList = new ArrayList<>();
    private final Dictionary dictionary = new Dictionary();
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionary.insertFromFile(wordList);
        dictionary.setTrie(wordList);
        listViewDefault();
        search_fld.setOnKeyTyped(keyEvent -> {
            if (search_fld.getText().isEmpty()){
                delete_text_btn.setVisible(false);
                listViewDefault();
            } else {
                delete_text_btn.setVisible(true);
                observableList.clear();
                String searchWord = search_fld.getText().trim();
                observableList = dictionary.Lookup(searchWord);
                if (observableList.isEmpty()) {
                    listViewDefault();
                } else {
                    list_view.setItems(observableList);
                }
            }
        });

        delete_text_btn.setOnAction(event -> {
            search_fld.clear();
            delete_text_btn.setVisible(false);
            listViewDefault();
        });
    }

    private void listViewDefault(){
        observableList.clear();
        for (int i = 0; i < 10; i++) {
            observableList.add(wordList.get(i).getWordTarget());
        }
        list_view.setItems(observableList);
    }
}
