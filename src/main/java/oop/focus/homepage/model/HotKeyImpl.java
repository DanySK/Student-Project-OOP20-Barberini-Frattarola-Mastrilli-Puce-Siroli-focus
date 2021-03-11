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
     * This method is used to save the event generated when a hotkey is clicked.
     * This method is implemented differently based on the category of the hotkey.
     * @return the event.
     */
    public abstract Event createEvent();

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
     * This is the hasCode related to the equals method.
     * @return an int.
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hotKeyType == null) ? 0 : hotKeyType.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * This method is used to check if one keyboard shortcut is the same as another.
     * Two hotkeys are the same if their name and the hotkey category are the same.
     * @param obj is the hot key whose equality needs to be checked.
     * @return a boolean which will be true if the two hot keys are equal and false if the two hot keys are different.
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HotKeyImpl other = (HotKeyImpl) obj;
        if (hotKeyType != other.hotKeyType) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}




