package oop.focus.homePage.model;

import java.util.*;
/**
 * 
 * This class implements the HotKey interface that model a hotkey. 
 * An HotKey object is rappresented by a String ,that is the hotKey name, and a category that is rappresented by a member of the HotKeyType enum.
 */
public abstract class HotKeyImpl implements HotKey {
	
	private String name;
	private HotKeyType hotKeyType;
	private Event eventButton;
	
	/**
	 * 
	 * Class constructor.
	 * @param s is the name of an hot key.
	 * @param e is the type of an hot key.
	 * @param event is the event that rappresent an hot key.(This is important for saving).
	 */
	public HotKeyImpl(final String s, final HotKeyType e, final Event event) {
		this.name = s;
		this.hotKeyType = e;
		this.eventButton = event;
	}
	
	/**
	 * 
	 * This method is use for getting the name of the HotKey.
	 * @return a String.
	 */
	public final String getName() {
		return this.name;
	}
	
	/**
	 * 
         * This method is use for getting the type of the HotKey.
         * @return a member of the HotKeyType enum ( EVENT or ACTIVITY or COUNTER).
         */
	public final HotKeyType getType() {
		return this.hotKeyType;
	}
	
	/**
	 * 
	 * This is an abstract method that is implemented differently based on the category of the hotkey.
	 */
	@Override
	public abstract void action();

}
	



