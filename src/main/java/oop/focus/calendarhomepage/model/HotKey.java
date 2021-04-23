package oop.focus.calendarhomepage.model;

/**
 * This interface model the HotKey class, a class used for represent the hot keys that are part of the homepage.
 */
public interface HotKey {

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
