package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Objects;
import java.util.Set;

/**
 * An immutable implementation of {@link EventsInput}.
 */
public class EventsInputImpl implements EventsInput {
    private final Set<String> names;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Instantiates a new immutable Events input.
     *
     * @param eventNames the event names
     * @param startDate  the start date
     * @param endDate    the end date
     */
    public EventsInputImpl(final Set<String> eventNames, final LocalDate startDate, final LocalDate endDate) {
        this.names = eventNames;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<String> getEventNames() {
        return this.names;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        EventsInputImpl that = (EventsInputImpl) o;
        return this.names.equals(that.names) && this.startDate.equals(that.startDate) && this.endDate.equals(that.endDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return Objects.hash(this.names, this.startDate, this.endDate);
    }
}
