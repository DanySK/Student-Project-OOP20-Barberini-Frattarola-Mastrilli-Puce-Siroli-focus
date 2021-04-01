package oop.focus.homepage.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
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
    public HotKeyManagerImpl(final DataSource dsi, final EventManager manager) {
        this.sd = dsi.getHotKeys();
        this.manager = manager;
    }

    /**
     * This method is used to perform the "action" method on a specific hot key.
     * Obviously a hot key has a category and the action varies according to that.
     * @param hotKey is the hot key on which to perform the action.
     * @param start is the start.
     * @param end is the end.
     */
    public final void action(final HotKey hotKey, final LocalDateTime start, final LocalDateTime end) {
        this.manager.addEvent(hotKey.createEvent(start, end));
    }

    /**
     * This method is used to add an hot keys.
     * @param hotKey is the hot key that must be added.
     */
    public final void add(final HotKey hotKey) {
        if (!this.sd.getAll().contains(hotKey)) {
            try {
                this.sd.save(hotKey);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is used to add a set of hot keys.
     * @param hotKeys is the set of hot keys that must be added.
     */
    public final void addAll(final Set<HotKey> hotKeys) {
        for (final HotKey hotKey : hotKeys) {
            this.add(hotKey);
        }
    }

    /**
     * This method is used to get the set of all the hot keys(of all categories).
     * @return a set of hot keys.
     */
    public final Set<HotKey> getAll() {
        return this.sd.getAll();
    }

    /**
     * This method is used to get the category of a specific hot key.
     * @param hotKey is the hot key whose category you want to know.
     * @return a member of the HotKeyType enumeration.
     */
    public final HotKeyType getCategory(final HotKey hotKey) {
        return hotKey.getType();
    }

    /**
     * This method is used to get all of the events generated after clicking an hot key.
     * @return a set of events generated after clicking an hot key.
     */
    public final List<Event> getEventsHotKey() {
        return this.manager.getHotKeyEvents();
    }
 
    public final long getClickCount(final String name) {
        return this.getEventsHotKey().stream().filter(e -> e.getName().equals(name)).count();
    }

    /**
     * This method is used to remove an hot key from the right category.
     * @param hotKey is the hot key that must be placed in a specific category.
     */
    public final void remove(final HotKey hotKey) {
        try {
            this.sd.delete(hotKey);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to remove a set of hot key from the collection containing all hot keys.
     * @param hotKeys is the set of hot keys to remove from the collection.
     */
    public final void removeAll(final Set<HotKey> hotKeys) {
        for (final HotKey hotKey : hotKeys) {
            this.remove(hotKey);
        }
    }

}
