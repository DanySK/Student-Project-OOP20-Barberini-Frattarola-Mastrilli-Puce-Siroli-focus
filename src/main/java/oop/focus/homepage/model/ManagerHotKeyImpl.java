package oop.focus.homepage.model;

import java.util.HashSet;
import java.util.Set;

/**
 * This is a class use to manage hot keys.
 * This class is use for track all the hot keys from all the categories.
 */

public class ManagerHotKeyImpl implements ManagerHotKey {

    private final Set<HotKey> hotKeyTracker;

    /**
     * This is the class constructor.
     */
    public ManagerHotKeyImpl() {
        this.hotKeyTracker = new HashSet<>();
    }

    /**
     * This method is used to perform the "action" method on a specific hot key.
     * Obviously a hot key has a category and the action varies according to that.
     * @param hotKey is the hot key on which to perform the action.
     */
    public final void action(final HotKey hotKey) {
        hotKey.createEvent();
    }

    /**
     * This method is use to add a set of hot keys.
     * @param hotKeys is the set of hot keys that must be added.
     */
    public final void addAll(final Set<HotKey> hotKeys) {
        this.hotKeyTracker.addAll(hotKeys);
    }

    /**
     * This method is use to get the set of all the hot keys(of all categories).
     * @return a set of hot keys.
     */
    public final Set<HotKey> getAll() {
        return this.hotKeyTracker;
    }

    /**
     * This method is use to get the category of a specific hot key.
     * @param hotKey is the hot key whose category you want to know.
     * @return a member of the HotKeyType enumeration.
     */
    public final HotKeyType getCategory(final HotKey hotKey) {
        return hotKey.getType();
    }

    /**
     * This method is use to remove an hot key from the right category.
     * @param hotKey is the hot key that must be placed in a specific category.
     */
    public final void remove(final HotKey hotKey) {
        this.hotKeyTracker.remove(hotKey);
    }

}
