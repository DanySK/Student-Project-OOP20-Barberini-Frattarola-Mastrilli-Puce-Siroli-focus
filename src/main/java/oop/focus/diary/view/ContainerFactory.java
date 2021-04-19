package oop.focus.diary.view;

import javafx.scene.Node;
import oop.focus.common.View;
import oop.focus.diary.model.TimeScrolling;

import java.util.List;

 /**
 * The interface Container factory defines methods to create different views.
 */
public interface ContainerFactory {
    /**
     * Return new {@link View},obtained by combining vertically the nodes in input.
     * @param list  the list of nodes to add in the new View
     * @return  the View
     */
    View mergeVertically(List<Node> list);

    /**
     * Return a new {@link View}, obtained by combining horizontally the nodes in input.
     * @param list  the list of nodes to add in the new View
     * @return  the View
     */
    View mergeHorizontally(List<Node> list);
}
