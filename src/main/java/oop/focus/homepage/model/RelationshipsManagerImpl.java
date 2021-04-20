package oop.focus.homepage.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class RelationshipsManagerImpl implements RelationshipsManager {

    private final Dao<String> sd;

    /**
     * This is the class constructor.
     * @param dsi is the data source.
     */
    public RelationshipsManagerImpl(final DataSource dsi) {
        this.sd = dsi.getRelationships();
    }

    public final void add(final String degree) {
        if (!this.sd.getAll().contains(degree)) {
            try {
                this.sd.save(degree);
            } catch (final DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public final ObservableSet<String> getAll() {
        return this.sd.getAll();
    }

    public final void remove(final String degree) {
        try {
            this.sd.delete(degree);
        } catch (final DaoAccessException e) {
            this.extracted();
        }
    }

    private void extracted() {
        throw new IllegalStateException();
    }

}
