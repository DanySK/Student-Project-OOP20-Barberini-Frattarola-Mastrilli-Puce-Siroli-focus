package oop.focus.homePage.model;

import java.util.Set;

public interface ManagerHotKey {

    /**
     * 
     * This method is use for add new HotKey to the hotKeySet, that contains all the hotkey.
     * @param hotKey is a object of the class HotKey.
     */
    void addHotKey(HotKey hotKey);

    /**
     * 
     * This method is use for get all the hotKey that are saved.
     * @return the hotKeySet.
     */
    Set<HotKey> getHotKeySet();

    /**
     * 
     * This method return the hotKeyType of a specific hotKey.
     * @param hotKey is a object of the class HotKey.
     * @return an HotKeyType that rappresent the type of the hot key.
     */
    HotKeyType getHotKeyType(HotKey hotKey);

    /**
     * 
     * This method is use for remove a specific hotkey.
     * @param hotKey is the element that needs to be removed from the hotKeySet.
     */
    void removeHotKey(HotKey hotKey);
	
    /**
     * 
     * The click method does something specific when a hotkey is clicked.
     * This method calls the action function of the HotKey class.
     * @param hotKey is the element that has been clicked.
     */
    void click(HotKey hotKey);
}
