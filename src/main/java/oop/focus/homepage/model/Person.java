package oop.focus.homepage.model;

/**
 * This class adds new methods to manage hotKeys.
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

    void setName(String newValue);

    void setRelationships(String newValue);


}
