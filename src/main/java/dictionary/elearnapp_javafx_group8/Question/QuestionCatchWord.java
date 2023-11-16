package dictionary.elearnapp_javafx_group8.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionCatchWord {
    private final String answer;
    private final List<QandA> qanda;

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
