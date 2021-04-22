package oop.focus.calendar.persons.controller;

import java.util.List;

import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;

public interface RelationshipsController extends Controller {

    /**
     * This method is used to add a new relationship.
     * @param relationship is the relationship to add.
     */
    void addRelationship(String relationship);

    /**
     * This method is used to delete a specific relationship.
     * @param relationship is the relationship to delete.
     */
    void deleteRelationship(String relationship);

    /**
     * This method is used to get an ObservableList of string.
     * @return the list of the string that represent the relationships.
     */
    ObservableSet<String> getDegree();

    /**
     * This method is used to get the dsi.
     * @return the data source.
     */
    DataSource getDsi();

    /**
     * This method is used to get all the used degree of kinship from all the saved persons.
     * @return List of String that contains all the used degrees of kinship.
     */
    List<String> getPersons();
}
