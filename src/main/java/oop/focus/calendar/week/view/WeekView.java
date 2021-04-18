package oop.focus.calendar.week.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.calendar.model.Format;
import oop.focus.common.View;

public interface WeekView extends Initializable, View {

    /**
     * This method is used to set the action of the button that is clicked when you want to go to the last week.
     * @param event is the action event.
     */
    void lastWeek(ActionEvent event);

    /**
     * This method is used to set the action of the button that is clicked when you want to go to the next week.
     * @param event is the action event.
     */
    void nextWeek(ActionEvent event);

    /**
     * This method is used to set the week days.
     */
    void setWeekDays();

    void setDayProperty(Format format, double spacing);
}
