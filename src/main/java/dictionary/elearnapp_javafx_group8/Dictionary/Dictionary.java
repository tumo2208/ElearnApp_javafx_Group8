package dictionary.elearnapp_javafx_group8.Dictionary;

import dictionary.elearnapp_javafx_group8.Trie.Trie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dictionary {
    private Trie trie = new Trie();
    private final String path = "src/main/resources/Database/data.txt";

    public void setTrie(List<Word> wordList) {
        for (int i = 0; i < wordList.size(); ++i) {
            trie.insert(wordList.get(i).getWordTarget());
        }
    }

    public List<Word> sortWordList(List<Word> wordList) {
        List<Word> newWordList = new ArrayList<>();
        newWordList.addAll(wordList);
        Word temp = new Word();

        Collections.sort(newWordList, new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.getWordTarget().compareTo(w2.getWordTarget());
            }
        });

        return newWordList;
    }

    public void addWord(Word word) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("|" + word.getWordTarget() + "\n" + word.getWordExplain());
            bufferedWriter.newLine();
            bufferedWriter.close();
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
                           String wordTarget, String wordExplain) {
        try {
            wordList.get(index).setWordTarget(wordTarget);
            wordList.get(index).setWordExplain(wordExplain);
            this.exportToFile(wordList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> Lookup(String word) {
        ObservableList<String> res = FXCollections.observableArrayList();
        List<String> wordList = trie.autoComplete(word);
        for (int i = 0; i < wordList.size(); ++i) {
            res.add(wordList.get(i));
        }
        return res;
    }

    public int Searcher(List<Word> wordList, String word) {
        List<Word> newWordList = new ArrayList<>();
        newWordList.addAll(wordList);
        newWordList = this.sortWordList(newWordList);
        int l = 0;
        int r = newWordList.size() - 1;
        while (l <= r) {
            int m = r - (l + r) / 2;
            if (newWordList.get(m).getWordTarget().equals(word)) {
                return m;
            } else if (newWordList.get(m).getWordTarget().compareTo(word) < 0) {
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
            String wordExplain = wordTarget + "\n";
            String line = new String();

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("|")) {
                    Word newWord = new Word(wordTarget, wordExplain);
                    wordList.add(newWord);
                    wordExplain = "";
                    wordTarget = line.replace("|", "");
                    wordExplain = wordTarget + "\n";
                } else {
                    wordExplain += (line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToFile(List<Word> wordList) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < wordList.size(); ++i) {
                bufferedWriter.write("|" + wordList.get(i).getWordTarget()
                        + "\n" + wordList.get(i).getWordExplain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
