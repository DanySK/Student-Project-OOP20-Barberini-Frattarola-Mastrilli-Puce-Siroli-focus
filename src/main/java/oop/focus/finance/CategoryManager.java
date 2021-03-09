package oop.focus.finance;

import java.util.List;

/**
 * Interface that models a category manager,
 * working on a category list and managing database operations.
 */
public interface CategoryManager {

    /**
     * Adds a category and saves it in the database.
     * 
     * @param category
     */
    void add(Category category);

    /**
     * Removes a category and deletes it from the database.
     * 
     * @param category
     */
    void remove(Category category);

    /**
     * @return the list of all categories.
     */
    List<Category> getCategories();
}
