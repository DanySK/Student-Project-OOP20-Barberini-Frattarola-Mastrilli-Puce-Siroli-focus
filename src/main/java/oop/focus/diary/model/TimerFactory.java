package oop.focus.diary.model;

import org.joda.time.LocalDateTime;

import oop.focus.homepage.model.ManagerEvent;
import org.joda.time.LocalTime;


public class TimerFactory {
    private final ManagerEvent me;
    private String eventName;

    public TimerFactory(final ManagerEvent me) {
        this.me = me;
    }
    public final void setEventName(final String eventName) {
        this.eventName = eventName;
    }
//INTERROMPI CRONOMETRO POCO PRIMA DELL'INIZIO DELL'ALTRO EVENTI
    public final TimeScrolling createTimer() {
        System.out.println(me.getClosestEvent(LocalDateTime.now()));
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0) && LocalTime.now().compareTo(me.getClosestEvent(LocalDateTime.now()).minusSeconds(10)) <= 0, new TimerListenerImpl(me, this.eventName));

    }
    public final TimeScrolling createStopwatch() {
        return new TimeScrollingImpl(t -> t + 1, t -> true, new TimerListenerImpl(me, this.eventName));
    }

}
