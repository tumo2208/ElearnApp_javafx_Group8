package dictionary.elearnapp_javafx_group8.Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ButtonCellController extends ListCell<String> {
    private final String historyPath = "src/main/resources/Database/history.txt";
    HBox hbox = new HBox();
    Label label = new Label("");
    Pane pane = new Pane();
    Button button = new Button("");
    String lastItem;

    public ButtonCellController(TextField searchField) {
        super();
        hbox.getChildren().addAll(label, pane, button);
        HBox.setHgrow(pane, Priority.ALWAYS);
        FontAwesomeIconView iconDelete = new FontAwesomeIconView();
        iconDelete.setIcon(FontAwesomeIcon.TIMES);
        this.button.setGraphic(iconDelete);
        this.button.disableProperty().bind(searchField.textProperty().isEmpty().not());
        this.button.visibleProperty().bind(searchField.textProperty().isEmpty());
        this.button.setOnAction(event -> {
            ListView<String> listView = this.getListView();
            int indexOfWordHistory = Model.getInstance().getHistory().Searcher(Model.getInstance().getHistoryList(), this.getItem());
            Model.getInstance().getHistory().deleteWord(Model.getInstance().getHistoryList(), indexOfWordHistory, historyPath);
            listView.getItems().remove(this.getItem());
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            label.setText(item);
            setGraphic(hbox);
        }
    }
}
