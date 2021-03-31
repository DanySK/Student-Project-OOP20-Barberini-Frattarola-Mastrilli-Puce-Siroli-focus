package oop.focus.homepage.model;

import java.util.List;

public interface RelationshipsManager {

    /**
    * This method is used to add a new degree of kinship.
    * @param degree is the degree of kinship to add.
    */
    void add(String degree);

    /**
     * This method is used to add new degrees of relationship from a list of persons.
     * @param personsList is the list from which to take the degrees of kinship to add. 
     */
    void addAll(List<Person> personsList);

    /**
     * This method is used to get all degrees of relationship saved.
     * @return a set of string that represent all the saved degrees of relationship.
     */
    List<String> getAll();

    /**
     * This method is used to remove a degree of kinship from all the saved degrees of kinship.
     * @param degree is the degree to remove.
     */
    void remove(String degree);

}
