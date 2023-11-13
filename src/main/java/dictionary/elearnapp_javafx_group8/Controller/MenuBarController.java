package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.animation.Animation;
import javafx.animation.Transition.*;
import javafx.animation.ScaleTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {

    public Button addWordButton;
    public Button gameButton;
    public Button googleTranslateButton;
    public Button searchWordButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addWordButton.setOnAction(event -> onAddWord());
        searchWordButton.setOnAction(event -> onSearch());
        googleTranslateButton.setOnAction(event -> onAPI());
        gameButton.setOnAction(event -> onGame());
    }

    private void onSearch() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("Search");
    }

    private void onAddWord() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("AddWord");
    }

    private void onAPI() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("GoogleTranslate");
    }

    private void onGame() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("Game");
    }
}