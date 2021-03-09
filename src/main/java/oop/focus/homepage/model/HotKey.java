package oop.focus.homepage.model;

/**
 * This interface model the HotKey class, a class used for represent the hotkeys that are part of the homepage.
 */
public interface HotKey {

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
}
