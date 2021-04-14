package oop.focus.calendar.day.view;

import javafx.scene.layout.VBox;
import oop.focus.common.View;

/**
 * Interface that models a Day View.
 * An Day View is a windows where you can build the day,
 * Is composed by 3 main box
 * the first box is composed by 2 label with the day information (name and number)
 * the second box is composed by a label with the daily events
 * the third box is composed by the hours box and the event box
 */
public interface CalendarDaysView extends View {

    /**
     * Used for create day view for the Calendar.
     */
    void buildDay();

    /**
     * Used for set the container panel of the day view.
     * @param container : is the day box
     */
    void setContainer(VBox container);

    /**
     * Get the VBox with all the object of the day.
     * @return VBox view of the day
     */
    VBox getContainer();

    /**
     * Used for set the string where are written the daily events.
     */
    void setDailyEvent();

    /**
     * Used for get the string where are written the daily events.
     * @return String
     */
    String getDailyEvent();


}
