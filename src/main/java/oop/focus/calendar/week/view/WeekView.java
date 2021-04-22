package oop.focus.calendar.week.view;

import javafx.fxml.Initializable;
import oop.focus.calendar.model.Format;
import oop.focus.common.View;

public interface WeekView extends Initializable, View {

    /**
     * This method is used to set the week days.
     */
    void setWeekDays();

    /**
     * This method is used to set the properties (format and spacing) of the days of the week.
     * @param format is the format of the hour, that can be extended or normal.
     * @param spacing is the spacing between every hour.
     */
    void setDayProperty(Format format, double spacing);
}
