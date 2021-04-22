package oop.focus.calendar.persons.controller;

import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;

public interface PersonsController extends Controller {

    /**
     * This method is used to add a new person.
     * @param person is the person to add.
     */
    void addPerson(Person person);

    /**
     * This method is used to delete a specific person.
     * @param person is the person to delete.
     */
    void deletePerson(Person person);

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
     * This method is used to get an ObservableList of persons.
     * @return the list of the persons.
     */
    ObservableSet<Person>  getPersons();

}
