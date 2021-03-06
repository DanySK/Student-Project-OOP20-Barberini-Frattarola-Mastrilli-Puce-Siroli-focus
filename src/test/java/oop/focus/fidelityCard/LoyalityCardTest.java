package oop.focus.fidelityCard;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.joda.time.*;
import org.junit.Test;

import oop.focus.homePage.fidelityCard.LoyalityCard;
import oop.focus.homePage.fidelityCard.LoyalityCardImpl;
import oop.focus.homePage.fidelityCard.ManagerLoyalityCard;
import oop.focus.homePage.fidelityCard.ManagerLoyalityCardImpl;
import oop.focus.homePage.model.Event;
import oop.focus.homePage.model.EventImpl;
import oop.focus.homePage.model.ManagerEvent;
import oop.focus.homePage.model.ManagerEventImpl;
import oop.focus.homePage.model.Ripetitions;

public class LoyalityCardTest {
	
	private ManagerLoyalityCard loyalityCards = new ManagerLoyalityCardImpl();
	
	@Test
	public void addingAndRemovingLoyaltyCardsTest() {

	    final LoyalityCard first = new LoyalityCardImpl("Coop", "1234567");
	    loyalityCards.addLoyalityCard(first);
	    assertEquals(loyalityCards.getLoyalityCard(), Set.of(first));
	    loyalityCards.removeLoyalityCard(first);
	    assertEquals(loyalityCards.getLoyalityCard(), Set.of());
	}
	
}
