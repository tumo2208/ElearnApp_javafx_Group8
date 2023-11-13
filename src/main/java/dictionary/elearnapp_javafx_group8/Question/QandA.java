package dictionary.elearnapp_javafx_group8.Question;

public class QandA {
    private String question;
    private String answer;

    public QandA(String question, String answer) {
        this.setAnswer(answer);
        this.setQuestion(question);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
