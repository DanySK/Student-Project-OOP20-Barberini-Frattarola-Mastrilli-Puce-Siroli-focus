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
     * @param counterHotKeys is the class that tracks counter type hot keys.
     * @param activityHotKeys is the class that tracks activity type hot keys.
     * @param eventHotKeys is the class that tracks event type hot keys.
     */
    public final void action(final HotKey hotKey, final ManagerCounter counterHotKeys, final ManagerActivity activityHotKeys, final ManagerEventHotKey eventHotKeys) {
        final HotKeyType temporaryType = this.getCategory(hotKey);
        if (temporaryType.equals(HotKeyType.ACTIVITY)) {
            activityHotKeys.action(hotKey);
        } else if (temporaryType.equals(HotKeyType.COUNTER)) {
            counterHotKeys.action(hotKey);
        } else {
            eventHotKeys.action(hotKey);
        }
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
     * This method is use to move an hot key to the right category.
     * @param hotKey is the hot key that must be placed in a specific category.
     * @param counterHotKeys is the class that tracks counter type hot keys.
     * @param activityHotKeys is the class that tracks activity type hot keys.
     * @param eventHotKeys is the class that tracks event type hot keys.
     */
    public final void moveToCategory(final HotKey hotKey, final ManagerCounter counterHotKeys, final ManagerActivity activityHotKeys,
            final ManagerEventHotKey eventHotKeys) {
        if (hotKey.getType().equals(HotKeyType.EVENT)) {
            eventHotKeys.addHotKey(hotKey);
        } else if (hotKey.getType().equals(HotKeyType.COUNTER)) {
            counterHotKeys.addHotKey(hotKey);
        } else {
            activityHotKeys.addHotKey(hotKey);
        }
    }

    /**
     * This method is use to remove an hot key from the right category.
     * @param hotKey is the hot key that must be placed in a specific category.
     * @param counterHotKeys is the class that tracks counter type hot keys.
     * @param activityHotKeys is the class that tracks activity type hot keys.
     * @param eventHotKeys is the class that tracks event type hot keys.
     */
    public final void removeFromCategory(final HotKey hotKey, final ManagerCounter counterHotKeys, final ManagerActivity activityHotKeys,
            final ManagerEventHotKey eventHotKeys) {
        final HotKeyType temporaryType = this.getCategory(hotKey);
        if (temporaryType.equals(HotKeyType.ACTIVITY)) {
            activityHotKeys.removeHotKey(hotKey);
        } else if (temporaryType.equals(HotKeyType.COUNTER)) {
            counterHotKeys.removeHotKey(hotKey);
        } else {
            eventHotKeys.removeHotKey(hotKey);
        }
    }

}
