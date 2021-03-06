package oop.focus.homePage.model;

/*
 * This interface model the HotKey class, a class used for represent the hotkeys that are part of the homepage.
 */
public interface HotKey {
	
    	/*
    	 * This method is use for getting the name of the HotKey.
    	 */
	String getName();
	
	/*
	 * This method is use for getting the type of the HotKey.
	 */
	HotKeyType getType();
	
	/*
	 * This is an abstract method that is implemented differently based on the category of the hotkey;
	 * @see HotKeyFactoryImpl class.
	 */
	void action();
}
