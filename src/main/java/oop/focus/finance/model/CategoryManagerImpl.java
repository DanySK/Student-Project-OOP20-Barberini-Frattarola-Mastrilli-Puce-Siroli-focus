package oop.focus.finance.model;

import javafx.collections.ObservableList;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class CategoryManagerImpl implements CategoryManager {

    private final Dao<Category> categories;

    public CategoryManagerImpl(final DataSource db) {
        this.categories = db.getCategories();
    }

    @Override
    public final void add(final Category category) {
        try {
            this.categories.save(category);
        } catch (DaoAccessException e) {
            e.printStackTrace();
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
    public final ObservableList<Category> getCategories() {
        return this.categories.getAll();
    } 
}
