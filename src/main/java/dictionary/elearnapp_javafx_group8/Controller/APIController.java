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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            vietnamFlag = new Image(getClass().getResource("/Images/vietnameseFlag.png").toString());
            englandFlag = new Image(getClass().getResource("/Images/englishFlag.png").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        secondLanguageArea.setEditable(false);
        resetAllAPI();
        firstLanguageArea.setOnKeyTyped(keyEvent -> {
            secondLanguageArea.clear();
            translateButton.setDisable(firstLanguageArea.getText().isEmpty());
        });

        switchButton.setOnAction(event -> {
            firstLanguageArea.clear();
            secondLanguageArea.clear();
            translateButton.setDisable(true);
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
                googleTranslate();
            } catch (Exception e) {
                secondLanguageArea.setText("ĐỤ MÁ MÀY NHẬP CÁI CHÓ GÌ VẬY???");
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
        String URLString = rootAPI + URLEncoder.encode(textToTranslate,"UTF-8");
        URLString = URLString.replace(" ", "%20");
//        URLString = URLString.replace("\n", "%0A");
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
        int cnt = 0;
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
}