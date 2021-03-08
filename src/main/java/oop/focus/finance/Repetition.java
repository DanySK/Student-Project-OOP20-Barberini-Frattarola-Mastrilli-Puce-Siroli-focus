package oop.focus.finance;

public enum Repetition {

    /**
     * it is not repeated.
     */
    ONCE(1, 1),
    /**
     * it is repeated every week.
     */
    WEEKLY(0.21, 52.14),
    /**
     * it is repeated every month.
     */
    MONTHLY(1, 12),
    /**
     * it is repeated every two months.
     */
    BIMONTHLY(2, 6),
    /**
     * it is repeated every three months.
     */
    QUARTERLY(3, 4),
    /**
     * it is repeated every six months.
     */
    HALF_YEARLY(6, 2),
    /**
     * it is repeated every year.
     */
    YEARLY(12, 1);

    private double perMonth;
    private double perYear;

    Repetition(final double perMonth, final double perYear) {
        this.perMonth = perMonth;
        this.perYear = perYear;
    }

    /**
     * @return the number of months that the subscription lasts
     */
    public double getPerMonth() {
        return perMonth;
    }

    /**
     * 
     * @return the number of times the transaction must be paid in a year
     */
    public double getPerYear() {
        return perYear;
    }
}
