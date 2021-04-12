package oop.focus.week.view;

import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface PersonsView extends Initializable, View {

    void goToDegree();

    void delete();

    void add();

    void populateTableView();
}
