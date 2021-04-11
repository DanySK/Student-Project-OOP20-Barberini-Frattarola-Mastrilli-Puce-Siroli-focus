package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Set;

/**
 * The interface Events input represents a collection of data that can be used as inputs to filter any
 * data about the events section.
 */
public interface EventsInput {
    /**
     * Gets the list of the selected event names.
     *
     * @return the account list
     */
    Set<String> getEventNames();

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
