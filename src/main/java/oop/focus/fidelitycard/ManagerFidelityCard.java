package oop.focus.fidelitycard;

import java.util.Set;

/**
 * This interface models the class that deals with managing all the fidelity cards.
 */
public interface ManagerFidelityCard {

    /**
     * This method is use to add a new fidelity card .
     * @param fidelityCardToAdd is the fidelity card that must be added.
     */
    void addFidelityCard(FidelityCard fidelityCardToAdd);

    /**
     * This method is use to get the color of a specific fidelity card.
     * @param fidelityCard is the fidelity card whose color you want to know.
     * @return a String that rappresent the color.
     */
    String getColor(FidelityCard fidelityCard);

    /**
     * This class is use for get the set of all the fidelity cards.
     * @return a set of all the saved fidelity cards.
     */
    Set<FidelityCard> getFidelityCards();

    /**
     * This method is use for remove a fidelity card.
     * @param fidelityCardToRemove is the fidelity card that have to be removed.
     */
    void removeFidelityCard(FidelityCard fidelityCardToRemove);
}
