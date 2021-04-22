package oop.focus.calendar.persons.model;

/**
 * This class model a person, which is composed of a name and a degree of kinship.
 */
public interface Person {

    /**
     *  This method is used for get the degree of kinship of the person.
     *  @return a String.
     */
    String getRelationships();

    /**
     *  This method is used for get the name of the person.
     *  @return a String.
     */
    String getName();

    /**
     * This method is used to set the name of the person.
     * @param newValue is teh new name.
     */
    void setName(String newValue);

    /**
     * This method is used to set the degree of kinship of the person.
     * @param newValue is the new degree of kinship.
     */
    void setRelationships(String newValue);

    /**
     * This method is used to get a string that contains the name and the degree of kinship of the person.
     * @return String that represent the name and the degree of kinship of the person.
     */
    String toString();

}
