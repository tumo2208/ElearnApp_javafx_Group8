package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Optional;
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
    public Dialog<ButtonType> editDialog;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewDefault();
        deleteSearchButton.setVisible(false);
        deleteSearchButton.setDisable(true);
        definitionArea.setEditable(false);
        listenButton.setVisible(false);
        editWordButton.setVisible(false);
        deleteWordButton.setVisible(false);
        listenButton.setDisable(true);
        editWordButton.setDisable(true);
        deleteWordButton.setDisable(true);
        searchField.setOnKeyTyped(keyEvent -> {
            if (searchField.getText().isEmpty()) {
                deleteSearchButton.setVisible(false);
                deleteSearchButton.setDisable(true);
                listViewDefault();
            } else {
                deleteSearchButton.setVisible(true);
                deleteSearchButton.setDisable(false);
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
                listenButton.setDisable(true);
                editWordButton.setDisable(true);
                deleteWordButton.setDisable(true);
            } else {
                listenButton.setVisible(true);
                editWordButton.setVisible(true);
                deleteWordButton.setVisible(true);
                listenButton.setDisable(false);
                editWordButton.setDisable(false);
                deleteWordButton.setDisable(false);
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
            if (definitionArea.getText().isEmpty()) {
                listenButton.setVisible(false);
                editWordButton.setVisible(false);
                deleteWordButton.setVisible(false);
                listenButton.setDisable(true);
                editWordButton.setDisable(true);
                deleteWordButton.setDisable(true);
                deleteSearchButton.setVisible(false);
                deleteSearchButton.setDisable(true);
            } else {
                listenButton.setVisible(true);
                editWordButton.setVisible(true);
                deleteWordButton.setVisible(true);
                listenButton.setDisable(false);
                editWordButton.setDisable(false);
                deleteWordButton.setDisable(false);
                deleteSearchButton.setVisible(true);
                deleteSearchButton.setDisable(false);
            }
        });

        editWordButton.setOnAction(event -> {
            editDialog = new Dialog<>();
            editDialog.setTitle("Edit Word");
            TextArea editArea = new TextArea();
            editArea.setText(definitionArea.getText());
            editArea.setPrefWidth(500);
            editArea.setPrefHeight(500);
            editDialog.getDialogPane().setContent(new VBox(editArea));
            editDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            editDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> option = editDialog.showAndWait();
            if (option.get() == ButtonType.OK) {
                Model.getInstance().getDictionary().updateWord(Model.getInstance().getWordList(),
                        indexOfWordSelected, editArea.getText().trim());
                editDialog.close();
            } else if (option.get() == ButtonType.CANCEL) {
                editDialog.close();
            } else if (option.get() == ButtonType.CLOSE) {
                editDialog.close();
            }
        });

        listenButton.setOnAction(event -> {
            if (!(wordLabel.getText().isEmpty())) {
                voice.makeSound(wordLabel.getText());
            }
        });
    }

    private void listViewDefault() {
        observableList.clear();
        for (int i = 0; i < Math.min(20, Model.getInstance().getWordList().size()); i++) {
            observableList.add(Model.getInstance().getWordList().get(i).getWordTarget());
        }
        listView.setItems(observableList);
    }

    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private final VoiceController voice = new VoiceController();
    private int indexOfWordSelected = -1;
}
