package oop.focus.fidelitycard;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

public class FidelityCardTest {

	private final DataSource dsi = new DataSourceImpl();
    private final ManagerFidelityCard fidelityCard = new ManagerFidelityCardImpl(dsi);

    /**
     * This test is use to add and remove fidelity card from the database.
     */
    @Test
    public void addingAndRemovingFidelityCardsTest() {

        final FidelityCard first = new FidelityCardImpl("Coop", "1234567", FidelityCardType.ALIMENTARI);
        fidelityCard.addFidelityCard(first);
        assertEquals(fidelityCard.getFidelityCards(), List.of(first));
        fidelityCard.removeFidelityCard(first);
        assertEquals(fidelityCard.getFidelityCards(), List.of());
    }
}
