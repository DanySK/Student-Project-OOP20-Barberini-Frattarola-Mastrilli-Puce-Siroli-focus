package oop.focus.fidelitycard;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class LoyalityCardTest {

    private final ManagerLoyalityCard loyalityCards = new ManagerLoyalityCardImpl();

    @Test
    public void addingAndRemovingLoyaltyCardsTest() {

        final LoyalityCard first = new LoyalityCardImpl("Coop", "1234567");
        loyalityCards.addLoyalityCard(first);
        assertEquals(loyalityCards.getLoyalityCard(), Set.of(first));
        loyalityCards.removeLoyalityCard(first);
        assertEquals(loyalityCards.getLoyalityCard(), Set.of());
    }
}
