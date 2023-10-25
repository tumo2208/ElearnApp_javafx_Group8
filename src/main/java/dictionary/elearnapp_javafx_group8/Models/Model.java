package dictionary.elearnapp_javafx_group8.Models;

import dictionary.elearnapp_javafx_group8.View.ViewFactory;

public class Model {

    public static Model model;
    private final ViewFactory viewFactory;

    private Model() {
        viewFactory = new ViewFactory();
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
}
