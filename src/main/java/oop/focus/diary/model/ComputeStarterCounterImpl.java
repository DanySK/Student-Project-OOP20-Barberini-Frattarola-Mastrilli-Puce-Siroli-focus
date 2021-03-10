package oop.focus.diary.model;


import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.ManagerEvent;


public class ComputeStarterCounterImpl implements ComputeStarterCounter {
    private static final int SEC_IN_A_DAY = 86_400;
    private final ManagerEvent me;
    private final String labelName;
    public ComputeStarterCounterImpl(final String labelName, final ManagerEvent me) {
        this.labelName = labelName;
        this.me = me;
    }
    private Set<Event> findByName() {
        return this.me.getEvents().stream().filter(s -> s.getName().equals(this.labelName)).collect(Collectors.toSet());
    }
    private  void manageDifferentDays() {
        this.findByName().stream().filter(s -> !s.getEndDate().equals(s.getStartDate())).forEach(s -> s.getEndHour().plusSeconds(SEC_IN_A_DAY));
    }
    @Override
    public final LocalTime countSeconds() {
        manageDifferentDays();
        return this.findByName().stream().map(s -> s.getEndHour().minusMillis(s.getStartHour().getMillisOfDay()))
                .reduce((a, b) -> a.plusMillis(b.getMillisOfDay())).get();
        }
}
