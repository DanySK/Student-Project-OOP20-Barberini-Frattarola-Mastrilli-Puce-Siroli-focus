package oop.focus.calendar.week.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface NewEventWeekView extends Initializable, View {

    /**
     * This method is used to set the action of the button that is clicked when you want to delete an event.
     * @param event is the action event.
     */
    void delete(ActionEvent event);

    /**
     * This method is used to get the root of the view.
     * @return Node that represent the root.
     */
    Node getRoot();

    /**
     * This method is used to set the action of the button that is clicked when you want to save an event.
     * @param event is the action event.
     */
    void save(ActionEvent event) throws IOException;
}
