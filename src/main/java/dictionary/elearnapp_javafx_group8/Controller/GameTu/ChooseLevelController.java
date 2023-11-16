package dictionary.elearnapp_javafx_group8.Controller.GameTu;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseLevelController implements Initializable {
    public ImageView lv1;
    public ImageView lv2;
    public ImageView lv3;
    public ImageView lv4;
    public ImageView lv5;
    public ImageView backButton;
    public AnchorPane chooseLevelBG;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseLevelBG.setBackground(CatchTheWordController.background);
        try {
            lockLv = new Image(getClass().getResource("/Images/GameTu/locklv.png").toString());
            level2 = new Image(getClass().getResource("/Images/GameTu/lv2.png").toString());
            level3 = new Image(getClass().getResource("/Images/GameTu/lv3.png").toString());
            level4 = new Image(getClass().getResource("/Images/GameTu/lv4.png").toString());
            level5 = new Image(getClass().getResource("/Images/GameTu/lv5.png").toString());
            backIce = new Image(getClass().getResource("/Images/GameTu/Back_Button.png").toString());
            backFire = new Image(getClass().getResource("/Images/GameTu/Back_Button_Click.png").toString());
            FileReader fileReader = new FileReader("src/main/resources/Database/GameTu/level.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            numOfLevel = Integer.parseInt(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (numOfLevel == 2) {
            lv2.setImage(level2);
            lv3.setImage(lockLv);
            lv4.setImage(lockLv);
            lv5.setImage(lockLv);
        } else if (numOfLevel == 3) {
            lv2.setImage(level2);
            lv3.setImage(level3);
            lv4.setImage(lockLv);
            lv5.setImage(lockLv);
        } else if (numOfLevel == 4) {
            lv2.setImage(level2);
            lv3.setImage(level3);
            lv4.setImage(level4);
            lv5.setImage(lockLv);
        } else if (numOfLevel == 5) {
            lv2.setImage(level2);
            lv3.setImage(level3);
            lv4.setImage(level4);
            lv5.setImage(level5);
        }

        lv1.setOnMouseClicked(mouseEvent -> {
            onQuestion();
            currentLevel = 0;
        });

        lv2.setOnMouseClicked(mouseEvent -> {
            if (numOfLevel >= 2) {
                onQuestion();
                currentLevel = 1;
            }
        });

        lv3.setOnMouseClicked(mouseEvent -> {
            if (numOfLevel >= 3) {
                onQuestion();
                currentLevel = 2;
            }
        });

        lv4.setOnMouseClicked(mouseEvent -> {
            if (numOfLevel >= 4) {
                onQuestion();
                currentLevel = 3;
            }
        });

        lv5.setOnMouseClicked(mouseEvent -> {
            if (numOfLevel >= 5) {
                onQuestion();
                currentLevel = 4;
            }
        });

        backButton.setOnMouseEntered(mouseEvent -> {
            backButton.setImage(backFire);
        });

        backButton.setOnMouseExited(mouseEvent -> {
            backButton.setImage(backIce);
        });

        backButton.setOnMouseClicked(mouseEvent -> onCatchTheWord());
    }

    private void onCatchTheWord() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("CatchTheWord");
    }

    private void onQuestion() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("QuestionTu");
    }

    private Image backIce;
    private Image backFire;
    private Image lockLv;
    private Image level2;
    private Image level3;
    private Image level4;
    private Image level5;
    private int numOfLevel;
    public static int currentLevel = 0;
}
