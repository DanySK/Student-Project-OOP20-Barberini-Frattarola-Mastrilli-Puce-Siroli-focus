package oop.focus.calendar.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.focus.common.View;

/**
 * Interface that models an Settings View.
 * An Settings View is a windows where you can set the Spacing and the Format,
 * The spacing need to be minimum 30
 * The format can be choose between Normal or Extended
 */
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

    /**
     * Used for set the windows error of settings.
     * @param string : Error phrases.
     */
    void windowsError(String string);
}
