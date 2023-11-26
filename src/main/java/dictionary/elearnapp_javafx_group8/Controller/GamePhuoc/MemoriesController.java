package dictionary.elearnapp_javafx_group8.Controller.GamePhuoc;

import dictionary.elearnapp_javafx_group8.Models.Model;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MemoriesController implements Initializable {
    public Label time;
    public static String setTopic;


    public Label topic;

    public ImageView card0;
    public ImageView card1;
    public ImageView card2;
    public ImageView card3;
    public ImageView card4;
    public ImageView card5;
    public ImageView card6;
    public ImageView card7;
    public ImageView card8;
    public ImageView card9;
    public ImageView card10;
    public ImageView card11;
    public ImageView card12;
    public ImageView card13;
    public ImageView card14;
    public StackPane pane0;
    public StackPane pane1;
    public StackPane pane2;
    public StackPane pane3;
    public StackPane pane4;
    public StackPane pane5;
    public StackPane pane6;
    public StackPane pane7;
    public StackPane pane8;
    public StackPane pane9;
    public StackPane pane10;
    public StackPane pane11;
    public StackPane pane12;
    public StackPane pane13;
    public StackPane pane14;


    public Label word0;
    public Label word1;
    public Label word2;
    public Label word3;
    public Label word4;
    public Label word5;
    public Label word6;
    public Label word7;
    public Label word8;
    public Label word9;
    public Label word10;
    public Label word11;
    public Label word12;
    public Label word13;
    public Label word14;
    public Button Back;


    public static String pathToGamePhuocData;
    public static final String trap = "x2 Time Speed";
    private final List<String> pairs = new ArrayList<>();
    private final List<String> list = new ArrayList<>();
    private final List<Integer> check = new ArrayList<>();
    private final List<Integer> showingCard = new ArrayList<>();
    boolean[] isFrontShowing = new boolean[15];




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 15; i++) {
            isFrontShowing[i] = true;
        }
        readData();
        splitPairs();
        makeList();
        addWord();
        getStart();


        time.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d",
                                remainingDuration.get().toMinutesPart(),
                                remainingDuration.get().toSecondsPart()),
                                remainingDuration));

        countDownTimeLine.setCycleCount((int) remainingDuration.get().getSeconds());
        countDownTimeLine.setOnFinished(event ->
                gameOver()
        );
        countDownTimeLine.play();

        pane0.setOnMouseClicked((t) -> this.isFrontShowing[0] = flipIndex(0));
        pane1.setOnMouseClicked((t) -> this.isFrontShowing[1] = flipIndex(1));
        pane2.setOnMouseClicked((t) -> this.isFrontShowing[2] = flipIndex(2));
        pane3.setOnMouseClicked((t) -> this.isFrontShowing[3] = flipIndex(3));
        pane4.setOnMouseClicked((t) -> this.isFrontShowing[4] = flipIndex(4));
        pane5.setOnMouseClicked((t) -> this.isFrontShowing[5] = flipIndex(5));
        pane6.setOnMouseClicked((t) -> this.isFrontShowing[6] = flipIndex(6));
        pane7.setOnMouseClicked((t) -> this.isFrontShowing[7] = flipIndex(7));
        pane8.setOnMouseClicked((t) -> this.isFrontShowing[8] = flipIndex(8));
        pane9.setOnMouseClicked((t) -> this.isFrontShowing[9] = flipIndex(9));
        pane10.setOnMouseClicked((t) -> this.isFrontShowing[10] = flipIndex(10));
        pane11.setOnMouseClicked((t) -> this.isFrontShowing[11] = flipIndex(11));
        pane12.setOnMouseClicked((t) -> this.isFrontShowing[12] = flipIndex(12));
        pane13.setOnMouseClicked((t) -> this.isFrontShowing[13] = flipIndex(13));
        pane14.setOnMouseClicked((t) -> this.isFrontShowing[14] = flipIndex(14));
        Back.setOnMouseClicked(e -> onGameMenu());

    }

    /**
     * bắt đầu game bằng animation úp hết tất cả lá bài.
     */
    public void getStart() {
        this.isFrontShowing[0] = flipIndex(0);
        this.isFrontShowing[1] = flipIndex(1);
        this.isFrontShowing[2] = flipIndex(2);
        this.isFrontShowing[3] = flipIndex(3);
        this.isFrontShowing[4] = flipIndex(4);
        this.isFrontShowing[5] = flipIndex(5);
        this.isFrontShowing[6] = flipIndex(6);
        this.isFrontShowing[7] = flipIndex(7);
        this.isFrontShowing[8] = flipIndex(8);
        this.isFrontShowing[9] = flipIndex(9);
        this.isFrontShowing[10] = flipIndex(10);
        this.isFrontShowing[11] = flipIndex(11);
        this.isFrontShowing[12] = flipIndex(12);
        this.isFrontShowing[13] = flipIndex(13);
        this.isFrontShowing[14] = flipIndex(14);
        topic.setText(setTopic);
    }

    /**
     * animation lật một card có chỉ số là i.
     * @param i số chỉ của card
     * @return trả về trạng thái sau khi lật
     */
    public boolean flipIndex(int i) {
        StackPane tmp = new StackPane();

        switch (i) {
            case 0:
                tmp = pane0;
                break;
            case 1:
                tmp = pane1;
                break;
            case 2:
                tmp = pane2;
                break;
            case 3:
                tmp = pane3;
                break;
            case 4:
                tmp = pane4;
                break;
            case 5:
                tmp = pane5;
                break;
            case 6:
                tmp = pane6;
                break;
            case 7:
                tmp = pane7;
                break;
            case 8:
                tmp = pane8;
                break;
            case 9:
                tmp = pane9;
                break;
            case 10:
                tmp = pane10;
                break;
            case 11:
                tmp = pane11;
                break;
            case 12:
                tmp = pane12;
                break;
            case 13:
                tmp = pane13;
                break;
            case 14:
                tmp = pane14;
                break;
        }

        RotateTransition cardRotate1 = new RotateTransition(Duration.millis(500), tmp);
        cardRotate1.setAxis(Rotate.Y_AXIS);
        cardRotate1.setFromAngle(0);
        cardRotate1.setToAngle(90);
        cardRotate1.setInterpolator(Interpolator.EASE_OUT);
        cardRotate1.play();
        StackPane finalTmp = tmp;
        cardRotate1.setOnFinished(actionEvent -> {
            if (isFrontShowing[i]) {
                finalTmp.getChildren().get(1).setVisible(true);
                finalTmp.getChildren().get(0).setVisible(false);
            } else {
                finalTmp.getChildren().get(1).setVisible(false);
                finalTmp.getChildren().get(0).setVisible(true);
            }
            RotateTransition cardRotate2 = new RotateTransition(Duration.millis(500), finalTmp);
            cardRotate2.setAxis(Rotate.Y_AXIS);
            cardRotate2.setFromAngle(270);
            cardRotate2.setToAngle(360);
            cardRotate2.setInterpolator(Interpolator.EASE_IN);
            cardRotate2.setOnFinished(actionEvent1 -> {
                if (isFrontShowing[i]) {
                    this.showingCard.add(i);
                    checkTrap();
                    checkShowingCards();
                    checkNumberShowing();
                } else {
                    if (showingCard.contains(i)) {
                        showingCard.remove(findIndex(i, showingCard));
                    }

                }
            });
            cardRotate2.play();
        });
        isFrontShowing[i] = !isFrontShowing[i];
        return isFrontShowing[i];
    }

    /**
     * xóa bỏ một card có số chỉ là a.
     * @param a số chỉ
     */
    public void removeCard(int a) {

        cardRemoved++;
        showingCard.remove(findIndex(a, showingCard));
        if (isFrontShowing[a]) flipIndex(a);

        StackPane tmp = new StackPane();

        switch (a) {
            case 0:
                tmp = pane0;
                break;
            case 1:
                tmp = pane1;
                break;
            case 2:
                tmp = pane2;
                break;
            case 3:
                tmp = pane3;
                break;
            case 4:
                tmp = pane4;
                break;
            case 5:
                tmp = pane5;
                break;
            case 6:
                tmp = pane6;
                break;
            case 7:
                tmp = pane7;
                break;
            case 8:
                tmp = pane8;
                break;
            case 9:
                tmp = pane9;
                break;
            case 10:
                tmp = pane10;
                break;
            case 11:
                tmp = pane11;
                break;
            case 12:
                tmp = pane12;
                break;
            case 13:
                tmp = pane13;
                break;
            case 14:
                tmp = pane14;
                break;
        }
        tmp.setVisible(false);
        if (cardRemoved == 15) {
            cardRemoved = 0;
            countDownTimeLine.stop();
            GameWin();
        }
    }

    /**
     * khởi tạo animation lật
     */
    private RotateTransition createRotator(StackPane pane, boolean isFrontShowing) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), pane);
        rotator.setAxis(Rotate.Y_AXIS);

        if (isFrontShowing) {
            rotator.setFromAngle(0);
            rotator.setToAngle(180);
        } else {
            rotator.setFromAngle(180);
            rotator.setToAngle(360);
        }
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);

        return rotator;
    }

    /**
     * chuyển mặt lá bài khi lật
     */
    private PauseTransition changeCardFace(StackPane pane, boolean isFrontShowing) {
        PauseTransition pause = new PauseTransition(Duration.millis(250));

        if (isFrontShowing) {
            pause.setOnFinished(
                    e -> {
                        pane.getChildren().get(1).setVisible(false);
                        pane.getChildren().get(0).setVisible(true);
                    });
        } else {
            pause.setOnFinished(
                    e -> {
                        pane.getChildren().get(1).setVisible(true);
                        pane.getChildren().get(0).setVisible(false);
                    });
        }

        return pause;
    }

    /**
     * trộn ngẫu nhiên list từ.
     */
    public void makeList() {
        check.clear();
        for (int i = 0; i < 15; i++) {
            this.check.add(i);
        }
        Collections.shuffle(this.check);
    }

    /**
     * tìm chỉ số liên hệ giữa lá bài và từ.
     * @param x số của lá bài
     * @param list list ánh xạ
     * @return index của lá bài trong list
     */
    public int findIndex(int x, List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * kiểm soát số bài đang lật
     */
    public void checkNumberShowing() {
        if (showingCard.size() > 1) {
            int tmp1 = showingCard.get(0);
            int tmp2 = showingCard.get(1);
            isFrontShowing[tmp1] = flipIndex(tmp1);
            isFrontShowing[tmp2] = flipIndex(tmp2);
            showingCard.remove(0);
            showingCard.remove(0);
        }
    }

    /**
     * kiểm tra xem trong số bài đang lật có lá nào khớp nhau không.
     */
    public void checkShowingCards() {

        for (int i = 0; i < showingCard.size(); i++) {
            int tmp = showingCard.get(i);
            int index = findIndex(tmp, check);
            if (index % 2 == 0) {
                if (showingCard.contains(check.get(index + 1))) {
                    removeCard(check.get(index));
                    removeCard(check.get(index + 1));
                }
            } else {
                if (showingCard.contains(check.get(index - 1))) {
                    removeCard(check.get(index));
                    removeCard(check.get(index - 1));
                }
            }
        }
    }

    /**
     * kiểm tra xem lá bài vừa lật có phải là ô bẫy.
     */
    public void checkTrap() {
        int tmp = showingCard.get(showingCard.size() - 1);
        if (tmp == check.get(14)) {
            removeCard(tmp);
            countDownTimeLine.setRate(2);
        }

    }

    /**
     * đọc vào từ file text.
     */
    public void readData() {
        pairs.clear();
        try {
            File data = new File(pathToGamePhuocData);
            Scanner scanner = new Scanner(data);

            while (scanner.hasNextLine()) {
                String pair = scanner.nextLine();
                pairs.add(pair);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * chia đôi từ thành tiếng anh và tiếng việt.
     */
    public void splitPairs() {
        Collections.shuffle(pairs);
        list.clear();
        for (int i = 0; i < 7; i++) {
            String[] tmp = pairs.get(i).split("\\.");
            list.add(tmp[0]);
            list.add(tmp[1]);
        }
    }
    /**
     * cài đặt thời gian đếm ngược
     */
    private final ObjectProperty<java.time.Duration> remainingDuration
            = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(90));
    /**
     * cài đặt dòng thời gian.
     */
    private final Timeline countDownTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
            remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));
    private int cardRemoved = 0;
    private final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    /**
     * ánh xạ giữa số chỉ từ trong list và số chỉ của card.
     */
    public void addWord() {
        for (int i = 0; i < 14; i++) {
            int tmp = check.get(i);
            add(tmp, list.get(i));
        }
        add(check.get(14), trap);
    }

    /**
     * chuyển từ vào các card sau khi đã ánh xạ.
     * @param x số chỉ của card
     * @param word từ được chuyển vào
     */
    public void add(int x, String word) {
        switch (x) {
            case 0:
                word0.setText(word);
                break;
            case 1:
                word1.setText(word);
                break;
            case 2:
                word2.setText(word);
                break;
            case 3:
                word3.setText(word);
                break;
            case 4:
                word4.setText(word);
                break;
            case 5:
                word5.setText(word);
                break;
            case 6:
                word6.setText(word);
                break;
            case 7:
                word7.setText(word);
                break;
            case 8:
                word8.setText(word);
                break;
            case 9:
                word9.setText(word);
                break;
            case 10:
                word10.setText(word);
                break;
            case 11:
                word11.setText(word);
                break;
            case 12:
                word12.setText(word);
                break;
            case 13:
                word13.setText(word);
                break;
            case 14:
                word14.setText(word);
                break;


        }
    }

    /**
     * thông báo thua.
     */
    public void gameOver() {
        alert.setTitle("GameOver");
        alert.setHeaderText("Do you want to play again?");
        alert.getButtonTypes().clear();
        ButtonType again = new ButtonType("Play Again");
        ButtonType quit = new ButtonType("Quit Game");
        alert.getButtonTypes().addAll(again, quit);
        alert.setOnHidden(e -> choiceOver());
        if(Model.getInstance().getViewFactory().selectedMenuProperty().get().equals("MemoriesPlay"))
        {
            alert.show();
        }
    }

    /**
     * lựa chọn khi thua.
     */
    public void choiceOver() {
        if (alert.getResult() == alert.getButtonTypes().get(0)) {
            resetNew();
        } else {
            onGameMenu();
        }
    }

    /**
     * thông báo thắng.
     */
    public void GameWin() {
        countDownTimeLine.pause();
        alert.setTitle("Win!");
        alert.setHeaderText("Congratulation,you won! Do you want to play agian?");
        alert.getButtonTypes().clear();
        ButtonType again = new ButtonType("Play Again");
        ButtonType quit = new ButtonType("Quit Game");
        alert.getButtonTypes().addAll(again, quit);
        alert.show();
        alert.setOnHidden(e -> ChoiceWin());
    }

    /**
     * lựa chọn khi thắng.
     */
    public void ChoiceWin() {
        if (alert.getResult() == alert.getButtonTypes().get(0)) {
            resetNew();
        } else {
            onGameMenu();
        }
    }

    /**
     * thoát ra menu game.
     */
    private void onGameMenu() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesMenu");
        countDownTimeLine.stop();
    }

    /**
     * reset game mới.
     */
    public void resetNew() {
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesMenu");
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesPlay");
        countDownTimeLine.stop();
    }
}