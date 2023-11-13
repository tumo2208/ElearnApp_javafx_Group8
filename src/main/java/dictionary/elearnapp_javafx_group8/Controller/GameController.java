package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public ImageView GamePhuoc;
    public ImageView GameTu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GameTu.setOnMouseClicked(mouseEvent -> onCatchTheWord());
        GamePhuoc.setOnMouseClicked(mouseEvent -> onMemories());
    }

    private void onCatchTheWord() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("CatchTheWord");
    }

    private void onMemories() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("Memories");
    }
}