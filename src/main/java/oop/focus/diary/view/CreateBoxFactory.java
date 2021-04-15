package oop.focus.diary.view;

import javafx.scene.Node;
import oop.focus.common.View;

import java.util.List;

public interface CreateBoxFactory {
    /**
     * Return new View, which is a VBox formed by the nodes in input.
     * @param list  the list of nodes to add in the VBox
     * @return  the View
     */
    View createVBox(List<Node> list);

    /**
     * Return a new View, which is a HBox formed by the nodes in input.
     * @param list  the list of nodes to add in the HBox
     * @return  the View
     */
    View createHBox(List<Node> list);
}
