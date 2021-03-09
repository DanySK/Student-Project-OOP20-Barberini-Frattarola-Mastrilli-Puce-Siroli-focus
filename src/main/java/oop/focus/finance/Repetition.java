package oop.focus.finance;

public enum Repetition {

    /**
     * it is not repeated.
     */
    ONCE(1, 1, 1),
    /**
     * it is repeated every week.
     */
    WEEKLY(2, 0.21, 52.14),
    /**
     * it is repeated every month.
     */
    MONTHLY(3, 1, 12),
    /**
     * it is repeated every two months.
     */
    BIMONTHLY(5, 2, 6),
    /**
     * it is repeated every three months.
     */
    QUARTERLY(6, 3, 4),
    /**
     * it is repeated every six months.
     */
    HALF_YEARLY(7, 6, 2),
    /**
     * it is repeated every year.
     */
    YEARLY(4, 12, 1);

    private final int ID;
    private final double perMonth;
    private final double perYear;

    Repetition(final int ID, final double perMonth, final double perYear) {
        this.ID = ID;
        this.perMonth = perMonth;
        this.perYear = perYear;
    }

    /**
     * @return the ID of the repetion
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the number of months that the subscription lasts
     */
    public double getPerMonth() {
        return this.perMonth;
    }

    /**
     * @return the number of times the transaction must be paid in a year
     */
    public double getPerYear() {
        return this.perYear;
    }
   
}
