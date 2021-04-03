package oop.focus.diary.model;

import org.joda.time.LocalDateTime;

import oop.focus.homepage.model.EventManager;
import org.joda.time.LocalTime;


public class CounterFactory {
    private final EventManager me;
    public CounterFactory(final EventManager me) {
        this.me = me;
    }
    public final TimeScrolling createTimer() {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0) && this.managerTimerEnds());
    }
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
