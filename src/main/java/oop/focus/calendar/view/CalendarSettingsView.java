package oop.focus.calendar.view;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface CalendarSettingsView {

    /**
     * Used for get the settings view.
     * @return settings vbox
     */
    VBox getSettings();

    /**
     * Used for set the stage of the settings view.
     * @param stage
     */
    void setWindow(Stage stage);
}
