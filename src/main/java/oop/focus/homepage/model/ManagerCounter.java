package oop.focus.homepage.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is use for counter hotKey.
 * When a hotkey of the activity category is clicked its counter(rappresented by an integer) must be incremented.
 */
public class ManagerCounter implements Manager { 

    private final Map<HotKey, Integer> hotKeyCounter;

    /**
     * This is the class constructor.
     */
    public ManagerCounter() {
        this.hotKeyCounter = new HashMap<>();
    }

    /**
     * This method is used to increment the counter of a counter type hotkey.
     * @param hotKey is the hotkey to which I want to increent the counter.
     */
    public final void action(final HotKey hotKey) {
        this.hotKeyCounter.replace(hotKey, this.hotKeyCounter.get(hotKey) + 1);
    }

    /**
     * This method is use to add a new hot key which type is Counter.
     * @param hotKey is the hot key that must be added.
     */
    public final void addHotKey(final HotKey hotKey) {
        this.hotKeyCounter.put(hotKey, 0);
    }

    /**
     * This method is used to get the counter type hotkey set.
     * @return a set of hot key of type counter.
     */
    public final Set<HotKey> getHotKeys() {
        return this.hotKeyCounter.keySet();
    }

    /**
     * This method is use to get the counter of a specific hot key.
     * @param hotKey is the hotkey whose counter you want to know. 
     * @return an integer that rappresent the hot key counter value.
     */
    public final Integer getValue(final HotKey hotKey) {
        return this.hotKeyCounter.get(hotKey);
    }

    /**
     * This method is use to remove a specific hot key from the set.
     * @param hotKey is the hot key to remove.
     */
    public final void removeHotKey(final HotKey hotKey) {
        this.hotKeyCounter.remove(hotKey);
    }
}
