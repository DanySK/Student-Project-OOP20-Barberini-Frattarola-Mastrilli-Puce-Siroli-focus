package oop.focus.fidelitycard;

/**
 * This interface model a loyalty card , that is rappresented by a name and an identification number.
 *
 */
public interface LoyalityCard {

    /**
     * This method is use for get the identification number(also know as id) of the loyality card.
     * @return a String that rappresent the card identification number.
     */
    String getCardId();

    /**
     * This method is use for get the name of the loyality card.
     * @return a String that rappresent the card name.
     */
    String getCardName();
}
