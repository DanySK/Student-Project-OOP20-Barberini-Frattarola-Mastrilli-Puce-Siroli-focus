package oop.focus.fidelitycard;

/**
 * This interface model a fidelity card , that is rappresented by a name and an identification number.
 *
 */
public interface FidelityCard {

    /**
     * This method is use for get the identification number(also know as id) of the fidelity card.
     * @return a String that rappresent the card identification number.
     */
    String getCardId();

    /**
     * This method is use for get the name of the fidelity card.
     * @return a String that rappresent the card name.
     */
    String getCardName();

    /**
     * This method is use to get the color(which is determined based on the type of fidelity card)  of a fidelity card.
     * @return a String that rappresent the fidelity card color.
     */
    String getColor();

    /**
     * This method is use for get the type of a fidelity card.
     * @return a member of the enumeration.
     */
    FidelityCardType getType();
}
