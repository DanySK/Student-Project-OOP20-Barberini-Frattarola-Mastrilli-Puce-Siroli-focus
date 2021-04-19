package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Set;

/**
 * The interface Events input builder models a builder for the {@link TimePeriodInput} interface.
 *
 * @param <X> the input type
 */
public interface TimePeriodInputBuilder<X> {
    /**
     * Insert the set of selected values.
     *
     * @param names the account list
     * @return the events input builder
     */
    TimePeriodInputBuilder<X> values(Set<X> names);

    /**
     * Insert the start date.
     *
     * @param startDate the start date
     * @return the events input builder
     */
    TimePeriodInputBuilder<X> from(LocalDate startDate);

    /**
     * Insert the end date.
     *
     * @param endDate the end date
     * @return the events input builder
     */
    TimePeriodInputBuilder<X> to(LocalDate endDate);

    /**
     * Creates a Events input with the given input data.
     *
     * @return the events input
     * @throws IllegalStateException if the event input cannot be created.
     */
    TimePeriodInput<X> save();
}
