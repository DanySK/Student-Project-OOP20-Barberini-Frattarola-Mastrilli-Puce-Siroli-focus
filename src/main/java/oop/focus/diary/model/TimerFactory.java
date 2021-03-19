package oop.focus.diary.model;

import org.joda.time.LocalDateTime;

import oop.focus.homepage.model.ManagerEvent;
import org.joda.time.LocalTime;


public class TimerFactory {
    private final ManagerEvent me;
    public TimerFactory(final ManagerEvent me) {
        this.me = me;
    }

    public final TimeScrolling createTimer(final String eventName) {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0) && managerTimerEnds(), new TimerListenerImpl(me, eventName));
    }
    private boolean managerTimerEnds() {
        if (me.getClosestEvent(LocalDateTime.now()).isPresent()) {
            return LocalTime.now().compareTo(me.getClosestEvent(LocalDateTime.now()).get().minusSeconds(1)) <= 0;
        }
        return true;
    }
    public final TimeScrolling createStopwatch(final String eventName) {
        return new TimeScrollingImpl(t -> t + 1, t -> managerTimerEnds(), new TimerListenerImpl(me, eventName));
    }

}
