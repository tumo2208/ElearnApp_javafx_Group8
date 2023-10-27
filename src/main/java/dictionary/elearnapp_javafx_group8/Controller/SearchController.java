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
        listViewDefault();
        deleteSearchButton.setVisible(false);
        definitionArea.setEditable(false);
        listenButton.setVisible(false);
        editWordButton.setVisible(false);
        deleteWordButton.setVisible(false);
        searchField.setOnKeyTyped(keyEvent -> {
            if (searchField.getText().isEmpty()){
                deleteSearchButton.setVisible(false);
                listViewDefault();
            } else {
                deleteSearchButton.setVisible(true);
                observableList.clear();
                String searchWord = searchField.getText().trim();
                observableList = Model.getInstance().getDictionary().Lookup(searchWord);
                listView.setItems(observableList);
            }
        });

        deleteSearchButton.setOnAction(event -> {
            searchField.clear();
            deleteSearchButton.setVisible(false);
            listViewDefault();
        });

        listView.setOnMouseClicked(MouseEvent -> {
            definitionArea.clear();
            wordLabel.setText("");
            String selectedWord = listView.getSelectionModel().getSelectedItem();
            indexOfWordSelected = Model.getInstance().getDictionary().Searcher(Model.getInstance().getWordList(), selectedWord);
            System.out.println(indexOfWordSelected);
            wordLabel.setText(Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
            definitionArea.setText(Model.getInstance().getWordList().get(indexOfWordSelected).getWordExplain());
            if (definitionArea.getText().isEmpty()) {
                listenButton.setVisible(false);
                editWordButton.setVisible(false);
                deleteWordButton.setVisible(false);
            } else {
                listenButton.setVisible(true);
                editWordButton.setVisible(true);
                deleteWordButton.setVisible(true);
            }
        });

        deleteWordButton.setOnAction(event -> {
            Model.getInstance().getDictionary().deleteWord(Model.getInstance().getWordList(), indexOfWordSelected);
            listViewDefault();
            listView.setItems(observableList);
            searchField.clear();
            definitionArea.clear();
            wordLabel.setText("");
            indexOfWordSelected = -1;
        });
    }

    private void listViewDefault(){
        observableList.clear();
        for (int i = 0; i < Math.min(20, Model.getInstance().getWordList().size()); i++) {
            observableList.add(Model.getInstance().getWordList().get(i).getWordTarget());
        }
        listView.setItems(observableList);
    }

    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private int indexOfWordSelected = -1;
}