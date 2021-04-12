package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import oop.focus.common.View;

/**
 * Interface that models an CalendarView.
 * Used for build the calendar page.
 */
public interface CalendarView extends View {

    /**
     * Used for set the Calendar Page.
     */
    void setCalendarPage();

    /**
     * Used for build the Settings button and his window.
     * @return Button : Settings Button
     */
    Button buildSettingsWindows();

    /**
     * Used for build the Add Event button and his window.
     * @return Button : AddEventButton
     */
    Button buildAddEventButton();

    /**
     * Used for show the panel of the button that we have clicked.
     * @param panelColumn : column where we put the panel
     * @param root : root of the panel
     * @return EventHandler
     */
    EventHandler<ActionEvent> addPanel(VBox panelColumn, Node root);

}
