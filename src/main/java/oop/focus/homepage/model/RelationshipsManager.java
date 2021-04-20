package oop.focus.homepage.model;

import javafx.collections.ObservableSet;

/**
 * This class is used to model a manager for the relationships.
 * This class has methods to save, delete and find relationships.
 */
public interface RelationshipsManager {

    /**
    * This method is used to add a new degree of kinship.
    * @param degree is the degree of kinship to add.
    */
    void add(String degree);

    /**
     * This method is used to get all degrees of relationship saved.
     * @return a set of string that represent all the saved degrees of relationship.
     */
    ObservableSet<String> getAll();

    /**
     * This method is used to remove a degree of kinship from all the saved degrees of kinship.
     * @param degree is the degree to remove.
     */
    void remove(String degree);

}
