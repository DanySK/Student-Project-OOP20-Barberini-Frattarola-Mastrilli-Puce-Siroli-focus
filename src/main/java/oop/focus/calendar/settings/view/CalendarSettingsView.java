package oop.focus.calendar.settings.view;

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
