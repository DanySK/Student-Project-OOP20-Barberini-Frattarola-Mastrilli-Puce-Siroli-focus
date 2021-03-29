package oop.focus.finance.model;

import javafx.collections.ObservableList;

/**
 * Interface that models a category manager,
 * working on a category list and managing database operations.
 */
public interface CategoryManager {

    /**
     * Adds a category and saves it in the database.
     * 
     * @param category that is saved
     */
    void add(Category category);

    /**
     * Removes a category and deletes it from the database.
     * 
     * @param category being deleted
     */
    void remove(Category category);

    /**
     * @return the list of all categories.
     */
    ObservableList<Category> getCategories();
}
