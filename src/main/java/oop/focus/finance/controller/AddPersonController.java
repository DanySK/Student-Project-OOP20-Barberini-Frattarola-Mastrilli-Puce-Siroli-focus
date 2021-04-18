package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;

/**
 * Implementation of a controller interface that implements the methods useful
 * for adding a person to the group of group transactions.
 */
public interface AddPersonController extends Controller {

    /**
     * Adds a person to the group of transactions group.
     *
     * @param person to be added to the group
     */
    void addPerson(Person person);

    /**
     * @return people saved in the database but not yet added to the group transaction group
     */
    ObservableList<Person> getPersonsToAdd();

    /**
     * @return database
     */
    DataSource getDb();
}
