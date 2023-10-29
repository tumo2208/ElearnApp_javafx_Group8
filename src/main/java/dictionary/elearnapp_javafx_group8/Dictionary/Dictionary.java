package dictionary.elearnapp_javafx_group8.Dictionary;

import dictionary.elearnapp_javafx_group8.Trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dictionary {

    private Trie trie = new Trie();
    private final String path = "src/main/resources/Database/data.txt";

    public void setTrie(List<Word> wordList) {
        for (Word word : wordList) {
            trie.insertWord(word.getWordTarget());
        }
    }

    public void addWord(Word word) {
        try (FileWriter fileWriter = new FileWriter(path, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
            bufferedWriter.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWord(List<Word> wordList, int index) {
        try {
            wordList.remove(index);
            trie = new Trie();
            this.setTrie(wordList);
            this.exportToFile(wordList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateWord(List<Word> wordList, int index,
                           String wordExplain) {
        try {
            wordList.get(index).setWordExplain(wordExplain);
            this.exportToFile(wordList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> Lookup(String word) {
        ObservableList<String> res = FXCollections.observableArrayList();
        List<String> wordList = trie.wordComplete(word);
        res.addAll(wordList);
        return res;
    }

    public int Searcher(List<Word> wordList, String word) {
        wordList.sort(Comparator.comparing(Word::getWordTarget));
        int l = 0;
        int r = wordList.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (wordList.get(m).getWordTarget().equals(word)) {
                return m;
            } else if (wordList.get(m).getWordTarget().compareTo(word) < 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    public void insertFromFile(List<Word> wordList) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String wordTarget = bufferedReader.readLine().replace("|", "");
            String wordExplain = "";
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("|")) {
                    Word newWord = new Word(wordTarget, wordExplain);
                    wordList.add(newWord);
                    wordExplain = "";
                    wordTarget = line.replace("|", "");
                } else {
                    wordExplain += (line + "\n");
                }
            }
            Word newWord = new Word(wordTarget, wordExplain);
            wordList.add(newWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToFile(List<Word> wordList) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : wordList) {
                bufferedWriter.write("|" + word.getWordTarget()
                        + "\n" + word.getWordExplain());
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
