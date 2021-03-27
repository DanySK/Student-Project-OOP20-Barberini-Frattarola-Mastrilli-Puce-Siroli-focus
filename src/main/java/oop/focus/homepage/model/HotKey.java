package oop.focus.homepage.model;

import org.joda.time.LocalDateTime;

/**
 * This interface model the HotKey class, a class used for represent the hotkeys that are part of the homepage.
 */
public interface HotKey {

    /**
     * This method is used to save the event generated when a hotkey is clicked, the event name is the hot key name.
     * This method is implemented differently based on the category of the hotkey. 
     * @param start is the start.
     * @param end is the end.
     * @return an event.
     */
    Event createEvent(LocalDateTime start, LocalDateTime end);

    /**
     * This method is use for getting the name of the HotKey.
     * @return a string that rappresent the hot key name.
     */
    String getName();

    /**
     * This method is use for getting the type of the HotKey.
     * @return a member of the HotKeyType enumeration.
     */
    HotKeyType getType();

    /**
     * This method is used to get the string representation of the hot key type.
     * @return a string representing the type of the hot key.
     */
    String getTypeRepresentation();
 
}
