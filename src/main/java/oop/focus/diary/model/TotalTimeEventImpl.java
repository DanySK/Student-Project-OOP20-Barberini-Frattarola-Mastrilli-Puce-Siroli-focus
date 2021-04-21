package oop.focus.diary.model;



import java.util.Optional;

import org.joda.time.Period;

import oop.focus.event.model.EventManager;
/**
 * Immutable implementation of {@link TotalTimeEvent}.
 */

public class TotalTimeEventImpl implements TotalTimeEvent {
    private final EventManager eventManager;

    /**
     * Instantiates a new total time event.
     * @param eventManager    the event manager
     */
    public TotalTimeEventImpl(final EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Optional<Period> computePeriod(final String labelName) {
        return this.eventManager.findByName(labelName).stream().map(s -> new Period(s.getStart().toDateTime(), s.getEnd().
                toDateTime())).reduce(Period::plus);
    }
}
