package oop.focus.homePage.fidelityCard;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * This interface models the class that deals with managing all the loyalty cards.
 */
public interface ManagerLoyalityCard {

    /**
     * 
     * This method is use to add a new loyality card .
     * @param loyalityCardToAdd is the loyality card that must be added.
     */
    void addLoyalityCard(LoyalityCard loyalityCardToAdd);

    /**
     * 
     * This class is use for get the set of all the loyality cards.
     * @return a set of all the saved loyality cards.
     */
    Set<LoyalityCard> getLoyalityCard();

    /**
     * 
     * This method is use for remove a loyality card.
     * @param loyalityCardToRemove is the loyality card that have to be removed.
     */
    void removeLoyalityCard(LoyalityCard loyalityCardToRemove);
}
