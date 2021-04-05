package oop.focus.calendar.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public interface CalendarController {

    /**
     * Used for return the Month box.
     * @return Month box
     */
    HBox buildMonth();

    /**
     * Used for build the Settings button and his window.
     * @return settings button
     */
    Button buildSettingsWindows();
}
