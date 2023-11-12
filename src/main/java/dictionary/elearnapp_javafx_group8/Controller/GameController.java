package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public Button GamePhuoc;
    public Button GameTu;
    public Button GamePhong;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GamePhuoc.setOnAction(actionEvent -> onGamePhuoc());
        GamePhong.setOnAction(actionEvent -> onGamePhong());
        GameTu.setOnAction(actionEvent -> onGameTu());
    }
    private void onGamePhuoc(){
        Model.getInstance().getViewFactory().selectedMenuProperty().set("GamePhuoc");
    }
    private void onGamePhong(){
        Model.getInstance().getViewFactory().selectedMenuProperty().set("GamePhong");
    }
    private void onGameTu(){
        Model.getInstance().getViewFactory().selectedMenuProperty().set("GameTu");
    }
}
