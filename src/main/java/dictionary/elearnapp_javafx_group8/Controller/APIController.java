package dictionary.elearnapp_javafx_group8.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class APIController implements Initializable {

    public ImageView flag1;
    public Label firstLanguage;
    public ImageView flag2;
    public Label secondLanguage;
    public TextArea firstLanguageArea;
    public TextArea secondLanguageArea;
    public Button switchButton;
    public Button translateButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            vietnamFlag = new Image(getClass().getResource("/Images/vietnameseFlag.png").toString());
            englandFlag = new Image(getClass().getResource("/Images/englishFlag.png").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        translateButton.setDisable(true);
        secondLanguageArea.setEditable(false);
        firstLanguage.setText("English");
        secondLanguage.setText("Vietnamese");
        firstLanguageArea.setOnKeyTyped(keyEvent -> translateButton.setDisable(firstLanguageArea.getText().isEmpty()));
        flag1.setImage(englandFlag);
        flag2.setImage(vietnamFlag);

        switchButton.setOnAction(event -> {
            firstLanguageArea.clear();
            secondLanguageArea.clear();
            translateButton.setDisable(true);
            if (sourceLanguage.equals("en")) {
                sourceLanguage = "vi";
                toLanguage = "en";
                firstLanguage.setText("Vietnamese");
                secondLanguage.setText("English");
                flag1.setImage(vietnamFlag);
                flag2.setImage(englandFlag);
            } else {
                sourceLanguage = "en";
                toLanguage = "vi";
                firstLanguage.setText("English");
                secondLanguage.setText("Vietnamese");
                flag1.setImage(englandFlag);
                flag2.setImage(vietnamFlag);
            }
        });

        translateButton.setOnAction(event -> {
            try {
                googleTranslate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void googleTranslate() throws IOException {
        String rootAPI = "https://translate.googleapis.com/translate_a/single?client=gtx"
                + "&sl=" + sourceLanguage
                + "&tl="+ toLanguage
                +"&dt=t&q=";
        String textToTranslate = firstLanguageArea.getText();
        String URLString = rootAPI + textToTranslate;
        URLString = URLString.replace(" ", "%20");

        URL url = new URL(URLString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream stream;
        if(httpURLConnection.getResponseCode() == 200){
            stream = httpURLConnection.getInputStream();
        }else{
            stream = httpURLConnection.getErrorStream();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String line;
        String translateText = "";
        int cnt = 0;
        while ((line = in.readLine()) != null && cnt < 2) {
            for (char c : line.toCharArray()) {
                if (cnt == 1) {
                    translateText += c;
                }
                if (c == '"') {
                    cnt++;
                }
            }
        }
        in.close();
        httpURLConnection.disconnect();

        translateText = translateText.substring(0, translateText.length() - 1);
        secondLanguageArea.setText(translateText);
    }

    private String sourceLanguage = "en";
    private String toLanguage = "vi";
    private Image vietnamFlag;
    private Image englandFlag;
}
