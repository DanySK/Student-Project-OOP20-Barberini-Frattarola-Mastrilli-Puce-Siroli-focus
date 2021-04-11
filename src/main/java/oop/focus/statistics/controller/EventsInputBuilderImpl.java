package oop.focus.statistics.controller;

import org.joda.time.LocalDate;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link EventsInputBuilder}.
 */
public class EventsInputBuilderImpl implements EventsInputBuilder {

    private Set<String> eventNames;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventsInputBuilder names(final Set<String> names) {
        this.eventNames = names.stream().filter(s -> !s.isEmpty()).collect(Collectors.toSet());
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventsInputBuilder from(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventsInputBuilder to(final LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventsInput save() throws IllegalStateException {
        if (this.startDate == null || this.endDate == null || this.eventNames == null) {
            throw new IllegalStateException();
        }
        return new EventsInputImpl(this.eventNames, this.startDate, this.endDate);
    }
}
