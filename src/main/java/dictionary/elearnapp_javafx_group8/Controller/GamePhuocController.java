package dictionary.elearnapp_javafx_group8.Controller;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import java.util.concurrent.TimeUnit;

public class GamePhuocController implements Initializable {
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

    boolean [] isFrontShowing =new boolean[15];


    private Image frontImage= new Image(getClass().getResourceAsStream("/Database/PhuocData/frontSide.jpg"));
    private ObjectProperty<java.time.Duration> remainingDuration = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(120));
    public static final String path="src/main/resources/Database/PhuocData/Animal.txt";
    public static final String trap="x2 Time Speed";
    private List<String> pairs=new ArrayList<>();
    private List<String> list=new ArrayList<>();
    private List<Integer> check=new ArrayList<>();
    private List<Integer> showingCard=new ArrayList<>();
    Timeline countDownTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) ->
            remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));
    private int cardRemoved=0;
    public void addWord(){
        for(int i=0;i<14;i++){
            int tmp=check.get(i);
            add(tmp,list.get(i));
        }
        add(check.get(14),trap);
    }
    // đang làm đến radom thứ tự các ô bài, cần làm tiếp switch case.
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
        countDownTimeLine.setOnFinished(event -> new Alert(Alert.AlertType.INFORMATION).show());
        //countDownTimeLine.setRate(2);
        countDownTimeLine.play();

        pane0.setOnMouseClicked((t) -> {
            //this.isFrontShowing[0]=flip(pane0,isFrontShowing[0]);

            this.isFrontShowing[0]=flipIndex(0);


        });
        pane1.setOnMouseClicked((t) -> {
            this.isFrontShowing[1]=flip(pane1,isFrontShowing[1]);
            if(isFrontShowing[1]) {
                this.showingCard.add(1);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(1));
            }
        });
        pane2.setOnMouseClicked((t) -> {
            this.isFrontShowing[2]=flip(pane2,isFrontShowing[2]);
            if(isFrontShowing[2]) {
                this.showingCard.add(2);
                checkTrap();
                checkNumberShowing();
                checkShowingCards();
            }
            else {
                this.showingCard.remove(findIndex(2));
            }
        });
        pane3.setOnMouseClicked((t) -> {
            this.isFrontShowing[3]=flip(pane3,isFrontShowing[3]);
            if(isFrontShowing[3]) {
                this.showingCard.add(3);
                checkTrap();
                checkNumberShowing();
                checkShowingCards();
            }
            else {
                this.showingCard.remove(findIndex(3));
            }
        });
        pane4.setOnMouseClicked((t) -> {
            this.isFrontShowing[4]=flip(pane4,isFrontShowing[4]);
            if(isFrontShowing[4]) {
                this.showingCard.add(4);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(4));
            }
        });
        pane5.setOnMouseClicked((t) -> {
            this.isFrontShowing[5]=flip(pane5,isFrontShowing[5]);
            if(isFrontShowing[5]) {
                this.showingCard.add(5);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(5));
            }
        });
        pane6.setOnMouseClicked((t) -> {
            this.isFrontShowing[6]=flip(pane6,isFrontShowing[6]);
            if(isFrontShowing[6]) {
                this.showingCard.add(6);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(6));
            }
        });
        pane7.setOnMouseClicked((t) -> {
            this.isFrontShowing[7]=flip(pane7,isFrontShowing[7]);
            if(isFrontShowing[7]) {
                this.showingCard.add(7);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(7));
            }
        });
        pane8.setOnMouseClicked((t) -> {
            this.isFrontShowing[8]=flip(pane8,isFrontShowing[8]);
            if(isFrontShowing[8]) {
                this.showingCard.add(8);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(8));
            }
        });
        pane9.setOnMouseClicked((t) -> {
            this.isFrontShowing[9]=flip(pane9,isFrontShowing[9]);
            if(isFrontShowing[9]) {
                this.showingCard.add(9);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(9));
            }
        });
        pane10.setOnMouseClicked((t) -> {
            this.isFrontShowing[10]=flip(pane10,isFrontShowing[10]);
            if(isFrontShowing[10]) {
                this.showingCard.add(10);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(10));
            }
        });
        pane11.setOnMouseClicked((t) -> {
            this.isFrontShowing[11]=flip(pane11,isFrontShowing[11]);
            if(isFrontShowing[11]) {
                this.showingCard.add(11);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(11));
            }
        });
        pane12.setOnMouseClicked((t) -> {
            this.isFrontShowing[12]=flip(pane12,isFrontShowing[12]);
            if(isFrontShowing[12]) {
                this.showingCard.add(12);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(12));
            }
        });
        pane13.setOnMouseClicked((t) -> {
            this.isFrontShowing[13]=flip(pane13,isFrontShowing[13]);
            if(isFrontShowing[13]) {
                this.showingCard.add(13);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(13));
            }
        });
        pane14.setOnMouseClicked((t) -> {
            this.isFrontShowing[14]=flip(pane14,isFrontShowing[14]);
            if(isFrontShowing[14]) {
                this.showingCard.add(14);
                checkTrap();
                checkShowingCards();
                checkNumberShowing();
            }
            else {
                this.showingCard.remove(findIndex(14));
            }
        });

    }
    public void getStart(){
        this.isFrontShowing[0]=flip(this.pane0,this.isFrontShowing[0]);
        this.isFrontShowing[1]=flip(this.pane1,this.isFrontShowing[1]);
        this.isFrontShowing[2]=flip(this.pane2,this.isFrontShowing[2]);
        this.isFrontShowing[3]=flip(this.pane3,this.isFrontShowing[3]);
        this.isFrontShowing[4]=flip(this.pane4,this.isFrontShowing[4]);
        this.isFrontShowing[5]=flip(this.pane5,this.isFrontShowing[5]);
        this.isFrontShowing[6]=flip(this.pane6,this.isFrontShowing[6]);
        this.isFrontShowing[7]=flip(this.pane7,this.isFrontShowing[7]);
        this.isFrontShowing[8]=flip(this.pane8,this.isFrontShowing[8]);
        this.isFrontShowing[9]=flip(this.pane9,this.isFrontShowing[9]);
        this.isFrontShowing[10]=flip(this.pane10,this.isFrontShowing[10]);
        this.isFrontShowing[11]=flip(this.pane11,this.isFrontShowing[11]);
        this.isFrontShowing[12]=flip(this.pane12,this.isFrontShowing[12]);
        this.isFrontShowing[13]=flip(this.pane13,this.isFrontShowing[13]);
        this.isFrontShowing[14]=flip(this.pane14,this.isFrontShowing[14]);
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
                    this.showingCard.remove(findIndex(i));
                }}
        );
        return !isFrontShowing[i];
    }
    public void removeCard(int a)  {

        cardRemoved++;
        showingCard.remove(findIndex(a));
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
        for(int i=0;i<15;i++){
            this.check.add(i);
        }
        Collections.shuffle(this.check);
    }
    public int findIndex(int x){
        for(int i=0;i<2;i++){
            if(this.showingCard.get(i)==x){
                return i;
            }
        }
        return 0;
    }
    public void checkNumberShowing(){
        if(showingCard.size()>2){
            int tmp=showingCard.get(0);
            isFrontShowing[tmp]=flipIndex(tmp);
            showingCard.remove(0);
        }
    }
    public void checkShowingCards(){

        for(int i=0;i<showingCard.size();i++){
            int tmp=showingCard.get(i);
            if(tmp%2==0){
                if(showingCard.contains(tmp+1)){
                    removeCard(tmp);
                    removeCard(tmp+1);
                }
            }
            else {
                if(showingCard.contains(tmp-1)){
                    removeCard(tmp);
                    removeCard(tmp-1);
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
            File data=new File(path);
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
        for(int i=0;i<7;i++){
            String[]tmp=pairs.get(i).split("\\.");
            list.add(tmp[0]);
            list.add(tmp[1]);
        }
    }

}
