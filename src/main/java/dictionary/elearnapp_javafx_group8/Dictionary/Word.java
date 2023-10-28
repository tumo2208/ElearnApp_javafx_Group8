package dictionary.elearnapp_javafx_group8.Dictionary;

public class Word {

    private String wordTarget;
    private String wordExplain;

    public Word() {
        this.setWordTarget("");
        this.setWordExplain("");
    }

    public Word(String wordTarget, String wordExplain) {
        this.setWordTarget(wordTarget);
        this.setWordExplain(wordExplain);
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj.getClass().equals(this.getClass()))) return false;
        Word newWord = (Word) obj;
        if (this == newWord) return true;
        return this.getWordExplain().equals(newWord.getWordExplain())
                && this.getWordTarget().equals(newWord.getWordTarget());
    }
}