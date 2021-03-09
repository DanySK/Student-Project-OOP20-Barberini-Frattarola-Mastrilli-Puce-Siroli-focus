package oop.focus.homepage.model;

import java.util.Set;

/**
 * This class adds new methods for the counter hotKey.
 */
public interface Manager {

    /**
     * This is an abstract method that is implemented differently based on the category of the hot key.
     * @param hotKey is the hot key on which to perform a specific operation.
     */
    void action(HotKey hotKey);

    /**
     * This method is use to add a new hot key.
     * @param hotKey is the hot key that must be added.
     */
    void addHotKey(HotKey hotKey);

    /**
     * This method is use to get the set of the hot keys.
     * @return a set of hot keys.
     */
    Set<HotKey> getHotKeys();

    /**
     * This method is use to remove an hotKey.
     * @param hotKey is the hotKey to remove.
     */
    void removeHotKey(HotKey hotKey);
}
