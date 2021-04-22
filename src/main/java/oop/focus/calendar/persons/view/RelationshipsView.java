package oop.focus.calendar.persons.view;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface RelationshipsView extends Initializable, View {

    /**
     * This method is used to set the action of the button that is clicked when you want to add a new relationship.
     */
    void addRelationships();

    /**
     * This method is used to set the action of the button that is clicked when you want to delete a relationship.
     */
    void deleteRelationships();

}
