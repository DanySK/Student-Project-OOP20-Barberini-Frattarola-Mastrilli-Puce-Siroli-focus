package oop.focus.homepage.model;

/**
 * This class implements the HotKey interface that model a hotkey. 
 * An HotKey object is rappresented by a String ,that is the hotKey name, and a category that is rappresented by a member of the HotKeyType enum.
 */
public abstract class HotKeyImpl implements HotKey {

    private final String name;
    private final HotKeyType hotKeyType;

    /**
     * Class constructor.
     * @param s is the name of an hot key.
     * @param e is the type of an hot key.
     */
    public HotKeyImpl(final String s, final HotKeyType e) {
        this.name = s;
        this.hotKeyType = e;
    }

    /**
     * This method is use for getting the name of the HotKey.
     * @return a String.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * This method is use for getting the type of the HotKey.
     * @return a member of the HotKeyType enum ( EVENT or ACTIVITY or COUNTER).
     */
    public final HotKeyType getType() {
        return this.hotKeyType;
    }

    /**
     * This method is used to save the event generated when a hotkey is clicked.
     * This method is implemented differently based on the category of the hotkey.
     */
    public abstract void createEvent();
}




