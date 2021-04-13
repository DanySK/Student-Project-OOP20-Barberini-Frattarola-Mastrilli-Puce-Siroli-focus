package oop.focus.week.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

import java.io.IOException;

public interface PersonsView extends Initializable, View {

    void goToDegree(final ActionEvent event) throws IOException;

    void delete(final ActionEvent event);

    void add(final ActionEvent event);

    void populateTableView();
}
