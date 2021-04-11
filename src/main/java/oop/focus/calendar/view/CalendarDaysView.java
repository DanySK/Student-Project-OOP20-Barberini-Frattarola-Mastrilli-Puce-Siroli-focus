package oop.focus.calendar.view;

import javafx.scene.layout.VBox;
import oop.focus.common.View;


public interface CalendarDaysView extends View {

    /**
     * Used for create day view for the Calendar.
     *
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
