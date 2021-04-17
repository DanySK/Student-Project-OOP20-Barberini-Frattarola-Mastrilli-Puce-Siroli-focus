package oop.focus.calendar.persons.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;


public interface PersonsView extends Initializable, View {

    /**
     * This method is used to set the action of the button that is clicked when you want to add a new person.
     * @param event is the action event.
     */
    void add(ActionEvent event);

    /**
     * This method is used to set the action of the button that is clicked when you want to delete a person.
     * @param event is the action event.
     */
    void delete(ActionEvent event);

    /**
     * This method is used to populate the table view with all of the saved persons.
     */
    void populateTableView();
}
