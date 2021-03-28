package oop.focus.diary.view;

import javafx.scene.control.TitledPane;

/**
 * The interface has methods to view diary's page, previously saved, inside the specific container.
 */
public interface InsertNewDiaryPage {
    /**
     * Creates a new TitledPane, that represents a diary's page. TitledPane has as name the title of the page to save
     * and, as content, the content of that page.
     * @param s the name of diary's page to view
     */
    void setData(String s);

    /**
     * The method returns the last titledPane created, then it will be added in the apposite container
     * by "PagesView" class.
     * @return  tha last titledPane created
     * */
    TitledPane getData();
}
