package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Comparator;
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
    public Button saveButton;
    public ImageView saveIcon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            saveDefault = new Image(getClass().getResource("/Images/saveButton.png").toString());
            saveLight = new Image(getClass().getResource("/Images/saveLightButton.png").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        definitionArea.setEditable(false);
        resetAllSearch();

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

            if (Model.getInstance().getWordList().get(indexOfWordSelected).isSaved()) {
                saveIcon.setImage(saveLight);
            } else {
                saveIcon.setImage(saveDefault);
            }

            indexOfWordHistory = Model.getInstance().getHistory().Searcher(Model.getInstance().getHistoryList(), Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
            if (indexOfWordHistory == -1) {
                Model.getInstance().getHistory().addWord(Model.getInstance().getWordList().get(indexOfWordSelected), historyPath);
                Model.getInstance().getHistoryList().add(Model.getInstance().getWordList().get(indexOfWordSelected));
                if (Model.getInstance().getHistoryList().size() > 20) {
                    Model.getInstance().getHistory().deleteWord(Model.getInstance().getHistoryList(), 0, historyPath);
                }
            } else {
                Model.getInstance().getHistory().deleteWord(Model.getInstance().getHistoryList(), indexOfWordHistory, historyPath);
                Model.getInstance().getHistory().addWord(Model.getInstance().getWordList().get(indexOfWordSelected), historyPath);
                Model.getInstance().getHistoryList().add(Model.getInstance().getWordList().get(indexOfWordSelected));
            }

            if (searchField.getText().isEmpty()) {
                observableList.clear();
                for (int i = Model.getInstance().getHistoryList().size() - 1; i >= 0; --i) {
                    observableList.add(Model.getInstance().getHistoryList().get(i).getWordTarget());
                }
                listView.setItems(observableList);
            }

            wordLabel.setText(Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
            definitionArea.setText(Model.getInstance().getWordList().get(indexOfWordSelected).getWordExplain());

            if (definitionArea.getText().isEmpty()) {
                listenButton.setVisible(false);
                editWordButton.setVisible(false);
                deleteWordButton.setVisible(false);
                saveButton.setVisible(false);
                listenButton.setDisable(true);
                editWordButton.setDisable(true);
                deleteWordButton.setDisable(true);
                saveButton.setDisable(true);
            } else {
                listenButton.setVisible(true);
                editWordButton.setVisible(true);
                deleteWordButton.setVisible(true);
                saveButton.setVisible(true);
                listenButton.setDisable(false);
                editWordButton.setDisable(false);
                deleteWordButton.setDisable(false);
                saveButton.setDisable(false);
            }
        });

        saveButton.setOnAction(event -> {
            if (Model.getInstance().getWordList().get(indexOfWordSelected).isSaved()) {
                Model.getInstance().getWordList().get(indexOfWordSelected).setSaved(false);
                saveIcon.setImage(saveDefault);
                indexOfWordSave = Model.getInstance().getSave().Searcher(Model.getInstance().getSaveList(),
                        Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
                Model.getInstance().getSave().deleteWord(Model.getInstance().getSaveList(), indexOfWordSave, savePath);
            } else {
                Model.getInstance().getWordList().get(indexOfWordSelected).setSaved(true);
                saveIcon.setImage(saveLight);
                Model.getInstance().getSave().addWord(Model.getInstance().getWordList().get(indexOfWordSelected), savePath);
                Model.getInstance().getSaveList().add(Model.getInstance().getWordList().get(indexOfWordSelected));
                Model.getInstance().getSaveList().sort(Comparator.comparing(Word::getWordTarget));
            }
        });

        deleteWordButton.setOnAction(event -> {
            indexOfWordSave = Model.getInstance().getSave().Searcher(Model.getInstance().getSaveList(),
                    Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
            indexOfWordHistory = Model.getInstance().getHistory().Searcher(Model.getInstance().getHistoryList(),
                    Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
            if (indexOfWordSave != -1) {
                Model.getInstance().getSave().deleteWord(Model.getInstance().getSaveList(),
                        indexOfWordSave, savePath);
            }
            if (indexOfWordHistory != -1) {
                Model.getInstance().getHistory().deleteWord(Model.getInstance().getHistoryList(),
                        indexOfWordHistory, historyPath);
            }
            Model.getInstance().getDictionary().deleteWord(Model.getInstance().getWordList(), indexOfWordSelected, dbPath);
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
                saveButton.setVisible(false);
                listenButton.setDisable(true);
                editWordButton.setDisable(true);
                deleteWordButton.setDisable(true);
                saveButton.setDisable(true);
                deleteSearchButton.setVisible(false);
                deleteSearchButton.setDisable(true);
            } else {
                listenButton.setVisible(true);
                editWordButton.setVisible(true);
                deleteWordButton.setVisible(true);
                saveButton.setVisible(true);
                listenButton.setDisable(false);
                editWordButton.setDisable(false);
                deleteWordButton.setDisable(false);
                saveButton.setDisable(false);
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
                        indexOfWordSelected, editArea.getText().trim(), dbPath);
                indexOfWordSave = Model.getInstance().getSave().Searcher(Model.getInstance().getSaveList(),
                        Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
                indexOfWordHistory = Model.getInstance().getHistory().Searcher(Model.getInstance().getHistoryList(),
                        Model.getInstance().getWordList().get(indexOfWordSelected).getWordTarget());
                if (indexOfWordSave != -1) {
                    Model.getInstance().getSave().updateWord(Model.getInstance().getSaveList(),
                            indexOfWordSave, editArea.getText().trim(), savePath);
                }
                if (indexOfWordHistory != -1) {
                    Model.getInstance().getHistory().updateWord(Model.getInstance().getHistoryList(),
                            indexOfWordHistory, editArea.getText().trim(), historyPath);
                }
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

    private void resetAllSearch() {
        searchField.clear();
        listViewDefault();
        deleteSearchButton.setVisible(false);
        deleteSearchButton.setDisable(true);
        listenButton.setVisible(false);
        editWordButton.setVisible(false);
        deleteWordButton.setVisible(false);
        saveButton.setVisible(false);
        listenButton.setDisable(true);
        editWordButton.setDisable(true);
        deleteWordButton.setDisable(true);
        saveButton.setDisable(true);
        definitionArea.clear();
        wordLabel.setText("");
    }

    private void listViewDefault() {
        observableList.clear();
        for (int i = Model.getInstance().getHistoryList().size() - 1; i >= 0; --i) {
            observableList.add(Model.getInstance().getHistoryList().get(i).getWordTarget());
        }
        listView.setItems(observableList);
    }

    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private final VoiceController voice = new VoiceController();
    private int indexOfWordSelected = -1;
    private int indexOfWordHistory = -1;
    private int indexOfWordSave = -1;
    private final String dbPath = "src/main/resources/Database/data.txt";
    private final String historyPath = "src/main/resources/Database/history.txt";
    private final String savePath = "src/main/resources/Database/save.txt";
    private Image saveDefault;
    private Image saveLight;
}
