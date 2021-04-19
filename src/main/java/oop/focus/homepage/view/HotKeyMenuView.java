package oop.focus.homepage.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface HotKeyMenuView extends Initializable, View {

    /**
     * This method is used to add a new hot key to the database.
     * @param event is the action event.
     */
    void addNewHotKey(ActionEvent event);

    /**
     * This method is used to delet the selected row.
     * @param event is the action event.
     */
    void deletSelectedRowItem(ActionEvent event);

    /**
     * This method is use to go back.
     * @param event is the action event.
     */
    void goBack(ActionEvent event);

    /**
     * This method is use to populate the table view.
     */
    void populateTableView();
}
