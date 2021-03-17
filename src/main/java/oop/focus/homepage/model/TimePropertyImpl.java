package oop.focus.homepage.model;



import java.util.List;

import org.joda.time.Duration;

public class TimePropertyImpl implements TimeProperty {

    private final Duration minimumEventDuration;
    private final int minimunDuration = 30;
    private final int minuteDistance = 5;

    /**
     * This is the class constructor.
     */
    public TimePropertyImpl() {
        this.minimumEventDuration = Duration.standardMinutes(this.minimunDuration);
    }

    /**
     * This method is use to verify if an event have a duration greatest than 30 minutes.
     * @param event is the event to check the duration of.
     * @return true if it has false otherwise.
     */
    public final boolean getMinEventTime(final Event event) {
        final Duration durationEvent = new Duration(event.getStartDate().toDateTime(event.getStartHour()), event.getEndDate().toDateTime(event.getEndHour()));
        return durationEvent.isEqual(this.minimumEventDuration) || durationEvent.isLongerThan(this.minimumEventDuration);
    }

    /**
     * This method it is used to check the possibility of adding an event when it has a start date equal to the end date.
     * @param e is the event that must be added.
     * @param list list of events that are scheduled for the date of the event you want to add.
     * @return true if the event is compatible , false otherwise.
     */
    public final boolean areCompatibleEquals(final Event e, final List<Event> list) {
        if (list.isEmpty()) {
            return true;
        }
        if (list.get(0).getStartDate().isEqual(list.get(0).getEndDate()) 
           && 
           e.getStartHour().isBefore(list.get(0).getStartHour())
           &&
           e.getEndHour().isBefore(list.get(0).getStartHour())) {
            return true;
        }
        if (list.get(list.size() - 1).getEndDate().isEqual(list.get(list.size() - 1).getStartDate()) && e.getStartHour().isAfter(list.get(list.size() - 1).getEndHour())) {
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

    /**
     * This method it is used to check the possibility of adding an event when it has a different start date than the end date.
     * @param e is the event that must be added.
     * @param listFirst list of events that are scheduled for the start date of the event you want to add.
     * @param listSecond list of events that are scheduled for the end date of the event you want to add.
     * @return true if the event is compatible , false otherwise.
     */
    public final boolean areCompatibleDifferent(final Event e, final List<Event> listFirst, final List<Event> listSecond) {
        if (this.isPossible(e, listSecond)) {
            if (listFirst.isEmpty()) {
                return true;
            }
            if (listFirst.get(listFirst.size() - 1).getEndDate().isEqual(e.getStartDate()) 
               &&
               e.getStartHour().isAfter(listFirst.get(listFirst.size() - 1).getEndHour())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is use to know the daily duration expressed in hours of an event.
     * @param event is the event whose duration expressed in hours is to be calculated.
     * @return true if the event duration is greater than 24 hours(that is the duration of a day).
     */
    public final boolean getHourDuration(final Event event) {
        final Duration hourDuration = new Duration(event.getStartDate().toDateTime(event.getStartHour()), event.getEndDate().toDateTime(event.getEndHour()));
        return hourDuration.toStandardHours().getHours() < 24;
    }

    /**
     * This method is use to get the distance between two minutes.
     * @return an integer that rappresent the distance.
     */
    public final int getMinuteDistance() {
        return this.minuteDistance;
    }

    /**
     * This method is used to check if an event is valid.
     * An event is valid if, with the same start date and end date, the start time is earlier than the end time , or if the start date and the end date do not coincide.
     * @param e is the event to verify the validity of.
     * @return true if the event is valid false otherwise.
     */
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

}
