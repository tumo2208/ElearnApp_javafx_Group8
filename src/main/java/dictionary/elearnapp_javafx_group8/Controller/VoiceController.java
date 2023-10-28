package dictionary.elearnapp_javafx_group8.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VoiceController {

    public static final String path="src/main/resources/Database/voice.vbs";
    private int itemVoice;
    private int rate;
    private int volume;

    /**
     * Trong trường hợp muốn khởi tạo một voice mới theo ý muốn.
     * @param itemVoice 1 là giọng nữ, 0 là giọng nam
     * @param rate tốc độ đọc
     * @param volume âm lượng
     */

    public VoiceController(int itemVoice,int rate,int volume){
        this.itemVoice=itemVoice;
        this.rate=rate;
        this.volume=volume;
    }

    /**
     * Khởi tạo mặc định.
     */

    public VoiceController() {
        this.itemVoice=1;
        this.rate=1;
        this.volume=70;
    }

    public void makeSound(String word){
        File file=new File(path);
        String setupVoice="Dim Speaker\n" +
                "\n" +
                "\n" +
                "Set Speaker = CreateObject(\"SAPI.spVoice\")\n" +
                "Set Speaker.Voice = Speaker.GetVoices.Item("+this.itemVoice+")\n" +
                "Speaker.Rate = "+this.rate+"\n" +
                "Speaker.Volume ="+this.volume+"\n" +
                "\n" +
                "Speaker.Speak \"" + word + "\"";
        try {
            FileWriter wr=new FileWriter(file,false);
            wr.write(setupVoice);
            wr.close();
        } catch (IOException e){
            System.out.println("IO ex");
        }

        try {
            Runtime.getRuntime().exec( "wscript "+path );
        }
        catch(IOException e ) {
            System.out.println(e);
            System.exit(0);
        }
        }

    }

