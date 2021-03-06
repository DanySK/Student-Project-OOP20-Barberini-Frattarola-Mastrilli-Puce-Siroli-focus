package oop.focus.homePage.fidelityCard;

import java.util.*;

public class ManagerLoyalityCardImpl implements  ManagerLoyalityCard {

    private Set<LoyalityCard> loyalityCard;

    public ManagerLoyalityCardImpl() {
	this.loyalityCard = new HashSet<>();
    }

    public final void addLoyalityCard(final LoyalityCard loyalityCardToAdd) {
	this.loyalityCard.add(loyalityCardToAdd);
    }

    public final Set<LoyalityCard> getLoyalityCard() {
	return this.loyalityCard;
    }

    public final void removeLoyalityCard(final LoyalityCard loyalityCardToRemove) {
	this.loyalityCard.remove(loyalityCardToRemove);
    }
}
