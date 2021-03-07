package oop.focus.diary.model;

import java.util.function.Function;
import java.util.function.Predicate;

public class Timer {
    private final TimeScrolling tmi;
    private final Function<Integer, Integer> function = new Function<Integer, Integer>() {
        @Override
        public Integer apply(final Integer arg0) {
            return arg0 - 1;
        }
    };
    private final Predicate<Integer> predicate = new Predicate<Integer>() {
        @Override
        public boolean test(final Integer arg0) {
            return !arg0.equals(0);
        }
    };
    public Timer() {
        super();
        tmi = new TimeScrollingImpl(function, predicate);
    }
    public final TimeScrolling getTmi() {
        return tmi;
    }
}

