package oop.focus.homePage.model;

import java.util.*;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
/**
 * 
 * This is a class use to manage hotkeys , this class is use for track all hotkeys.
 */

public class ManagerHotKeyImpl implements ManagerHotKey{
	
	private final Set<HotKey> hotKeySet;
	
	/**
	 * 
	 * This method is use for add new HotKey to the hotKeySet, that contains all the hotkey.
	 */
	public ManagerHotKeyImpl() {
		this.hotKeySet = new HashSet<>();  //prendi i bottoni dal database;
		//Dao<HotKey>.getAll
	}
	
	/**
	 * 
         * This method is use for add new HotKey to the hotKeySet, that contains all the hotkey.
         * @param hotKey is a object of the class HotKey.
         */
	public final void addHotKey(final HotKey hotKey) {
		this.hotKeySet.add(hotKey);
	}
	
	/**
	 * 
         * This method is use for get all the hotKey that are saved.
         * @return the hotKeySet.
         */
	public final Set<HotKey> getHotKeySet() {
		return this.hotKeySet;
	}
	
	/**
	 * 
         * This method return the hotKeyType of a specific hotKey.
         * @param hotKey is a object of the class HotKey.
         * @return an HotKeyType that rappresent the type of the hot key.
         */
	public final HotKeyType getHotKeyType(final HotKey hotKey) {
		return hotKey.getType();
	}
	
	/**
	 * 
         * This method is use for remove a specific hotkey.
         * @param hotKey is the element that needs to be removed from the hotKeySet.
         */
	public final void removeHotKey(final HotKey hotKey) {
		this.hotKeySet.remove(hotKey);
	}

	/**
	 * 
         * The click method does something specific when a hotkey is clicked.
         * This method calls the action function of the HotKey class.
         * @param hotKey is the element that has been clicked.
         */
	public final void click(final HotKey hotKey) {
		hotKey.action();
		//salvalo
	}
}
