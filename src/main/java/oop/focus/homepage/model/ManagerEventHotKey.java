package oop.focus.homepage.model;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is use to track all the event hot key.
 */
public class ManagerEventHotKey implements Manager {

    private final Set<HotKey> set;

    /**
     * This is the class constructor.
     */
    public ManagerEventHotKey() {
        this.set = new HashSet<>();
    }

    public final void action(final HotKey hotKey) {
        /*if (this.set.contains(hotKey)) {
        }
        //creare un nuovo evento con nome name , data di oggi , l'ora sarò scelta dall'utente e infine la ripetizione sarà nulla.
         */
    }

    /**
     * This method is use to add a new hot key which type is Event.
     * @param hotKey is the hot key that must be added.
     */
    public final void addHotKey(final HotKey hotKey) {
        this.set.add(hotKey);
    }

    /**
     * This method is used to get the set of event type hotkeys.
     * @return a set of hot key of type counter.
     */
    public final Set<HotKey> getHotKeys() {
        return this.set;
    }

    /**
     * This method is use to remove a specific hot key from the set.
     * @param hotKey is the hot key to remove.
     */
    public final void removeHotKey(final HotKey hotKey) {
        this.set.remove(hotKey);
    }
}
