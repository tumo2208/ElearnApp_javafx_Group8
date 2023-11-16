package dictionary.elearnapp_javafx_group8.Dictionary;

import java.util.List;

public class History extends Dictionary {
    @Override
    public void addWord(Word word, String path) {
        super.addWord(word, path);
    }

    @Override
    public void deleteWord(List<Word> wordList, int index, String path) {
        try {
            wordList.remove(index);
            this.exportToFile(wordList, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWord(List<Word> wordList, int index, String wordExplain, String path) {
        super.updateWord(wordList, index, wordExplain, path);
    }

    @Override
    public int Searcher(List<Word> wordList, String word) {
        for (int i = 0; i < wordList.size(); ++i) {
            if (wordList.get(i).getWordTarget().equals(word)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insertFromFile(List<Word> wordList, String path) {
        super.insertFromFile(wordList, path);
    }

    @Override
    public void exportToFile(List<Word> wordList, String path) {
        super.exportToFile(wordList, path);
    }
}
