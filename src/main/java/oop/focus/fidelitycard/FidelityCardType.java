package oop.focus.fidelitycard;

public enum FidelityCardType {

    /**
     * The type alimentari is represented from the purple color.
     */
    ALIMENTARI("Purple"),
    /**
     * The type ristorazione is represented from the pink color.
     */
    RISTORAZIONE("Pink"),
    /**
     * The type cosmesi is represented from the black color.
     */
    COSMESI("Black"),
    /**
     * The type farmacia is represented from the blu color.
     */
    FARMACIA("Blu"),
    /**
     * The type abbigliamento is represented from the red color.
     */
    ABBIGLIAMENTO("Red");

    private final String color;

    /**
     * @param color is the color of the fidelity card type.
     */
    FidelityCardType(final String color) {
        this.color = color;
    }

    /**
     * This method is use to get the color of a specific fidelity card type.
     * @return the fidelity card color.
     */
    public String getColor() {
        return this.color;
    }
}
