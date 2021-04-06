package oop.focus.calendar.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public interface CalendarController {


    /**
     * Used for build the Settings button and his window.
     * @return settings button
     */
    Button buildSettingsWindows();

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
}
