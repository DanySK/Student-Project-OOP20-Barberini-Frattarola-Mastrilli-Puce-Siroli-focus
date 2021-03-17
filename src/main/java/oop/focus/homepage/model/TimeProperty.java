package oop.focus.homepage.model;

import java.util.List;


public interface TimeProperty {

    /**
     * This method is use to verify if an event have a duration greatest than 30 minutes.
     * @param event is the event to check the duration of.
     * @return true if it has false otherwise.
     */
    boolean getMinEventTime(Event event);

    /**
     * This method it is used to check the possibility of adding an event when it has a start date equal to the end date.
     * @param event is the event that must be added.
     * @param eventsList list of events that are scheduled for the date of the event you want to add.
     * @return true if the event is compatible , false otherwise.
     */
    boolean areCompatibleEquals(Event event, List<Event> eventsList);

    /**
     * This method it is used to check the possibility of adding an event when it has a different start date than the end date.
     * @param event is the event that must be added.
     * @param startList list of events that are scheduled for the start date of the event you want to add.
     * @param endList list of events that are scheduled for the end date of the event you want to add.
     * @return true if the event is compatible , false otherwise.
     */
    boolean areCompatibleDifferent(Event event, List<Event> startList, List<Event> endList);

    /**
     * This method is use to know the daily duration expressed in hours of an event.
     * @param event is the event whose duration expressed in hours is to be calculated.
     * @return true if the event duration is greater than 24 hours(that is the duration of a day).
     */
    boolean getHourDuration(Event event);

    /**
     * This method is use to get the distance between two minutes.
     * @return an integer that rappresent the distance.
     */
    int getMinuteDistance();

    /**
     * This method is used to check if an event is valid.
     * An event is valid if, with the same start date and end date, the start time is earlier than the end time , or if the start date and the end date do not coincide.
     * @param e is the event to verify the validity of.
     * @return true if the event is valid false otherwise.
     */
    boolean getValidity(Event e);
}
