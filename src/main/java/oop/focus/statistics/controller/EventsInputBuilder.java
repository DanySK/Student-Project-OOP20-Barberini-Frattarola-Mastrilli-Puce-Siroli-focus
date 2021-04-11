package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Set;

/**
 * The interface Events input builder models a builder for the {@link EventsInput} interface.
 */
public interface EventsInputBuilder {
    /**
     * Insert the list of selected event names.
     *
     * @param names the account list
     * @return the events input builder
     */
    EventsInputBuilder names(Set<String> names);

    /**
     * Insert the start date.
     *
     * @param startDate the start date
     * @return the events input builder
     */
    EventsInputBuilder from(LocalDate startDate);

    /**
     * Insert the end date.
     *
     * @param endDate the end date
     * @return the events input builder
     */
    EventsInputBuilder to(LocalDate endDate);

    /**
     * Creates a Events input with the given input data.
     *
     * @return the events input
     * @throws IllegalStateException if the event input cannot be created.
     */
    EventsInput save() throws IllegalStateException;
}
