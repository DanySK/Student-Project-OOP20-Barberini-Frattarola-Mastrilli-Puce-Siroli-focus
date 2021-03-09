package oop.focus.finance;

import java.util.ArrayList;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager {

    private final List<Category> categories = new ArrayList<>();

    @Override
    public final void add(final Category category) {
        this.categories.add(category);
    }

    @Override
    public final void remove(final Category category) {
        this.categories.remove(category);
    }

    @Override
    public final List<Category> getCategories() {
        return this.categories;
    } 
}
