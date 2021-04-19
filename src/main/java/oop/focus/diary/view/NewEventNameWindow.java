package oop.focus.diary.view;


import oop.focus.common.View;

/**
 * The interface can be used to set properties of a new window, which can be used to add a new event's name,
 * if there isn't yet.
 */
public interface NewEventNameWindow extends View {
    /**
     * The method returns a new event's name, written by the user.
     * @return  the event's name for which the counter is computed time
     */
    String getText();
}
