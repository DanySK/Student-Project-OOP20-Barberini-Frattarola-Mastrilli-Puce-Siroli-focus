package oop.focus.calendar.week.view;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface NewEventWeekView extends Initializable, View {

    /**
     * This method is used to set the action of the button that is clicked when you want to delete an event.
     */
    void delete();

    /**
     * This method is used to set the action of the button that is clicked when you want to save an event.
     */
    void save();
}
