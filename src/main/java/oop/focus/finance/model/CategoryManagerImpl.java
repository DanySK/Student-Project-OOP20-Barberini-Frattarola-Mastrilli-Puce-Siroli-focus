package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

/**
 * Immutable implementation of a category manager.
 */
public class CategoryManagerImpl implements CategoryManager {

    private final Dao<Category> categories;
    private final Dao<String> colors;

    public CategoryManagerImpl(final DataSource db) {
        this.categories = db.getCategories();
        this.colors = db.getColors();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void add(final Category category) {
        try {
            this.checkColor(category.getColor());
            this.categories.save(category);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void remove(final Category category) {
        try {
            this.categories.delete(category);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final ObservableSet<Category> getCategories() {
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
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
