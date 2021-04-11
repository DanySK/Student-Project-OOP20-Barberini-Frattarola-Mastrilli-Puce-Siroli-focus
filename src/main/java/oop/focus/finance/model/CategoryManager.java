package oop.focus.finance.model;

import javafx.collections.ObservableSet;

/**
 * Interface that models a category manager,
 * working on a category list and managing database operations.
 */
public interface CategoryManager {

    /**
     * Adds a category and saves it in the database.
     * If the color of category doesn't exist, saves it in the database too.
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
    ObservableSet<Category> getCategories();
}
