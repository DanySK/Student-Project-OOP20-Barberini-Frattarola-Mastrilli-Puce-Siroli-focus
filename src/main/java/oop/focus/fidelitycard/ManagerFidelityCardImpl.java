package oop.focus.fidelitycard;

import java.util.HashSet;
import java.util.Set;

/**
 * This class models the management of fidelity cards.
 */
public class ManagerFidelityCardImpl implements  ManagerFidelityCard {

    private final Set<FidelityCard> fidelityCard;

    /**
     * This is the class constructor.
     */
    public ManagerFidelityCardImpl() {
        this.fidelityCard = new HashSet<>();
    }

    /**
     * This class is use to add a new fidelity card to the set that contains all the fidelity cards.
     * @param fidelityCardToAdd is the fidelity card that must be added.
     */
    public final void addFidelityCard(final FidelityCard fidelityCardToAdd) {
        this.fidelityCard.add(fidelityCardToAdd);
    }

    /**
     * This method is use to get the set that contains all the fidelity cards.
     * @return a set of fidelity cards.
     */
    public final Set<FidelityCard> getFidelityCards() {
        return this.fidelityCard;
    }

    /**
     * This method is use to get the color of a specific fidelity card.
     * @param fidelityCard is the fidelity card whose color you want to know.
     * @return a String that rappresent the color.
     */
    public final String getColor(final FidelityCard fidelityCard) {
        final FidelityCard temp = this.fidelityCard.stream().filter(e -> e.equals(fidelityCard)).findAny().get();
        return temp.getColor();
    }

    /**
     * This method is use to remove a specific fidelity card from the set that contains all the fidelity cards.
     * @param fidelityCardToRemove is the fidelity card that must be removed.
     */
    public final void removeFidelityCard(final FidelityCard fidelityCardToRemove) {
        this.fidelityCard.remove(fidelityCardToRemove);
    }
}
