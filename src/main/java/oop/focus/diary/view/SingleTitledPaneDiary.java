package oop.focus.diary.view;

import javafx.scene.control.TitledPane;

/**
 * The interface has methods to view diary's page, previously saved, inside the specific container.
 */
public interface SingleTitledPaneDiary {
    /**
     * Creates a new TitledPane, that represents a diary's page. TitledPane has as name the title of the page to save
     * and, as content, the content of that page.
     * @param s the name of diary's page to view
     * @return titledPane created
     */
    TitledPane createTitledPane(String s);
}
