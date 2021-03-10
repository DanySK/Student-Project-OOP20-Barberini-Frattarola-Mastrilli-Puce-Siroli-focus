package oop.focus.diary.model;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.ManagerEvent;


public class ComputeStarterCounterImpl implements ComputeStarterCounter {
    private static final int SEC_IN_A_DAY = 86_400;
    private final ManagerEvent me;
    public ComputeStarterCounterImpl(final ManagerEvent me) {
        this.me = me;
    }
    private Set<Event> findByName(final String labelName) {
        return this.me.getEvents().stream().filter(s -> s.getName().equals(labelName)).collect(Collectors.toSet());
    }
    private void manageDifferentDays(final String labelName) {
        this.findByName(labelName).stream().filter(s -> !s.getEndDate().equals(s.getStartDate())).forEach(s -> s.getEndHour().plusSeconds(SEC_IN_A_DAY));
    }
    @Override
    public final Optional<LocalTime> countSeconds(final String nameLabel) {
        manageDifferentDays(nameLabel);
        return this.findByName(nameLabel).stream().map(s -> s.getEndHour().minusMillis(s.getStartHour().getMillisOfDay()))
                .reduce((a, b) -> a.plusMillis(b.getMillisOfDay()));
        }
}
