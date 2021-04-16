package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Set;

/**
 * The interface Time period input represents a collection of data that can be used as
 * input from any source, defined in a specific time period.
 *
 * @param <X> the input type
 */
public interface TimePeriodInput<X> {
    /**
     * Gets the list of the selected event names.
     *
     * @return the account list
     */
    Set<X> getValues();

    /**
     * Gets the selected start date.
     *
     * @return the start date
     */
    LocalDate getStartDate();

    /**
     * Gets the selected end date.
     *
     * @return the end date
     */
    LocalDate getEndDate();
}
