package dictionary.elearnapp_javafx_group8.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionCatchWord {
    private String answer;
    private List<QandA> qanda = new ArrayList<>();

    public QuestionCatchWord(String answer, List<QandA> qanda) {
        this.answer = answer;
        this.qanda = new ArrayList<>(qanda);
    }

    public List<QandA> getQanda() {
        return qanda;
    }

    public String getAnswer() {
        return answer;
    }
}