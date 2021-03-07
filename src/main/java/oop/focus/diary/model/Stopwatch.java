package oop.focus.diary.model;

import java.util.function.Function;
import java.util.function.Predicate;

public class Stopwatch {
    private final TimeScrolling ts;
    private final Predicate<Integer> pre = new Predicate<Integer>() {
        @Override
        public boolean test(final Integer arg0) {
            return true;
        }
    };
    private final Function<Integer, Integer> fun = new Function<Integer, Integer>() {
        @Override
        public Integer apply(final Integer arg0) {
            return arg0 + 1;
        }
    };
    public Stopwatch() {
        super();
        ts = new TimeScrollingImpl(fun, pre);
    }
    public final TimeScrolling getTs() {
        return ts;
    }
}
