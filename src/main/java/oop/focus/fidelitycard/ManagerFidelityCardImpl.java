package oop.focus.fidelitycard;


import java.util.List;
import oop.focus.db.DataSource;
import oop.focus.db.SingleDao;
import oop.focus.db.exceptions.DaoAccessException;

/**
 * This class models the management of fidelity cards.
 */
public class ManagerFidelityCardImpl implements  ManagerFidelityCard {

    private final SingleDao<FidelityCard> sd;

    /**
     * This is the class constructor.
     * @param dsi is the data source.
     */
    public ManagerFidelityCardImpl(final DataSource dsi) {
        this.sd = dsi.getFidelityCards();
    }

    /**
     * This class is use to add a new fidelity card to the set that contains all the fidelity cards.
     * @param fidelityCardToAdd is the fidelity card that must be added.
     */
    public final void addFidelityCard(final FidelityCard fidelityCardToAdd) {
        if (!this.sd.getAll().contains(fidelityCardToAdd)) {
            try {
                this.sd.save(fidelityCardToAdd);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is use to get the color of a specific fidelity card.
     * @param fidelityCard is the fidelity card whose color you want to know.
     * @return a String that represent the color.
     */
    public final String getColor(final FidelityCard fidelityCard) {
        return this.sd.getAll().stream().filter(e -> e.equals(fidelityCard)).findAny().get().getColor();
    }

    /**
     * This method is use to get the set that contains all the fidelity cards.
     * @return a set of fidelity cards.
     */
    public final List<FidelityCard> getFidelityCards() {
        return this.sd.getAll();
    }

    /**
     * This method is use to remove a specific fidelity card from the set that contains all the fidelity cards.
     * @param fidelityCardToRemove is the fidelity card that must be removed.
     */
    public final void removeFidelityCard(final FidelityCard fidelityCardToRemove) {
        try {
            this.sd.delete(fidelityCardToRemove);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }
}
