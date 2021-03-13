package oop.focus.homepage.model;

/**
 * This enumeration rappresent the hot keys categories.
 * For each category there's a color , rappresented by a string, and his italian translation.
 * Every hot key type is rappresented by an identification number.
 */
public enum HotKeyType { 

    /**
     * The event hot key is rappresented from the pink color, the Italian translation of its category and has as identification number the number one.
     */
    EVENT("Rosa", "Evento"),
    /**
     * The activity hot key is rappresented from the purple color, the Italian translation of its category and has as identification number the number two.
     */ 
    ACTIVITY("Viola", "Attività"),
    /**
     * The counter hot key is rappresented from the blue color, the Italian translation of its category and has as identification number the number three.
     */
    COUNTER("Blu", "Contatore");

    private String color;
    private String type;

    /**
     * @param color is the color of the HotKey.
     * @param type is a String that rappresent the type of the hot key.
     * @param id is an int use for identify the three different type of hot key.
     */
    HotKeyType(final String color, final String type) {
        this.color = color;
        this.type = type;
    }

    /**
     * That method is use for get the color of the hot key.
     * @return a String that rappresent the color of the hot key.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * This method is use to get the type of the hot key.
     * @return a String that rappresent the type of the hot key.
     */
    public String getType() {
        return this.type;
    }
}
