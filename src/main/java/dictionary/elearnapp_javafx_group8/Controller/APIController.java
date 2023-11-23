package dictionary.elearnapp_javafx_group8.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.net.URLEncoder;

public class APIController implements Initializable {

    public ImageView flag1;
    public Label firstLanguage;
    public ImageView flag2;
    public Label secondLanguage;
    public TextArea firstLanguageArea;
    public TextArea secondLanguageArea;
    public Button switchButton;
    public Button translateButton;
    public Label limitLetters;
    public ImageView loadingImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            vietnamFlag = new Image(getClass().getResource("/Images/vietnameseFlag.png").toString());
            englandFlag = new Image(getClass().getResource("/Images/englishFlag.png").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        firstLanguageArea.setWrapText(true);
        secondLanguageArea.setWrapText(true);
        limitLetters.setText("0 / 2000");
        limitLetters.setAlignment(Pos.CENTER_RIGHT);
        limitLetters.setTextAlignment(TextAlignment.RIGHT);
        limitLetters.setWrapText(true);
        secondLanguageArea.setEditable(false);
        loadingImg.setVisible(false);
        resetAllAPI();

        firstLanguageArea.setOnKeyTyped(keyEvent -> {
            secondLanguageArea.clear();
            translateButton.setDisable(firstLanguageArea.getText().isEmpty());
            limitLetters.setText(firstLanguageArea.getText().length() + " / 2000");
        });

        switchButton.setOnAction(event -> {
            firstLanguageArea.clear();
            secondLanguageArea.clear();
            translateButton.setDisable(true);
            loadingImg.setVisible(false);
            if (sourceLanguage.equals("en")) {
                sourceLanguage = "vi";
                toLanguage = "en";
                firstLanguage.setText("VIETNAMESE");
                secondLanguage.setText("ENGLISH");
                flag1.setImage(vietnamFlag);
                flag2.setImage(englandFlag);
            } else {
                sourceLanguage = "en";
                toLanguage = "vi";
                firstLanguage.setText("ENGLISH");
                secondLanguage.setText("VIETNAMESE");
                flag1.setImage(englandFlag);
                flag2.setImage(vietnamFlag);
            }
        });

        translateButton.setOnAction(event -> {
            try {
                if (firstLanguageArea.getText().length() <= 2000) {
                    googleTranslate();
                    loadingImg.setVisible(true);
                    secondLanguageArea.setVisible(false);
                    countDownTimeLine.setOnFinished(event1 -> {
                        loadingImg.setVisible(false);
                        secondLanguageArea.setVisible(true);
                    });
                    countDownTimeLine.play();
                } else {
                    secondLanguageArea.setText("ERROR TO TRANSLATE");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            translateButton.setDisable(true);
        });
    }

    private void googleTranslate() throws IOException {
        String rootAPI = "https://translate.googleapis.com/translate_a/single?client=gtx"
                + "&sl=" + sourceLanguage
                + "&tl=" + toLanguage
                + "&dt=t&q=";
        String textToTranslate = firstLanguageArea.getText();
        textToTranslate=textToTranslate.replace("\"", "\'\'");
        textToTranslate=textToTranslate.replace("],[","], [");
        String URLString = rootAPI + URLEncoder.encode(textToTranslate,"UTF-8");
        URLString = URLString.replace(" ", "%20");
        URL url = new URL(URLString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream stream;
        if (httpURLConnection.getResponseCode() == 200) {
            stream = httpURLConnection.getInputStream();
        } else {
            stream = httpURLConnection.getErrorStream();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        String translateText = "";
        line = in.readLine();
        line = line.replace("824257d7a249c58caeea06a2e64a25bd", "");
        line = line.replace("07cf6d00dc83dfc068bf115b58e01f7f", "");
        for (String str : line.split("\\],\\[")) {
            if (str.contains("\"")) {
                String[] outs = str.split("\"");
                translateText += outs[1];
            }
        }
        in.close();
        httpURLConnection.disconnect();
        translateText = translateText.translateEscapes();
        translateText=translateText.replace("], [","],[");
        secondLanguageArea.setText(translateText);
    }

    private void resetAllAPI() {
        translateButton.setDisable(true);
        firstLanguage.setText("ENGLISH");
        secondLanguage.setText("VIETNAMESE");
        flag1.setImage(englandFlag);
        flag2.setImage(vietnamFlag);
        firstLanguageArea.clear();
        secondLanguageArea.clear();
    }

    private String sourceLanguage = "en";
    private String toLanguage = "vi";
    private Image vietnamFlag;
    private Image englandFlag;
    private final ObjectProperty<Duration> remainingDuration
            = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(5));
    private final Timeline countDownTimeLine = new Timeline(new KeyFrame(javafx.util.Duration.seconds(5),
            (ActionEvent event) -> remainingDuration.setValue(remainingDuration.get().minus(1, ChronoUnit.SECONDS))));
}