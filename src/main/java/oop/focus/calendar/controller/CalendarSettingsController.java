package oop.focus.calendar.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oop.focus.calendar.model.Format;


public interface CalendarSettingsController {

    /**
     * Used for set the format of the hours.
     * @param format
     */
    void setFormat(Format format);

    /**
     * Used for get the format of the hours.
     * @return format
     */
    Format getFormat();

    /**
     * Used for check if the spacing isn't under a certain threshold and if is a number.
     * 
     * @param spacing : is the string of the TextField
     * @return true if pass the check or false if not
     */
    boolean checkSpacing(String spacing);

    /**
     * Used for set the space between the hours.
     * @param spacing
     */
    void setSpacing(double spacing);

    /**
     * Used for get the spacing between the hours.
     * @return spacing
     */
    double getSpacing();


    /**
     * Used for get the settings box.
     * @return vbox
     */
    VBox getSettings();

    /**
     * Used for set the stage of the settings view.
     * @param stage
     */
    void setWindow(Stage stage);

    /**
     * Get the EventHandler of the save button.
     * @param spacing : TextField where is written the spacing to save
     * @return EventHandler
     */
    EventHandler<ActionEvent> saveOnAction(TextField spacing);
}
