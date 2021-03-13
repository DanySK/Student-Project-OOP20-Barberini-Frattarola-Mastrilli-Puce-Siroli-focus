package oop.focus.fidelitycard;

public enum FidelityCardType {

    /**
     * 
     */
    ALIMENTARI("Purple"),
    /**
     * 
     */
    RISTORAZIONE("Pink"),
    /**
     * 
     */
    COSMESI("Black"),
    /**
     * 
     */
    FARMACIA("Blu"),
    /**
     * 
     */
    ABBIGLIAMENTO("Red");

    private final String color;

    FidelityCardType(final String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
