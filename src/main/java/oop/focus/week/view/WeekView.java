package oop.focus.week.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface WeekView extends Initializable, View {

    void lastWeek(ActionEvent event);

    void nextWeek(ActionEvent event);

    void setWeekDays();
}
