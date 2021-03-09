package oop.focus.homepage.model;

public enum Repetition {

    /**
     * The event never repeat.
     */
    NEVER(1),
    /**
     * it is repeated every week.
     */
    WEEKLY(2),
    /**
     * it is repeated every month.
     */
    MONTHLY(3),
    /**
     * it is repeated every two months.
     */
    BIMONTHLY(4),
    /**
     * it is repeated every three months.
     */
    QUARTERLY(5),
    /**
     * it is repeated every six months.
     */
    HALF_YEARLY(6),
    /**
     * it is repeated every year.
     */
    YEARLY(7);

    private int id;

    Repetition(final int id) {
        this.id = id;
    }

    public final int getId() {
        return this.id;
    }
}
