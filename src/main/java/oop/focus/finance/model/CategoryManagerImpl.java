package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class CategoryManagerImpl implements CategoryManager {

    private final Dao<Category> categories;
    private final Dao<String> colors;

    public CategoryManagerImpl(final DataSource db) {
        this.categories = db.getCategories();
        this.colors = db.getColors();
    }

    @Override
    public final void add(final Category category) {
        try {
            this.checkColor(category.getColor());
            this.categories.save(category);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    private void checkColor(final String color) {
        if (!this.colors.getAll().contains(color)) {
            try {
                this.colors.save(color);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

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
}
