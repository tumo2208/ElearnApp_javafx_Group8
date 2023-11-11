package dictionary.elearnapp_javafx_group8.Models;

import dictionary.elearnapp_javafx_group8.Dictionary.Dictionary;
import dictionary.elearnapp_javafx_group8.Dictionary.History;
import dictionary.elearnapp_javafx_group8.Dictionary.Save;
import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.View.ViewFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Model {

    public static Model model;
    private final String dbPath = "src/main/resources/Database/data.txt";
    private final String historyPath = "src/main/resources/Database/history.txt";
    private final String savePath = "src/main/resources/Database/save.txt";
    private final ViewFactory viewFactory;
    private final List<Word> wordList = new ArrayList<>();
    private final Dictionary dictionary = new Dictionary();
    private final List<Word> historyList = new ArrayList<>();
    private final History history = new History();
    private final List<Word> saveList = new ArrayList<>();
    private final Save save = new Save();

    private Model() {
        viewFactory = new ViewFactory();
        dictionary.insertFromFile(wordList, dbPath);
        dictionary.setTrie(wordList);
        wordList.sort(Comparator.comparing(Word::getWordTarget));
        history.insertFromFile(historyList, historyPath);
        save.insertFromFile(saveList, savePath);
        saveList.sort(Comparator.comparing(Word::getWordTarget));
        for (int i = 0; i < saveList.size(); ++i) {
            wordList.get(dictionary.Searcher(wordList, saveList.get(i).getWordTarget())).setSaved(true);
        }
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public List<Word> getHistoryList() {
        return historyList;
    }

    public History getHistory() {
        return history;
    }

    public List<Word> getSaveList() {
        return saveList;
    }

    public Save getSave() {
        return save;
    }

}
