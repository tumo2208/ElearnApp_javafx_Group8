package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Dictionary;
import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.*;

public class MenuController implements Initializable {

    private List<Word> wordList = new ArrayList<>();
    private Dictionary dictionary = new Dictionary();
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    public TextField search_fld;
    public Button delete_text_btn;
    public ListView<String> list_view;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionary.insertFromFile(wordList);
        dictionary.setTrie(wordList);
        listViewDefault();
        search_fld.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
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
            }
        });

        delete_text_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                search_fld.clear();
                delete_text_btn.setVisible(false);
                listViewDefault();
            }
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
