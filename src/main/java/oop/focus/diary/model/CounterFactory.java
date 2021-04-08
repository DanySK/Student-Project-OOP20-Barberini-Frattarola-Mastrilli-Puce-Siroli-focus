package oop.focus.diary.model;

import org.joda.time.LocalDateTime;

import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;

/**
 * This class uses Factory's pattern and, if is possible, creates a new counter(which can be timer or stopwatch).
 */
public class CounterFactory {
    private final EventManager me;
    public CounterFactory(final EventManager me) {
        this.me = me;
    }
    public final TimeScrolling createTimer() {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0) && this.managerTimerEnds());
    }

    /**
     * This method, using methods of event manager, checks if is possible to start a timer or a stopwatch in this
     * moment, or sets the end of counter, according to the start time of next event.
     * @return  true if a counter can start, false if there is another event in progress.
     */
    private boolean managerTimerEnds() {
        if (!this.me.checkEmptyJourney(LocalDateTime.now()) && this.me.getClosestEvent(LocalDateTime.now()).isPresent()) {
            return LocalTime.now().compareTo(this.me.getClosestEvent(LocalDateTime.now()).get().minusSeconds(1)) <= 0;
        }
        return true;
    }
    public final TimeScrolling createStopwatch() {
        return new TimeScrollingImpl(t -> t + 1, t -> this.managerTimerEnds());
    }

}
