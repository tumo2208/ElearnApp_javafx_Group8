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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MemoriesController implements Initializable {
    public Button Phong;

    public Button Phuoc;

    public Button Tu;

    public Label time;


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

    boolean [] isFrontShowing =new boolean[15];


    private Image frontImage= new Image(getClass().getResourceAsStream("/Images/GamePhuoc/frontSide.jpg"));
    private ObjectProperty<java.time.Duration> remainingDuration
            = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(120));
    public static String pathToGamePhuocData;// ="src/main/resources/Database/GamePhuoc/Animal.txt";
    public static final String trap="x2 Time Speed";
    private List<String> pairs=new ArrayList<>();
    private List<String> list=new ArrayList<>();
    private List<Integer> check=new ArrayList<>();
    private List<Integer> showingCard=new ArrayList<>();
    private Timeline countDownTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
            remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));
    private int cardRemoved=0;
    private Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
    public void addWord(){
        for(int i=0;i<14;i++){
            int tmp=check.get(i);
            add(tmp,list.get(i));
        }
        add(check.get(14),trap);
    }

    public void add(int x,String word){
        switch (x){
            case 0: word0.setText(word);
                break;
            case 1: word1.setText(word);
                break;
            case 2: word2.setText(word);
                break;
            case 3: word3.setText(word);
                break;
            case 4: word4.setText(word);
                break;
            case 5: word5.setText(word);
                break;
            case 6: word6.setText(word);
                break;
            case 7: word7.setText(word);
                break;
            case 8: word8.setText(word);
                break;
            case 9: word9.setText(word);
                break;
            case 10: word10.setText(word);
                break;
            case 11: word11.setText(word);
                break;
            case 12: word12.setText(word);
                break;
            case 13: word13.setText(word);
                break;
            case 14: word14.setText(word);
                break;


        }
    }
    public void gameOver(){
        alert.setTitle("GameOver");
        alert.setHeaderText("Do you want to play again?");
        alert.getButtonTypes().clear();
        ButtonType again=new ButtonType("Play Again");
        ButtonType quit=new ButtonType("Quit Game");
        alert.getButtonTypes().addAll(again,quit);
        alert.show();
        alert.setOnHidden(e-> choiceOver());
    }
    public void choiceOver(){
        if (alert.getResult()==alert.getButtonTypes().get(0)){
            resetNew();
        } else {
            onGameMenu();
        }
    }
    public void GameWin(){
        alert.setTitle("Win!");
        alert.setHeaderText("Congratulation,you won! Do you want to play agian?");
        alert.getButtonTypes().clear();
        ButtonType again=new ButtonType("Play Again");
        ButtonType quit=new ButtonType("Quit Game");
        alert.getButtonTypes().addAll(again,quit);
        alert.show();
        alert.setOnHidden(e->ChoiceWin());
    }
    public void ChoiceWin(){
        if (alert.getResult()==alert.getButtonTypes().get(0)){
            resetNew();
        } else {
            onGameMenu();
        }
    }
    private void onGameMenu(){
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesMenu");
        countDownTimeLine.stop();
    }
    public void resetNew(){
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesMenu");
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesPlay");
        countDownTimeLine.stop();
    }
    private void onGamePlay(){
        Model.getInstance().getViewFactory().selectedMenuProperty().set("MemoriesPlay");
    }

    public void reset(){
        countDownTimeLine=new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
                remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));
        countDownTimeLine.setRate(1);
        splitPairs();
        makeList();
        addWord();
        for (int i=0;i<15;i++){
            if(isFrontShowing[i]=false){
                isFrontShowing[i]=flipIndex(i);
            }
        }
        for (int i=0;i<15;i++){
            isFrontShowing[i]=true;
        }
        pane0.setVisible(true);
        pane1.setVisible(true);
        pane2.setVisible(true);
        pane3.setVisible(true);
        pane4.setVisible(true);
        pane5.setVisible(true);
        pane6.setVisible(true);
        pane7.setVisible(true);
        pane8.setVisible(true);
        pane9.setVisible(true);
        pane10.setVisible(true);
        pane11.setVisible(true);
        pane12.setVisible(true);
        pane13.setVisible(true);
        pane14.setVisible(true);
        getStart();
        remainingDuration=new SimpleObjectProperty<>(java.time.Duration.ofSeconds(120));
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
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i=0;i<15;i++){
            isFrontShowing[i]=true;
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

        pane0.setOnMouseClicked((t) -> {
            this.isFrontShowing[0]=flipIndex(0);
        });
        pane1.setOnMouseClicked((t) -> {
            this.isFrontShowing[1]=flipIndex(1);

        });
        pane2.setOnMouseClicked((t) -> {
            this.isFrontShowing[2]=flipIndex(2);
        });
        pane3.setOnMouseClicked((t) -> {
            this.isFrontShowing[3]=flipIndex(3);
        });
        pane4.setOnMouseClicked((t) -> {
            this.isFrontShowing[4]=flipIndex(4);
        });
        pane5.setOnMouseClicked((t) -> {
            this.isFrontShowing[5]=flipIndex(5);
        });
        pane6.setOnMouseClicked((t) -> {
            this.isFrontShowing[6]=flipIndex(6);
        });
        pane7.setOnMouseClicked((t) -> {
            this.isFrontShowing[7]=flipIndex(7);
        });
        pane8.setOnMouseClicked((t) -> {
            this.isFrontShowing[8]=flipIndex(8);
        });
        pane9.setOnMouseClicked((t) -> {
            this.isFrontShowing[9]=flipIndex(9);
        });
        pane10.setOnMouseClicked((t) -> {
            this.isFrontShowing[10]=flipIndex(10);
        });
        pane11.setOnMouseClicked((t) -> {
            this.isFrontShowing[11]=flipIndex(11);
        });
        pane12.setOnMouseClicked((t) -> {
            this.isFrontShowing[12]=flipIndex(12);
        });
        pane13.setOnMouseClicked((t) -> {
            this.isFrontShowing[13]=flipIndex(13);
        });
        pane14.setOnMouseClicked((t) -> {
            this.isFrontShowing[14]=flipIndex(14);
        });
        Back.setOnMouseClicked(e->onGameMenu());

    }
    public void getStart(){
        this.isFrontShowing[0]=flipIndex(0);
        this.isFrontShowing[1]=flipIndex(1);
        this.isFrontShowing[2]=flipIndex(2);
        this.isFrontShowing[3]=flipIndex(3);
        this.isFrontShowing[4]=flipIndex(4);
        this.isFrontShowing[5]=flipIndex(5);
        this.isFrontShowing[6]=flipIndex(6);
        this.isFrontShowing[7]=flipIndex(7);
        this.isFrontShowing[8]=flipIndex(8);
        this.isFrontShowing[9]=flipIndex(9);
        this.isFrontShowing[10]=flipIndex(10);
        this.isFrontShowing[11]=flipIndex(11);
        this.isFrontShowing[12]=flipIndex(12);
        this.isFrontShowing[13]=flipIndex(13);
        this.isFrontShowing[14]=flipIndex(14);

    }
    public boolean flip(StackPane pane, boolean isFrontShowing){
        RotateTransition rotator = createRotator(pane,isFrontShowing);
        PauseTransition ptChangeCardFace = changeCardFace(pane,isFrontShowing);
        ParallelTransition parallelTransition = new ParallelTransition(rotator, ptChangeCardFace);
        parallelTransition.play();
        return !isFrontShowing;
    }
    public boolean flipIndex(int i){
        StackPane tmp=new StackPane();

        switch (i){
            case 0: tmp=pane0;
                break;
            case 1: tmp=pane1;
                break;
            case 2:tmp=pane2;
                break;
            case 3:tmp=pane3;
                break;
            case 4:tmp=pane4;
                break;
            case 5:tmp=pane5;
                break;
            case 6:tmp=pane6;
                break;
            case 7:tmp=pane7;
                break;
            case 8:tmp=pane8;
                break;
            case 9:tmp=pane9;
                break;
            case 10:tmp=pane10;
                break;
            case 11:tmp=pane11;
                break;
            case 12:tmp=pane12;
                break;
            case 13:tmp=pane13;
                break;
            case 14:tmp=pane14;
                break;
        }
        RotateTransition rotator = createRotator(tmp,isFrontShowing[i]);
        PauseTransition ptChangeCardFace = changeCardFace(tmp,isFrontShowing[i]);
        isFrontShowing[i]=!isFrontShowing[i];
        ParallelTransition parallelTransition = new ParallelTransition(rotator, ptChangeCardFace);
        parallelTransition.play();
        parallelTransition.setOnFinished(
                e->{if(isFrontShowing[i]) {
                    this.showingCard.add(i);
                    checkTrap();
                    checkShowingCards();
                    checkNumberShowing();
                }
                else {
                    if(showingCard.contains(i)){
                        showingCard.remove(findIndex(i,showingCard));
                    }

                }}
        );
        return isFrontShowing[i];
    }
    public void removeCard(int a)  {

        cardRemoved++;
        showingCard.remove(findIndex(a,showingCard));
        if(isFrontShowing[a]) flipIndex(a);

        StackPane tmp=new StackPane();

        switch (a){
            case 0: tmp=pane0;
                break;
            case 1: tmp=pane1;
                break;
            case 2:tmp=pane2;
                break;
            case 3:tmp=pane3;
                break;
            case 4:tmp=pane4;
                break;
            case 5:tmp=pane5;
                break;
            case 6:tmp=pane6;
                break;
            case 7:tmp=pane7;
                break;
            case 8:tmp=pane8;
                break;
            case 9:tmp=pane9;
                break;
            case 10:tmp=pane10;
                break;
            case 11:tmp=pane11;
                break;
            case 12:tmp=pane12;
                break;
            case 13:tmp=pane13;
                break;
            case 14:tmp=pane14;
                break;
        }
        tmp.setVisible(false);
        if (cardRemoved==15){
            cardRemoved=0;
            GameWin();
            countDownTimeLine.playFromStart();
        }
    }
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
    public void makeList(){
        check.clear();
        for(int i=0;i<15;i++){
            this.check.add(i);
        }
        Collections.shuffle(this.check);
    }
    public int findIndex(int x,List<Integer> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i)==x){
                return i;
            }
        }
        return -1;
    }
    public void checkNumberShowing(){
        if(showingCard.size()>1){
            int tmp=showingCard.get(0);
            isFrontShowing[tmp]=flipIndex(tmp);
            showingCard.remove(0);
        }
    }
    public void checkShowingCards(){

        for(int i=0;i<showingCard.size();i++){
            int tmp=showingCard.get(i);
            int index=findIndex(tmp,check);
            if(index%2==0){
                if(showingCard.contains(check.get(index+1))){
                    removeCard(check.get(index));
                    removeCard(check.get(index+1));
                }
            }
            else {
                if(showingCard.contains(check.get(index-1))){
                    removeCard(check.get(index));
                    removeCard(check.get(index-1));
                }
            }
        }
    }
    public void checkTrap(){
        int tmp=showingCard.get(showingCard.size()-1);
        if(tmp==check.get(14)){
            removeCard(tmp);
            countDownTimeLine.setRate(2);
        }

    }


    public void readData(){
        try {
            File data=new File(pathToGamePhuocData);
            Scanner scanner=new Scanner(data);

            while (scanner.hasNextLine()){
                String pair=scanner.nextLine();
                pairs.add(pair);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void splitPairs(){
        Collections.shuffle(pairs);
        list.clear();
        for(int i=0;i<7;i++){
            String[]tmp=pairs.get(i).split("\\.");
            list.add(tmp[0]);
            list.add(tmp[1]);
        }
    }
}
