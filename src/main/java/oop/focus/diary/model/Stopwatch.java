package oop.focus.diary.model;

import java.util.Optional;

public class Stopwatch {
    private final TimeScrolling ts;
    public Stopwatch() {
        super();
        ts = new TimeScrollingImpl(false, Optional.empty());
    }
    public final TimeScrolling getTs() {
        return ts;
    }
}
