package dictionary.elearnapp_javafx_group8.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VoiceController {

    private static final String path = "src/main/resources/Database/voice.vbs";
    private final int itemVoice;
    private final int rate;
    private final int volume;

    public VoiceController(int itemVoice, int rate, int volume) {
        this.itemVoice = itemVoice;
        this.rate = rate;
        this.volume = volume;
    }

    public VoiceController() {
        this.itemVoice = 1;
        this.rate = 1;
        this.volume = 70;
    }

    public void makeSound(String word) {
        File file = new File(path);
        String setupVoice = "Dim Speaker\n" +
                "\n" +
                "\n" +
                "Set Speaker = CreateObject(\"SAPI.spVoice\")\n" +
                "Set Speaker.Voice = Speaker.GetVoices.Item(" + this.itemVoice + ")\n" +
                "Speaker.Rate = " + this.rate + "\n" +
                "Speaker.Volume =" + this.volume + "\n" +
                "\n" +
                "Speaker.Speak \"" + word + "\"";
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(setupVoice);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Runtime.getRuntime().exec("wscript " + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}