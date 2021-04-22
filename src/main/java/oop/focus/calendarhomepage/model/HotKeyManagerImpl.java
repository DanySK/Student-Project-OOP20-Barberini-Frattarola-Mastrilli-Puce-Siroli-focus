package oop.focus.calendarhomepage.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

import javafx.collections.ObservableSet;

/**
 * This is a class use to manage hot keys.
 * This class is use for track all the hot keys from all the categories.
 */

public class HotKeyManagerImpl implements HotKeyManager {

    private final Dao<HotKey> sd;

    /**
     * This is the class constructor.
     * @param dsi is the DataSource.
     */
    public HotKeyManagerImpl(final DataSource dsi) {
        this.sd = dsi.getHotKeys();
    }

    public final void add(final HotKey hotKey) {
        if (!this.sd.getAll().contains(hotKey)) {
            try {
                this.sd.save(hotKey);
            } catch (final DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public final ObservableSet<HotKey> getAll() {
        return this.sd.getAll();
    }

    public final HotKeyType getCategory(final HotKey hotKey) {
        return hotKey.getType();
    }

    public final void remove(final HotKey hotKey) {
        try {
            this.sd.delete(hotKey);
        } catch (final DaoAccessException e) {
            e.printStackTrace();
        }
    }

}
