package oop.focus.fidelitycard;

/**
 * This class is use to model a loyality card.
 */
public class LoyalityCardImpl implements LoyalityCard {

    private final String name;
    private final String id;

    /** 
     * This is the class constructor.
     * @param name is the name of the new card.
     * @param id is the identification number of the card.
     */
    public LoyalityCardImpl(final String name, final String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * This method is use for get the identification number(also know as id) of the loyality card.
     * @return a String that rappresent the card identification number.
     */
    public final String getCardId() {
        return this.id;
    }

    /**
     * This method is use for get the name of the loyality card.
     * @return a String that rappresent the card name.
     */
    public final String getCardName() {
        return this.name;
    }
}
