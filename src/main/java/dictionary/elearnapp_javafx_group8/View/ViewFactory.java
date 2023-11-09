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

    public ViewFactory() {
        selectedMenu = new SimpleStringProperty("");
    }

    public StringProperty selectedMenuProperty() {
        return selectedMenu;
    }

    public AnchorPane getSearchView() {
        if (searchView == null) {
            try {
                searchView = new FXMLLoader(getClass().getResource("/FXML/Search.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return searchView;
    }

    public AnchorPane getAddWordView() {
        if (addWordView == null) {
            try {
                addWordView = new FXMLLoader(getClass().getResource("/FXML/AddWord.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return addWordView;
    }

    public AnchorPane getAPIView() {
        if (APIView == null) {
            try {
                APIView = new FXMLLoader(getClass().getResource("/FXML/API.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return APIView;
    }

    public AnchorPane getGameView() {
        if (gameView == null) {
            try {
                gameView = new FXMLLoader(getClass().getResource("/FXML/Game.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return gameView;
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
