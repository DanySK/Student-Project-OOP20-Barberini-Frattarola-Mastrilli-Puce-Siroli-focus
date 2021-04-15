package oop.focus.diary.controller;

import javafx.collections.ObservableSet;

/**
 * This interface takes care of managing the part of counter that works with events.
 */
public interface EventCounterController {
    /**
     * The method returns an observableSet with all events' names saved in database.
     * @return  an observable set of names of events
     */
    ObservableSet<String> getEventsNames();

    /**
     * The method sets the disable field of start and stop buttons.
     * @param disable   if true the buttons are disable, if false they are enabled.
     */
    void disableButton(boolean disable);

    /**
     * The method sets the event chosen by the user, so that could be set the counter.
     * @param eventChosen   the name of event, chosen by the user, that the counter is computed.
     */
    void setChosen(String eventChosen);

    /**
     * The method adds new event's name.
     * @param newEvent  the event's name to be saved.
     */
    void addEvent(String newEvent);
}
