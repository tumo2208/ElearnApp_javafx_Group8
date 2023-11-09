package dictionary.elearnapp_javafx_group8.Dictionary;

public class Word {

    private String wordTarget;
    private String wordExplain;
    private boolean isSaved;

    public Word(String wordTarget, String wordExplain) {
        this.setWordTarget(wordTarget);
        this.setWordExplain(wordExplain);
        isSaved = false;
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

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Word newWord = (Word) obj;
        return this.getWordExplain().equals(newWord.getWordExplain())
                && this.getWordTarget().equals(newWord.getWordTarget());
    }
}
