package oop.focus.diary.model;

import java.util.Optional;

public class Timer {
    private final TimeScrolling tmi;
    public Timer() {
        super();
        tmi = new TimeScrollingImpl(true, Optional.of(0));
    }
    public final TimeScrolling getTmi() {
        return tmi;
    }
}
