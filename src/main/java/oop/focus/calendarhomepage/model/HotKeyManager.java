package oop.focus.calendarhomepage.model;

import java.util.Set;

import javafx.collections.ObservableSet;

/**
 * This is the interface that models the handling of hot keys of all categories.
 */
public interface HotKeyManager {

    /**
     * This method is used to add an hot keys.
     * @param hotKey is the hot key that must be added.
     */
    void add(HotKey hotKey);

    /**
     * This method is used to add a set of hot keys.
     * @param hotKeys is the set of hot keys that must be added.
     */
    void addAll(Set<HotKey> hotKeys);

    /**
     * This method is used to get the set of all the hot keys(of all categories).
     * @return a set of hot keys.
     */
    ObservableSet<HotKey> getAll();

    /**
     * This method is used to get the category of a specific hot key.
     * @param hotKey is the hot key whose category you want to know.
     * @return a member of the HotKeyType enumeration.
     */
    HotKeyType getCategory(HotKey hotKey);

    /**
     * is used to remove a specific hot key from the collection containing all hot keys. 
     * @param hotKey is the hot key that must be remove.
     */
    void remove(HotKey hotKey);

}
