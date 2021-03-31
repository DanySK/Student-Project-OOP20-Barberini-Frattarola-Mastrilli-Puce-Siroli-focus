package oop.focus.homepage.model;

import org.joda.time.LocalDateTime;

/**
 * This interface model the HotKey class, a class used for represent the hot keys that are part of the homepage.
 */
public interface HotKey {

    /**
     * This method is used to save the event generated when a hot key is clicked, the event name is the hot key name.
     * This method is implemented differently based on the category of the hot key. 
     * @param start is the start.
     * @param end is the end.
     * @return an event.
     */
    Event createEvent(LocalDateTime start, LocalDateTime end);

    /**
     * This method is used for getting the name of the HotKey.
     * @return a string that represent the hot key name.
     */
    String getName();

    /**
     * This method is used for getting the type of the HotKey.
     * @return a member of the HotKeyType enumeration.
     */
    HotKeyType getType();

    /**
     * This method is used to get the string representation of the hot key type.
     * @return a string representing the type of the hot key.
     */
    String getTypeRepresentation();

    /**
     * This method is used to modify the name.
     * @param newName is the new name of the hot key.
     */
    void setName(String newName);

    /**
     * This method is used to modify the type.
     * @param newType is the new type of the hot key.
     */
    void setType(String newType);
 
}
