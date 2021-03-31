package oop.focus.homepage.model;

import org.joda.time.LocalDateTime;

import oop.focus.finance.model.Repetition;

/**
 * This class implements the HotKey interface that model a hotkey. 
 * An HotKey object is rappresented by a String ,that is the hotKey name, and a category that is rappresented by a member of the HotKeyType enum.
 */
public class HotKeyImpl implements HotKey {

    private String name;
    private HotKeyType hotKeyType;
    private String typeRepresentation;

    /**
     * Class constructor.
     * @param s is the name of an hot key.
     * @param e is the type of an hot key.
     */
    public HotKeyImpl(final String s, final HotKeyType e) {
        this.name = s;
        this.hotKeyType = e;
        this.typeRepresentation = this.hotKeyType.getType();
    }

    /**
     * This method is used to save the event generated when a hotkey is clicked, the event name is the hot key name.
     * This method is implemented differently based on the category of the hotkey. 
     * @param start is the start.
     * @param end is the end.
     * @return an event.
     */
    public Event createEvent(final LocalDateTime start, final LocalDateTime end) {
        return new EventImpl(this.name, start, end, Repetition.ONCE);
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
     * @return a member of the HotKeyType enumeration.
     */
    public final HotKeyType getType() {
        return this.hotKeyType;
    }

    /**
     * This method is used to get the string representation of the hot key type.
     * @return a string representing the type of the hot key.
     */
    public final String getTypeRepresentation() {
        return this.typeRepresentation;
    } 

    public final void setName(final String newValue) {
        this.name = newValue;	
    }

    /**
     * This is the hasCode related to the equals method.
     * @return a string.
     */
    public final int hashCode() {
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

    public final void setType(final String newValue) {
		this.hotKeyType = HotKeyType.getTypeFrom(newValue);
		this.typeRepresentation = newValue;
	}

}




