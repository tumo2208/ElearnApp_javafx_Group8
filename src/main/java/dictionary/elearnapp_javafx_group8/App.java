package dictionary.elearnapp_javafx_group8;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showAppWindow(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
