package dictionary.elearnapp_javafx_group8.Models;

import dictionary.elearnapp_javafx_group8.Dictionary.Dictionary;
import dictionary.elearnapp_javafx_group8.Dictionary.History;
import dictionary.elearnapp_javafx_group8.Dictionary.Save;
import dictionary.elearnapp_javafx_group8.Dictionary.Word;
import dictionary.elearnapp_javafx_group8.Question.QandA;
import dictionary.elearnapp_javafx_group8.Question.QuestionCatchWord;
import dictionary.elearnapp_javafx_group8.View.ViewFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Model {

    public static Model model;
    final String dbPath = "src/main/resources/Database/data.txt";
    final String historyPath = "src/main/resources/Database/history.txt";
    final String savePath = "src/main/resources/Database/save.txt";
    private final ViewFactory viewFactory;
    private final List<Word> wordList = new ArrayList<>();
    private final Dictionary dictionary = new Dictionary();
    private final List<Word> historyList = new ArrayList<>();
    private final History history = new History();
    private final List<Word> saveList = new ArrayList<>();
    private final Save save = new Save();
    private final List<QuestionCatchWord> questionCatchWordList = new ArrayList<>();

    private Model() {
        viewFactory = new ViewFactory();
        dictionary.insertFromFile(wordList, dbPath);
        dictionary.setTrie(wordList);
        wordList.sort(Comparator.comparing(Word::getWordTarget));
        history.insertFromFile(historyList, historyPath);
        save.insertFromFile(saveList, savePath);
        saveList.sort(Comparator.comparing(Word::getWordTarget));
        for (Word word : saveList) {
            wordList.get(dictionary.Searcher(wordList, word.getWordTarget())).setSaved(true);
        }
        try {
            FileReader fileReader = new FileReader("src/main/resources/Database/GameTu/question.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String answer = bufferedReader.readLine().replace("|", "");
            List<QandA> qanda = new ArrayList<>();
            boolean isQuestion = true;
            String question = "";
            String qAns;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("|")) {
                    QuestionCatchWord questionCatchWord = new QuestionCatchWord(answer, qanda);
                    questionCatchWordList.add(questionCatchWord);
                    answer = line.replace("|", "");
                    qanda = new ArrayList<>();
                    question = "";
                    isQuestion = true;
                } else {
                    if (isQuestion) {
                        question = line;
                    } else {
                        qAns = line;
                        QandA qandA = new QandA(question, qAns);
                        qanda.add(qandA);
                    }
                    isQuestion = !isQuestion;
                }
            }
            QuestionCatchWord questionCatchWord = new QuestionCatchWord(answer, qanda);
            questionCatchWordList.add(questionCatchWord);
        } catch (Exception e) {
            e.printStackTrace();
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

    public List<QuestionCatchWord> getQuestionCatchWordList() {
        return questionCatchWordList;
    }

}