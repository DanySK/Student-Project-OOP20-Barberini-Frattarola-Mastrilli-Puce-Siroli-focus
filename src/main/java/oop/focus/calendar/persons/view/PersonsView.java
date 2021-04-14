package oop.focus.calendar.persons.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

import java.io.IOException;

public interface PersonsView extends Initializable, View {

    void goToDegree(ActionEvent event) throws IOException;

    void delete(ActionEvent event);

    void add(ActionEvent event);

    void populateTableView();
}
