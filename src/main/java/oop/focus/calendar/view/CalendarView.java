package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
     * Used for show the dedicated panel.
     * @param panelcolumn : column where we put the panel
     * @param root
     * @return EventHandler
     */
    EventHandler<ActionEvent> addPanel(VBox panelcolumn, Node root);

}
