package oop.focus.finance.model;

import javafx.collections.ObservableSet;

/**
 * Interface that models a category manager,
 * working on all categories and managing database operations.
 */
public interface CategoryManager {

    /**
     * Saves a category in the database.
     * If the color of category doesn't exist, saves it in the database too.
     * 
     * @param category that is saved
     */
    void add(Category category);

    /**
     * Removes a category from the database.
     * 
     * @param category being deleted
     */
    void remove(Category category);

    /**
     * @return the list of all categories.
     */
    ObservableSet<Category> getCategories();
}
