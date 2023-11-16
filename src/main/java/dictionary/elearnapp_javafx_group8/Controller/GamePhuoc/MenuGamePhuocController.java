package dictionary.elearnapp_javafx_group8.Controller.GamePhuoc;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuGamePhuocController implements Initializable {

    public Button topic1;
    public Button topic10;
    public Button topic11;
    public Button topic12;
    public Button topic2;
    public Button topic3;
    public Button topic4;
    public Button topic5;
    public Button topic6;
    public Button topic7;
    public Button topic8;
    public Button topic9;
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnMouseClicked(e -> onGame());
        topic1.setOnMouseClicked(e -> getPath(1));
        topic2.setOnMouseClicked(e -> getPath(2));
        topic3.setOnMouseClicked(e -> getPath(3));
        topic4.setOnMouseClicked(e -> getPath(4));
        topic5.setOnMouseClicked(e -> getPath(5));
        topic6.setOnMouseClicked(e -> getPath(6));
        topic7.setOnMouseClicked(e -> getPath(7));
        topic8.setOnMouseClicked(e -> getPath(8));
        topic9.setOnMouseClicked(e -> getPath(9));
        topic10.setOnMouseClicked(e -> getPath(10));
        topic11.setOnMouseClicked(e -> getPath(11));
        topic12.setOnMouseClicked(e -> getPath(12));

    }

    private void getPath(int i) {
        String tmp = ".txt";
        String name;
        switch (i) {
            case 1:
                name = "Animal";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 2:
                name = "Sport";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 3:
                name = "Food";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 4:
                name = "Career";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 5:
                name = "Plants";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 6:
                name = "Clothing";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 7:
                name = "Environment";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 8:
                name = "Furniture";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 9:
                name = "Human Body";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 10:
                name = "Transport";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 11:
                name = "Public Area";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
            case 12:
                name = "IT";
                this.path += name;
                MemoriesController.setTopic = name;
                break;
        }
        MemoriesController.pathToGamePhuocData = path + tmp;
        OnPlay();
    }

    private void OnPlay() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesPlay");
    }

    private void onGame() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("Game");
    }

    private String path = "src/main/resources/Database/GamePhuoc/";
}