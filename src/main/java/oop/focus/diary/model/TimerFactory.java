package oop.focus.diary.model;

public class TimerFactory {
    public final TimeScrolling createTimer() {
        return new TimeScrollingImpl(t -> t - 1, t -> !t.equals(0));
    }
    public final TimeScrolling createStopwatch() {
        return new TimeScrollingImpl(t -> t + 1, t -> true);
    }
}
