package oop.focus.finance.model;

import org.joda.time.LocalDate;

import java.util.function.Function;

public enum Repetition {

    /**
     * it is not repeated.
     */
    ONCE("Once", i -> 0, i -> 0, d -> d),
    /**
     * it is repeated daily.
     */
    DAILY("Daily", i -> (int) (i * Costants.DAYS_PER_MONTH),
            i -> i * Costants.DAYS_PER_YEAR,
            d -> d.plusDays(1)),
    /**
     * it is repeated every week.
     */
    WEEKLY("Weekly", i -> (int) (i * Costants.WEEKS_PER_MONTH),
            i -> (int) (i * Costants.WEEKS_PER_YEAR),
            d -> d.plusWeeks(1)),
    /**
     * it is repeated every month.
     */
    MONTHLY("Monthly", i -> i,
            i -> i * Costants.MONTHS_PER_YEAR,
            d -> d.plusMonths(1)),
    /**
     * it is repeated every two months.
     */
    BIMONTHLY("Bimonthly", i -> i / Costants.MONTHS_PER_TWO_MONTHS,
            i -> i * Costants.TWO_MONTH_PER_YEAR,
            d -> d.plusMonths(Costants.MONTHS_PER_TWO_MONTHS)),
    /**
     * it is repeated every three months.
     */
    QUARTERLY("Quaterly", i -> i / Costants.MONTHS_PER_THREE_MONTHS,
            i -> i * Costants.THREE_MONTH_PER_YEAR,
            d -> d.plusMonths(Costants.MONTHS_PER_THREE_MONTHS)),
    /**
     * it is repeated every six months.
     */
    HALF_YEARLY("Half_yearly", i -> i / Costants.MONTHS_PER_SIX_MONTHS,
            i -> i * Costants.SIX_MONTH_PER_YEAR,
            d -> d.plusMonths(Costants.MONTHS_PER_SIX_MONTHS)),
    /**
     * it is repeated every year.
     */
    YEARLY("Yearly", i -> i / Costants.MONTHS_PER_YEAR,
            i -> i,
            d -> d.plusYears(1));

    private final String name;
    private final Function<Integer, Integer> perMonthFunction;
    private final Function<Integer, Integer> perYearFunction;
    private final Function<LocalDate, LocalDate> nextRenewalFunction;

    Repetition(final String name, final Function<Integer, Integer> perMonthFunction, final Function<Integer, Integer> perYearFunction,
               final Function<LocalDate, LocalDate> nextRenewalFunction) {
        this.name = name;
        this.perMonthFunction = perMonthFunction;
        this.perYearFunction = perYearFunction;
        this.nextRenewalFunction = nextRenewalFunction;
    }

    /**
     * @return the function to calculate the average yearly expense
     */
    public Function<Integer, Integer> getPerMonthFunction() {
        return this.perMonthFunction;
    }

    /**
     * @return the function to calculate the average monthly expense
     */
    public Function<Integer, Integer> getPerYearFunction() {
        return this.perYearFunction;
    }

    /**
     * @return the function to calculate the day of the next renewal
     */
    public Function<LocalDate, LocalDate> getNextRenewalFunction() {
        return this.nextRenewalFunction;
    }

    public final String getName() {
        return this.name;
    }

    private static class Costants {

        public static final double DAYS_PER_MONTH = 30.42;
        public static final int DAYS_PER_YEAR = 365;
        public static final double WEEKS_PER_MONTH = 4.35;
        public static final double WEEKS_PER_YEAR = 52.14;
        public static final int MONTHS_PER_TWO_MONTHS = 2;
        public static final int TWO_MONTH_PER_YEAR = 6;
        public static final int MONTHS_PER_THREE_MONTHS = 3;
        public static final int THREE_MONTH_PER_YEAR = 4;
        public static final int MONTHS_PER_SIX_MONTHS = 6;
        public static final int SIX_MONTH_PER_YEAR = 2;
        public static final int MONTHS_PER_YEAR = 12;
    }

}
