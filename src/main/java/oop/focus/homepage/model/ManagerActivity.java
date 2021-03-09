package oop.focus.homepage.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is use for activity hotKey.
 * When a hotkey of the activity category is clicked its status (represented by a boolean) must be changed.
 */
public class ManagerActivity implements Manager {

    private final Map<HotKey, Boolean> hotKeyActivity;

    /**
     * This is the class constructor.
     */
    public ManagerActivity() {
        this.hotKeyActivity = new HashMap<>();
    }

    /**
     * This method is use for change the status of an activity hot key ( from "to do" to "done").
     * @param hotKey is the hotkey to which I want to change the state.
     */
    public final void action(final HotKey hotKey) {
        this.hotKeyActivity.replace(hotKey, !this.hotKeyActivity.get(hotKey));
    }

    /**
     * This method is use to add a new hot key which type is Activity.
     * @param hotKey is the hot key that must be added.
     */
    public final void addHotKey(final HotKey hotKey) {
        this.hotKeyActivity.put(hotKey, false);
    }

    /**
     * This method is used to get the task type hotkey set.
     * @return a set of hot key of type activity.
     */
    public final Set<HotKey> getHotKeys() {
        return this.hotKeyActivity.keySet();
    }

    /**
     * This method is use to get the status("to do" or "done") of a specific hot key.
     * @param hotKey is the hotkey whose status you want to know. 
     * @return a boolean that rappresent the hot key status.
     */
    public final boolean getStatus(final HotKey hotKey) {
        return this.hotKeyActivity.get(hotKey);
    }

    /**
     *This method is use to remove a specific hot key from the set.
     * @param hotKey is the hot key to remove.
     */
    public final void removeHotKey(final HotKey hotKey) {
        this.hotKeyActivity.remove(hotKey);
    }
}
