package oop.focus.homepage.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.Set;

/**
 * This is a class use to manage hot keys.
 * This class is use for track all the hot keys from all the categories.
 */

public class HotKeyManagerImpl implements HotKeyManager {

    private final Dao<HotKey> sd;
    private final EventManager manager;
    /**
     * This is the class constructor.
     * @param dsi is the DataSource.
     * @param manager is the manager of events.
     */
    public HotKeyManagerImpl(final DataSourceImpl dsi, final EventManager manager) {
        this.sd = dsi.getHotKeys();
        this.manager = manager;
    }

    public final void action(final HotKey hotKey, final LocalDateTime start, final LocalDateTime end) {
        this.manager.addEvent(hotKey.createEvent(start, end));
    }

    public final void add(final HotKey hotKey) {
        if (!this.sd.getAll().contains(hotKey)) {
            try {
                this.sd.save(hotKey);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public final void addAll(final Set<HotKey> hotKeys) {
        for (final HotKey hotKey : hotKeys) {
            this.add(hotKey);
        }
    }

    public final Set<HotKey> getAll() {
        return this.sd.getAll();
    }

    public final HotKeyType getCategory(final HotKey hotKey) {
        return hotKey.getType();
    }

    public final List<Event> getEventsHotKey() {
        return this.manager.getHotKeyEvents();
    }
 
    public final long getClickCount(final String name) {
        return this.getEventsHotKey().stream().filter(e -> e.getName().equals(name)).count();
    }

    public final void remove(final HotKey hotKey) {
        try {
            this.sd.delete(hotKey);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    public final void removeAll(final Set<HotKey> hotKeys) {
        for (final HotKey hotKey : hotKeys) {
            this.remove(hotKey);
        }
    }

}
