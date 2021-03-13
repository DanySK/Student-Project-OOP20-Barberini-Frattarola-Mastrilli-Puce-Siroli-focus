package oop.focus.fidelitycard;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class LoyalityCardTest {

    private final ManagerFidelityCard fidelityCard = new ManagerFidelityCardImpl();

    @Test
    public void addingAndRemovingFidelityCardsTest() {

        final FidelityCard first = new FidelityCardImpl("Coop", "1234567", FidelityCardType.ALIMENTARI);
        fidelityCard.addFidelityCard(first);
        assertEquals(fidelityCard.getFidelityCards(), Set.of(first));
        fidelityCard.removeFidelityCard(first);
        assertEquals(fidelityCard.getFidelityCards(), Set.of());
    }
}
