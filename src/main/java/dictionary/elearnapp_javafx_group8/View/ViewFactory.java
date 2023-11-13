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

    public ViewFactory() {
        selectedMenu = new SimpleStringProperty("");
    }

    public StringProperty selectedMenuProperty() {
        return selectedMenu;
    }

    public AnchorPane getSearchView() {
        //if (searchView == null) {
            try {
                searchView = new FXMLLoader(getClass().getResource("/FXML/Search.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
        return searchView;
    }

    public AnchorPane getAddWordView() {
        //if (addWordView == null) {
            try {
                addWordView = new FXMLLoader(getClass().getResource("/FXML/AddWord.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
        return addWordView;
    }

    public AnchorPane getAPIView() {
        //if (APIView == null) {
            try {
                APIView = new FXMLLoader(getClass().getResource("/FXML/API.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
        return APIView;
    }

    public AnchorPane getGameView() {
        //if (gameView == null) {
            try {
                gameView = new FXMLLoader(getClass().getResource("/FXML/Game.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
        return gameView;
    }

    public AnchorPane getSaveView() {
        //if (saveView == null) {
            try {
                saveView = new FXMLLoader(getClass().getResource("/FXML/Save.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
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
    public AnchorPane getMemoryPlayView(){
        try {
            memoryPlayView = new FXMLLoader(getClass().getResource("/FXML/GamePhuoc/Memories.fxml")).load();
        } catch (Exception e){
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

    public void showAppWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/AppUI.fxml"));
        AppUIController appUIController = new AppUIController();
        fxmlLoader.setController(appUIController);
        createStage(fxmlLoader);
    }

    private void createStage(FXMLLoader fxmlLoader) {
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("English Learning Application");
        stage.show();
    }
}
