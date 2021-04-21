package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

/**
 * Immutable implementation of a category manager.
 */
public class CategoryManagerImpl implements Manager<Category> {

    private final Dao<Category> categories;
    private final Dao<String> colors;

    public CategoryManagerImpl(final DataSource db) {
        this.categories = db.getCategories();
        this.colors = db.getColors();
    }

    /**
     * Saves a category in the database.
     * If the color of category doesn't exist, saves it in the database too.
     *
     * @param category that is saved
     */
    @Override
    public final void add(final Category category) {
        try {
            this.checkColor(category.getColor());
            this.categories.save(category);
        } catch (final DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a category from the database.
     *
     * @param category being deleted
     */
    @Override
    public final void remove(final Category category) {
        try {
            this.categories.delete(category);
        } catch (final DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the list of all categories.
     */
    @Override
    public final ObservableSet<Category> getElements() {
        return this.categories.getAll();
    }

    /**
     * Check if the color has been saved in the database. If not, it is saved.
     *
     * @param color to check
     */
    private void checkColor(final String color) {
        if (!this.colors.getAll().contains(color)) {
            try {
                this.colors.save(color);
            } catch (final DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
