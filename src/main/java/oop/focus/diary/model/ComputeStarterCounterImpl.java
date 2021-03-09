package oop.focus.diary.model;

import java.util.Set;
import java.util.stream.Collectors;

import oop.focus.homePage.model.Event;
import oop.focus.homePage.model.ManagerEvent;
import oop.focus.homePage.model.ManagerEventImpl;

public class ComputeStarterCounterImpl implements ComputeStarterCounter {
    private static final int SEC_IN_A_DAY = 86_400;
    private final ManagerEvent me;
    private final String labelName;
    public ComputeStarterCounterImpl(final String labelName) {
        this.labelName = labelName;
        me = new ManagerEventImpl();
    }
    private Set<Event> findByName() {
        return this.me.getEvents().stream().filter(s -> s.getName().equals(this.labelName)).collect(Collectors.toSet());
    }
    private  void manageDifferentDays() {
        this.findByName().stream().filter(s -> !s.getEndDate().equals(s.getStartDate())).forEach(s -> s.getEndHour().plusSeconds(SEC_IN_A_DAY));
    }
    @Override
    public final long countSeconds() {
        manageDifferentDays();
        return this.findByName().stream().mapToLong(s -> s.getEndHour().getSecondOfMinute() - s.getEndHour().getSecondOfMinute())
                .reduce((a, b) -> a + b).getAsLong();
    }
}
