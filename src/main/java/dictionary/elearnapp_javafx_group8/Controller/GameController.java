package dictionary.elearnapp_javafx_group8.Controller;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public ImageView GamePhuocInner;
    public ImageView GameTuInner;
    public ImageView GamePhuoc;
    public ImageView GameTu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TransitionOnHover(GameTu, GameTuInner);
        TransitionOnHover(GamePhuoc, GamePhuocInner);
        GameTu.setOnMouseClicked(mouseEvent -> onCatchTheWord());
        GamePhuoc.setOnMouseClicked(mouseEvent -> onMemories());
    }

    private void onCatchTheWord() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("CatchTheWord");
    }

    private void onMemories() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesMenu");
    }

    private void TransitionOnHover(ImageView onHover, ImageView transition) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), transition);
        onHover.setOnMouseEntered(mouseEvent -> {
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.2);
            st.setToY(1.2);
            st.play();
        });
        onHover.setOnMouseExited(mouseEvent -> {
            st.setFromX(1.2);
            st.setFromY(1.2);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });
    }
}