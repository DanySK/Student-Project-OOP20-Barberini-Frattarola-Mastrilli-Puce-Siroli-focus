package oop.focus.diary.model;


import oop.focus.homepage.model.ManagerEvent;
 
public class TimerFactory {
    private final ManagerEvent me;
    private String eventName;

    public TimerFactory(final ManagerEvent me) {
        this.me = me;
    }
    public final void setEventName(final String eventName) {
        this.eventName = eventName;
    }

    public final TimeScrolling createTimer() {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0), new TimerListenerImpl(me, this.eventName));
    }
    public final TimeScrolling createStopwatch() {
        return new TimeScrollingImpl(t -> t + 1, t -> true, new TimerListenerImpl(me, this.eventName));
    }

}
