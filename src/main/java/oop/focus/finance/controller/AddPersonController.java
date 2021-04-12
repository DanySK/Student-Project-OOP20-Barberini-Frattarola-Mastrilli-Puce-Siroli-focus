package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;

public interface AddPersonController extends Controller {

    /**
     * Adds the person to the group.
     *
     * @param person to be added to the group
     */
    void addPerson(Person person);

    /**
     * @return people saved in the database but not yet added to the group transaction group
     */
    ObservableList<Person> getPersonsToAdd();

    /**
     * @return finance manager
     */
    DataSource getDb();
}
