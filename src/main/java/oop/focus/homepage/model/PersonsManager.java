package oop.focus.homepage.model;


import javafx.collections.ObservableSet;

/**
 * This class is used to model a manager for persons.
 * This class has methods to save, delete and get the saved persons.
 */
public interface PersonsManager {

    /**
     * This method is used to add new Person to the database.
     * @param person is the person to add.
     */
    void addPerson(Person person);

    /**
     * This method is used to get all the saved persons.
     * @return a list of persons.
     */
    ObservableSet<Person> getPersons();

    /**
     * This method is used to remove a person from the database if it's already saved.
     * @param person is the person to remove from the database.
     */
    void removePerson(Person person);
}
