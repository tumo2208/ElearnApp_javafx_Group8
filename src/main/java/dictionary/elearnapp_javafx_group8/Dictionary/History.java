package dictionary.elearnapp_javafx_group8.Dictionary;

import java.util.ArrayList;
import java.util.Comparator;
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
    public int Searcher(List<Word> wordList, String word) {
        List<Word> historyList = new ArrayList<>(wordList);
        historyList.sort(Comparator.comparing(Word::getWordTarget));
        int l = 0;
        int r = historyList.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (historyList.get(m).getWordTarget().equals(word)) {
                return m;
            } else if (historyList.get(m).getWordTarget().compareTo(word) < 0) {
                l = m + 1;
            } else {
                r = m - 1;
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
