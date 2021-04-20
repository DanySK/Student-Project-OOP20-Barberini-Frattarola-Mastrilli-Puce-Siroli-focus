package oop.focus.fidelitycard;

import java.util.Set;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

/**
 * This class models the management of fidelity cards.
 */
public class ManagerFidelityCardImpl implements  ManagerFidelityCard {

    private final Dao<FidelityCard> sd;

    /**
     * This is the class constructor.
     * @param dsi is the data source.
     */
    public ManagerFidelityCardImpl(final DataSource dsi) {
        this.sd = dsi.getFidelityCards();
    }

    public final void addFidelityCard(final FidelityCard fidelityCardToAdd) {
        if (!this.sd.getAll().contains(fidelityCardToAdd)) {
            try {
                this.sd.save(fidelityCardToAdd);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }


    public final String getColor(final FidelityCard fidelityCard) {
        return this.sd.getAll().stream().filter(e -> e.equals(fidelityCard)).findAny().get().getColor();
    }

    public final Set<FidelityCard> getFidelityCards() {
        return this.sd.getAll();
    }

    public final void removeFidelityCard(final FidelityCard fidelityCardToRemove) {
        try {
            this.sd.delete(fidelityCardToRemove);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }
}
