package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Dictionary;
import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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


    @FXML
    public void mouseClickWord(MouseEvent arg){
        String selectedWord=listView.getSelectionModel().getSelectedItem();
        if(selectedWord!=null){

            wordIndex=dictionary.Searcher(Model.getInstance().getWordList(), selectedWord);
            if(wordIndex==-1){
                return;
            }
                wordLabel.setText(Model.getInstance().getWordList().get(wordIndex).getWordTarget());
                definitionArea.setText(Model.getInstance().getWordList().get(wordIndex).getWordExplain());
                definitionArea.setEditable(false);

        }
    }
    @FXML
    public void mouseClickEdit(MouseEvent arg){
        definitionArea.setEditable(true);
    }
    @FXML
    public void clickSaveButton(MouseEvent arg){
        String wordTarget=wordLabel.getText();
        String wordExplain=definitionArea.getText();
        Model.getInstance().getDictionary().updateWord(Model.getInstance().getWordList(),
                wordIndex,wordTarget,wordExplain );
        definitionArea.setEditable(false);
    }
    public void clickDeleteButton(MouseEvent arg){
        Model.getInstance().getDictionary().deleteWord(Model.getInstance().getWordList(),wordIndex);
        for(int i=0;i<observableList.size();i++){
            if(observableList.get(i).equals(wordLabel.getText())){
                observableList.remove(i);
                break;
            }
        }
        listView.setItems(observableList);
        wordLabel.setText("");
        definitionArea.setText("");
    }


    private int wordIndex;
    private final Dictionary dictionary = new Dictionary();
    private ObservableList<String> observableList = FXCollections.observableArrayList();
}
