package oop.focus.calendar.view;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface CalendarOptionsView {

    /**
     * 
     * @return options
     */
    VBox getOptions();

    /**
     * 
     * @param stage
     */
    void setWindow(Stage stage);
}
