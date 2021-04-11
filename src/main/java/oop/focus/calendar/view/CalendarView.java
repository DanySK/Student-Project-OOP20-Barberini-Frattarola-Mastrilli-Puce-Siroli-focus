package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import oop.focus.common.View;

public interface CalendarView extends View {


    /**
     * Used for set the Calendar Page.
     */
    void setCalendarPage();

    /**
     * Used for build the Settings button and his window.
     * @return button
     */
    Button buildSettingsWindows();

    /**
     * Used for build the Add Event button and his window.
     * @return button
     */
    Button buildAddEventButton();

    /**
     * Used for show the month panel.
     * @param panelcolumn : column where we put the panel
     * @return EventHandler
     */
    EventHandler<ActionEvent> monthPanel(VBox panelcolumn);

    /**
     * Used for show the week panel.
     * @param panelcolumn : column where we put the panel
     * @return EventHandler
     */
    EventHandler<ActionEvent> weekPanel(VBox panelcolumn);

    /**
     * Used for show the person panel.
     * @param panelcolumn : column where we put the panel
     * @return EventHandler
     */
    EventHandler<ActionEvent> personPanel(VBox panelcolumn);

    /**
     * Used for show the statistics panel.
     * @param panelcolumn : column where we put the panel
     * @return EventHandler
     */
    EventHandler<ActionEvent> statisticsPanel(VBox panelcolumn);
}
