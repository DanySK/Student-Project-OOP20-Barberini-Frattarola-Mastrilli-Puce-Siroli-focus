package oop.focus.calendar.persons.controller;

import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;

public interface RelationshipsController {

    /**
     * This method is used to add a new relationship.
     * @param relationship is the relationship to add.
     */
    void addRelationship(String relationship);

    /**
     * This method is used to delete a specific relationship.
     * @param relationship is the relationship to delete.
     */
    void deleteRelationship(String relationship) throws IllegalStateException;

    /**
     * This method is used to get an ObservableList of string.
     * @return the list of the string that represent the relationships.
     */
    ObservableList<String> getDegree();

    /**
     * This method is used to get the dsi.
     * @return the data source.
     */
    DataSource getDsi();

    /**
     * This method is used to get the view.
     * @return View.
     */
    View getView();
}
