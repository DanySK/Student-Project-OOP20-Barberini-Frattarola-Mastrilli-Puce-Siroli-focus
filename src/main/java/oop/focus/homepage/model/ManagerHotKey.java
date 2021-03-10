package oop.focus.homepage.model;

import java.util.Set;

/**
 * This is the interface that models the handling of hot keys of all categories.
 */
public interface ManagerHotKey {

    /**
     * This method is used to perform the "action" method on a specific hot key.
     * Obviously a hot key has a category and the action varies according to that.
     * @param hotKey is the hot key on which to perform the action.
     */
    void action(HotKey hotKey);

    /**
     * This method is use to add a set of hot keys.
     * @param hotKeys is the set of hot keys that must be added.
     */
    void addAll(Set<HotKey> hotKeys);

    /**
     * This method is use to get the set of all the hot keys(of all categories).
     * @return a set of hot keys.
     */
    Set<HotKey> getAll();

    /**
     * This method is use to get the category of a specific hot key.
     * @param hotKey is the hot key whose category you want to know.
     * @return a member of the HotKeyType enumeration.
     */
    HotKeyType getCategory(HotKey hotKey);

    /**
     * This method is use to remove an hot key from the right category.
     * @param hotKey is the hot key that must be placed in a specific category.
     */
    void remove(HotKey hotKey);

}
