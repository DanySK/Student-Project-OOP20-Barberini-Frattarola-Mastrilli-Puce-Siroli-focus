package oop.focus.homepage.model;
//import java.util.*;

/**
 * 
 * This is a class that model a person , wich has a name , a surname and a degreeOfKinship.
 *
 */
public class PersonImpl implements Person {

    private final String name;
    private final String surname;
    private final String degreeOfKinship;

    /**
     * This is the class constructor.
     * @param name is the name of the person to be created.
     * @param surname is the surname of the person to be created.
     * @param degree it is the degree of kinship of the person to be created.
     */
    public PersonImpl(final String name, final String surname, final String degree) {
        this.name = name;
        this.surname = surname;
        this.degreeOfKinship = degree;
    }

    /**
     * This method is use for get the name of the person.
     * @return a String that rappresent the person name.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * This method is use for get the surname of the person.
     * @return a String that rappresent the person surname.
     */
    public final String getSurname() {
        return this.surname;
    }

    /**
     * This method is use for get the degree of kinship of the person.
     * @return a String that rappresent the person degree of kinship.
     */
    public final String getDegreeOfKinship() {
        return this.degreeOfKinship;
    }
}
