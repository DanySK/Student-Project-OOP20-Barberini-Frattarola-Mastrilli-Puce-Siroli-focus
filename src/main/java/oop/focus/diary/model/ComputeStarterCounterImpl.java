package oop.focus.diary.model;



import java.util.Optional;

import org.joda.time.Period;

import oop.focus.homepage.model.ManagerEvent;


public class ComputeStarterCounterImpl implements ComputeStarterCounter {
    private final ManagerEvent me;
    public ComputeStarterCounterImpl(final ManagerEvent me) {
        this.me = me;
    }
    @Override
    public final Optional<Period> computePeriod(final String labelName) {
        return this.me.findByName(labelName).stream().map(s -> new Period(s.getStart().toDateTime(), s.getEnd().toDateTime())).
            reduce((a, b) -> a.plus(b));
    }
}
