package oop.focus.homePage.model;

import java.util.Optional;

/**
 * 
 * This enumeration rappresent the hot keys categories.
 * For each category there's a color , rappresented by a string, and his italian translation.
 * Every hot key type is rappresented by an identification number.
 */
public enum HotKeyType { 

        /**
         * 
         * The event hot key is rappresented from the pink color, the Italian translation of its category and has as identification number the number one.
         */
	EVENT("Rosa", "Evento", 1),
	/**
	 * 
	 * The activity hot key is rappresented from the purple color, the Italian translation of its category and has as identification number the number two.
	 */
	ACTIVITY("Viola", "Attività", 2),
	/**
	 * 
	 * The counter hot key is rappresented from the blue color, the Italian translation of its category and has as identification number the number three.
	 */
	COUNTER("Blu", "Contatore", 3);
	
	private String color;
	private String type;
	private int id;
	
	/**
	 * 
	 * @param color is the color of the HotKey.
	 * @param type is a String that rappresent the type of the hot key.
	 * @param id is an int use for identify the three different type of hot key.
	 */
	HotKeyType(final String color, final String type, final int id ){
		this.color = color;
		this.type = type;
		this.id = id;
	}
	
	/**
	 * 
	 * That method is use for get the color of the hot key.
	 * @return a String that rappresent the color of the hot key.
	 */
	public String getColor() {
		return this.color;
	}
	
	/**
	 * 
	 * This method is use to get the type of the hot key.
	 * @return a String that rappresent the type of the hot key.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 
	 * This method is use to get the id of the hot key.
	 * @return an int that rappresent the id of the hot key.
	 */
	public int getId() {
		return id;
	}
}
