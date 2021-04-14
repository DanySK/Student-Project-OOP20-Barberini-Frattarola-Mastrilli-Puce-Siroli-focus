package oop.focus.calendar.week.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface NewEventWeekView extends Initializable, View {

    void delete(ActionEvent event);

    Node getRoot();

    void save(ActionEvent event) throws IOException;
}
