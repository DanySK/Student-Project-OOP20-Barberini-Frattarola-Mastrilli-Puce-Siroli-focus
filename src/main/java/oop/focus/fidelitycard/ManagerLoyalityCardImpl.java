package oop.focus.fidelitycard;

import java.util.HashSet;
import java.util.Set;

/**
 * This class models the management of loyalty cards.
 */
public class ManagerLoyalityCardImpl implements  ManagerLoyalityCard {

    private final Set<LoyalityCard> loyalityCard;

    /**
     * This is the class constructor.
     */
    public ManagerLoyalityCardImpl() {
        this.loyalityCard = new HashSet<>();
    }

    /**
     * This class is use to add a new loyality card to the set that contains all the loyality cards.
     * @param loyalityCardToAdd is the loyality card that must be added.
     */
    public final void addLoyalityCard(final LoyalityCard loyalityCardToAdd) {
        this.loyalityCard.add(loyalityCardToAdd);
    }

    /**
     * This method is use to get the set that contains all the loyality cards.
     * @return a set of loyality cards.
     */
    public final Set<LoyalityCard> getLoyalityCard() {
        return this.loyalityCard;
    }

    /**
     * This method is use to remove a specific loyality card from the set that contains all the loyality cards.
     * @param loyalityCardToRemove is the loyality card that must be removed.
     */
    public final void removeLoyalityCard(final LoyalityCard loyalityCardToRemove) {
        this.loyalityCard.remove(loyalityCardToRemove);
    }
}
