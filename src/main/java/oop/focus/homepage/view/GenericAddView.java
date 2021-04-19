package oop.focus.homepage.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface GenericAddView extends Initializable, View {

    /**
     * This method is use to save an element.
     * @param event is the action event.
     * @throws IOException if is impossible to save the element.
     */
    void save(ActionEvent event) throws IOException;

    /**
     * This method is used to go back from the view.
     * @param event is the action event.
     */
    void goBack(ActionEvent event);

    /**
     * This method is use to save an element.
     * @param event is the action event.
     * @throws IOException if is impossible to save the element.
     */
    void delete(ActionEvent event);

    /**
     * This method is used to get the root of a view.
     * @return Node that represent the root.
     */
    Node getRoot();

}
