package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.focus.common.View;

public interface CalendarSettingsView extends View {

    /**
     * Get the EventHandler of the save button.
     * @param spacing : TextField where is written the spacing to save
     * @return EventHandler
     */
    EventHandler<ActionEvent> saveOnAction(TextField spacing);

    /**
     * Used for set the stage of the settings view.
     * @param stage
     */
    void setWindow(Stage stage);
}
