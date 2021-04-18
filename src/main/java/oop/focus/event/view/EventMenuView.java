package oop.focus.event.view;

import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface EventMenuView extends View, Initializable {

    /**
     * This method is used to delete a specific event.
     */
    void deleteItem();

    /**
     * This method is used to see the information of a specific event.
     */
    void viewInformation();

    /**
     * This method is used to refresh the table view.
     */
    void refreshTable();
}
