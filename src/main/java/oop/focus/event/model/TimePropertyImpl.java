package oop.focus.event.model;

import java.util.List;

import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

public class TimePropertyImpl implements TimeProperty {

    private final Duration minimumEventDuration;

    /**
     * This is the class constructor.
     */
    public TimePropertyImpl() {
        this.minimumEventDuration = Duration.standardMinutes(Costants.MININUM_DURATION);
    }

    public final boolean areCompatibleDifferent(final Event e, final List<Event> listFirst, final List<Event> listSecond) {
        if (this.isPossible(e, listSecond)) {
            return listFirst.isEmpty() || listFirst.get(listFirst.size() - 1).getEndDate().isEqual(e.getStartDate()) && e.getStartHour().isAfter(listFirst.get(listFirst.size() - 1).getEndHour());
        }
        return false;
    }

    public final boolean areCompatibleEquals(final Event e, final List<Event> list) {
        if (list.isEmpty() || list.get(0).getStartDate().isEqual(list.get(0).getEndDate()) 
           && e.getStartHour().isBefore(list.get(0).getStartHour())
           && e.getEndHour().isBefore(list.get(0).getStartHour())
           || list.get(list.size() - 1).getEndDate().isEqual(list.get(list.size() - 1).getStartDate()) && e.getStartHour().isAfter(list.get(list.size() - 1).getEndHour())) {
            return true;
        } else {
            for (int i = 0; i <= list.size() - 2; i++) {
                if (e.getStartHour().isAfter(list.get(i).getEndHour()) && e.getEndHour().isBefore(list.get(i + 1).getStartHour())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean getHourDuration(final Event event) {
        final Duration hourDuration = new Duration(event.getStartDate().toDateTime(event.getStartHour()), event.getEndDate().toDateTime(event.getEndHour()));
        return hourDuration.toStandardHours().getHours() < Costants.HOUR_OF_DAY;
    }

    public final boolean getMinEventTime(final Event event) {
        final Duration durationEvent = new Duration(event.getStartDate().toDateTime(event.getStartHour()), event.getEndDate().toDateTime(event.getEndHour()));
        return durationEvent.isEqual(this.minimumEventDuration) || durationEvent.isLongerThan(this.minimumEventDuration);
    }

    public final boolean getStart(final LocalDateTime date, final Event e) {
        return e.getStart().isBefore(date) && e.getEnd().isAfter(date) || e.getStart().isEqual(date) || e.getEnd().isEqual(date);
    }

    public final boolean getValidity(final Event e) {
        return e.getStartDate().isEqual(e.getEndDate()) && e.getEndHour().isAfter(e.getStartHour()) || e.getEndDate().isAfter(e.getStartDate());
    }

    /**
     * This method is used to check whether it is possible to do so on the day in which an event to be entered ends.
     * @param e is the event on which to check.
     * @param listSecond contains all the events that take place on the day the event ends.
     * @return true the event can be added on the day it ends, false otherwise.
     */
    private boolean isPossible(final Event e, final List<Event> listSecond) {
        return listSecond.isEmpty() || listSecond.get(0).getStartHour().isAfter(e.getEndHour());
    }

    private static class Costants {
        public static final int HOUR_OF_DAY = 24;
        public static final int MININUM_DURATION = 30;
    }
}
