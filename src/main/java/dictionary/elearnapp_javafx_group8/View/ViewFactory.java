package dictionary.elearnapp_javafx_group8.View;

import dictionary.elearnapp_javafx_group8.Controller.AppUIController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    private StringProperty selectedMenu = new SimpleStringProperty();
    private AnchorPane searchView;
    private AnchorPane addWordView;
    private AnchorPane APIView;
    private AnchorPane gameView;
    private AnchorPane saveView;
    private AnchorPane memoriesMenuView;
    private AnchorPane memoryPlayView;
    private AnchorPane catchTheWordView;
    private AnchorPane playGameTuView;
    private AnchorPane questionTuView;
    private AnchorPane answerTuView;
    private AnchorPane loseTuView;
    private AnchorPane winTuView;

    public ViewFactory() {
        selectedMenu = new SimpleStringProperty("");
    }

    public StringProperty selectedMenuProperty() {
        return selectedMenu;
    }

    public AnchorPane getSearchView() {
        try {
            searchView = new FXMLLoader(getClass().getResource("/FXML/Search.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchView;
    }

    public AnchorPane getAddWordView() {
        try {
            addWordView = new FXMLLoader(getClass().getResource("/FXML/AddWord.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addWordView;
    }

    public AnchorPane getAPIView() {
        try {
            APIView = new FXMLLoader(getClass().getResource("/FXML/API.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return APIView;
    }

    public AnchorPane getGameView() {
        try {
            gameView = new FXMLLoader(getClass().getResource("/FXML/Game.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameView;
    }

    public AnchorPane getSaveView() {
        try {
            saveView = new FXMLLoader(getClass().getResource("/FXML/Save.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saveView;
    }

    public AnchorPane getMemoriesMenuView() {
        try {
            memoriesMenuView = new FXMLLoader(getClass().getResource("/FXML/GamePhuoc/MenuGame.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memoriesMenuView;
    }

    public AnchorPane getMemoryPlayView() {
        try {
            memoryPlayView = new FXMLLoader(getClass().getResource("/FXML/GamePhuoc/Memories.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memoryPlayView;
    }

    public AnchorPane getCatchTheWordView() {
        try {
            catchTheWordView = new FXMLLoader(getClass().getResource("/FXML/GameTu/CatchTheWord.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catchTheWordView;
    }

    public AnchorPane getPlayGameTuView() {
        try {
            playGameTuView = new FXMLLoader(getClass().getResource("/FXML/GameTu/ChooseLevel.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return playGameTuView;
    }

    public AnchorPane getQuestionTuView() {
        try {
            questionTuView = new FXMLLoader(getClass().getResource("/FXML/GameTu/Question.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionTuView;
    }

    public AnchorPane getAnswerTuView() {
        try {
            answerTuView = new FXMLLoader(getClass().getResource("/FXML/GameTu/Answer.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answerTuView;
    }

    public AnchorPane getLoseTuView() {
        try {
            loseTuView = new FXMLLoader(getClass().getResource("/FXML/GameTu/Lose.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loseTuView;
    }

    public AnchorPane getWinTuView() {
        try {
            winTuView = new FXMLLoader(getClass().getResource("/FXML/GameTu/Win.fxml")).load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return winTuView;
    }

    public void showAppWindow(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/AppUI.fxml"));
        AppUIController appUIController = new AppUIController();
        fxmlLoader.setController(appUIController);
        createStage(fxmlLoader, stage);
    }

    private void createStage(FXMLLoader fxmlLoader, Stage stage) {
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("English Learning Application");
        stage.setResizable(false);
        stage.show();
    }
}
