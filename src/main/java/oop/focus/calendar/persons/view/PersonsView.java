package oop.focus.calendar.persons.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;


public interface PersonsView extends Initializable, View {

    void add(ActionEvent event);

    void delete(ActionEvent event);

    void populateTableView();
}
