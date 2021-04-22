package oop.focus.calendarhomepage.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface GenericAddView extends Initializable, View {

    /**
     * This method is use to save an element.
     * @param event is the action event.
     */
    void save(ActionEvent event);

    /**
     * This method is used to go back from the view.
     * @param event is the action event.
     */
    void goBack(ActionEvent event);

    /**
     * This method is use to save an element.
     * @param event is the action event.
     */
    void delete(ActionEvent event);

    /**
     * This method is used to get the root of a view.
     * @return Node that represent the root.
     */
    Node getRoot();

}
