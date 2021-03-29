package oop.focus.diary.model;

import org.joda.time.LocalDateTime;

import oop.focus.homepage.model.ManagerEvent;
import org.joda.time.LocalTime;


public class CounterFactory {
    private final ManagerEvent me;
    public CounterFactory(final ManagerEvent me) {
        this.me = me;
    }
    public final TimeScrolling createTimer() {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0) && this.managerTimerEnds());
    }
    private boolean managerTimerEnds() {
        if (!this.me.getClosestEvent(LocalDateTime.now()).isEmpty()) {
            return LocalTime.now().compareTo(this.me.getClosestEvent(LocalDateTime.now()).get().minusSeconds(1)) <= 0;
        }
        return true;
    }
    public final TimeScrolling createStopwatch() {
        return new TimeScrollingImpl(t -> t + 1, t -> this.managerTimerEnds());
    }

}
