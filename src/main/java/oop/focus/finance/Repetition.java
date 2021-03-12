package oop.focus.finance;

import org.joda.time.LocalDate;

import java.util.function.Function;

public enum Repetition {

    /**
     * it is not repeated.
     */
    ONCE(1, 1, d -> d),
    /**
     * it is repeated every week.
     */
    WEEKLY(0.21, 52.14, d -> d.plusWeeks(1)),
    /**
     * it is repeated every month.
     */
    MONTHLY(1, 12, d -> d.plusMonths(1)),
    /**
     * it is repeated every two months.
     */
    BIMONTHLY(2, 6, d -> d.plusMonths(2)),
    /**
     * it is repeated every three months.
     */
    QUARTERLY(3, 4, d -> d.plusMonths(3)),
    /**
     * it is repeated every six months.
     */
    HALF_YEARLY(6, 2, d -> d.plusMonths(6)),
    /**
     * it is repeated every year.
     */
    YEARLY(12, 1, d -> d.plusYears(1));

    private final double perMonth;
    private final double perYear;
    private final Function<LocalDate, LocalDate> function;

    Repetition(final double perMonth, final double perYear, final Function<LocalDate, LocalDate> function) {
        this.perMonth = perMonth;
        this.perYear = perYear;
        this.function = function;
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

    /**
     * @return the function to calculate the day of the next renewal
     */
    public Function<LocalDate, LocalDate> getFunction() {
        return this.function;
    }
}
