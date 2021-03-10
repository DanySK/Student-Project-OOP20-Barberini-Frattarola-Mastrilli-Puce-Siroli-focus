package oop.focus.diary.model;

import oop.focus.homepage.model.ManagerEvent;

public class TimerFactory {
    private final ManagerEvent me;

    public TimerFactory(final ManagerEvent me) {
        this.me = me;
    }
    public final TimeScrolling createTimer(final String eventName) {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0), eventName, me);
    }
    public final TimeScrolling createStopwatch(final String eventName) {
        return new TimeScrollingImpl(t -> t + 1, t -> true, eventName, me);
    }
}
