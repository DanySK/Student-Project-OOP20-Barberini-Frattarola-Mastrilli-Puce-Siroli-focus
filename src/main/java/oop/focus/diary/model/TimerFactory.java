package oop.focus.diary.model;

public class TimerFactory {
    private String eventName;

    public final void setName(final String eventName) {
        this.eventName = eventName;
    }
    public final TimeScrolling createTimer() {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0), this.eventName);
    }
    public final TimeScrolling createStopwatch() {
        return new TimeScrollingImpl(t -> t + 1, t -> true, this.eventName);
    }
}
