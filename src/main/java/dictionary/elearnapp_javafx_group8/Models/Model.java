package dictionary.elearnapp_javafx_group8.Models;

import dictionary.elearnapp_javafx_group8.Dictionary.Dictionary;
import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.View.ViewFactory;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public static Model model;
    private final ViewFactory viewFactory;
    private final List<Word> wordList = new ArrayList<>();
    private final Dictionary dictionary = new Dictionary();

    private Model() {
        viewFactory = new ViewFactory();
        dictionary.insertFromFile(wordList);
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
}
