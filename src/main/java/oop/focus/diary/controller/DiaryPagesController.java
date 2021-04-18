package oop.focus.diary.controller;

import javafx.collections.ObservableSet;
import oop.focus.diary.model.DiaryImpl;

import java.util.Set;

/**
 * This interface models a controller of pages' diary.
 */
public interface DiaryPagesController extends RemoveControllers<String> {

    /**
     * Returns an observable set with all {@link DiaryImpl} saved.
     * @return  an observable set of all Diary page's saved
     */
    ObservableSet<DiaryImpl> getObservableSet();

    /**
     * Return the content of page's diary whose title is the input's string.
     * @param fileName  the title of a specific diary's page
     * @return  the content of page's diary
     */
    String getContentByName(String fileName);

    /**
     * Updates the diary's page whose title is the first string in input,
     * replacing his content with the second string.
     * @param name  the name of diary's page
     * @param content   the new content of diary's page
     */
    void updatePage(String name, String content);

    /**
     * The method creates a new diary's page whose name and content are specified in input.
     * @param name  the title of the page to insert
     * @param content   the content of the page to insert
     */
    void createPage(String name, String content);

    /**
     * Returns a set with all files' names.
     * @return  a set of titles of all files saved
     */
    Set<String> getFileName();
}
