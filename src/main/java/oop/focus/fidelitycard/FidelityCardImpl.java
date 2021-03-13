package oop.focus.fidelitycard;

/**
 * This class is use to model a fidelity card.
 */
public class FidelityCardImpl implements FidelityCard {

    private final String name;
    private final String id;
    private final FidelityCardType type;

    /** 
     * This is the class constructor.
     * @param name is the name of the new card.
     * @param id is the identification number of the card.
     * @param type is the type of the fidelityCard.
     */
    public FidelityCardImpl(final String name, final String id, final FidelityCardType type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    /**
     * This method is use for get the identification number(also know as id) of the fidelity card.
     * @return a String that rappresent the card identification number.
     */
    public final String getCardId() {
        return this.id;
    }

    /**
     * This method is use for get the name of the fidelity card.
     * @return a String that rappresent the card name.
     */
    public final String getCardName() {
        return this.name;
    }

    /**
     * This method is use to get the color(which is determined based on the type of fidelity card)  of a fidelity card.
     * @return a String that rappresent the fidelity card color.
     */
    public final String getColor() {
        return this.type.getColor();
    }
    /**
     * This method is use for get the type of a fidelity card.
     * @return a member of the enumeration.
     */
    public final FidelityCardType getType() {
        return this.type;
    }

    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FidelityCardImpl other = (FidelityCardImpl) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
