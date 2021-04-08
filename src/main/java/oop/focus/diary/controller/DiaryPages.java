package oop.focus.diary.controller;

import javafx.collections.ObservableSet;
import oop.focus.diary.model.DiaryImpl;

import java.util.List;

/**
 * This interface models a controller of pages' diary, saved as a file.
 */
public interface DiaryPages {
    /**
     * The method returns a list with all files' names.
     * @return  a list of all files saved
     */
    List<String> filesName();

    /**
     * The list return an observable set with all diary saved(a diary is formed by title and content).
     * @return  an observable set of all Diary's saved
     */
    ObservableSet<DiaryImpl> getObservableSet();

    /**
     * Return the content of page's diary whose title is the input's string.
     * @param fileName  the title of a specific diary's page
     * @return  the content of page's diary
     */
    String getContentByName(String fileName);

    /**
     * The method updates the diary's page whose title is "name", replacing his content with the input's "content".
     * @param name  the name of diary's page
     * @param content   the new content of diary's page
     */
    void updatePage(String name, String content);

    /**
     * The method removes the diary's page whose title is the string in input.
     * @param name  the title of page to remove
     */
    void removePage(String name);

    /**
     * The method creates a new diary's page whose name and content are specified.
     * @param name  the title of the page to insert
     * @param content   the content of the page to insert
     */
    void createPage(String name, String content);
}
