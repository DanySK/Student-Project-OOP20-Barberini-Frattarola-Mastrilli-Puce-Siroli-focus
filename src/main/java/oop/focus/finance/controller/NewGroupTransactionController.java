package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.homepage.model.Person;

import java.time.LocalDate;
import java.util.Set;

public interface NewGroupTransactionController extends Controller {

    /**
     * Adds a new group transaction to the database.
     *
     * @param description  of the transaction to add
     * @param madeBy person who made the transaction
     * @param forSet persons for whom the transaction was made
     * @param amount  of the transaction to add
     * @param date of the transaction to add
     * @param hours at which the transaction was made
     * @param minutes at which the transaction was made
     */
    void newGroupTransaction(String description, Person madeBy, Set<Person> forSet, double amount,
                             LocalDate date, int hours, int minutes);

    /**
     * @return an observable list of people saved in the group
     */
    ObservableList<Person> getGroupList();

    /**
     * @return people saved in the group
     */
    ObservableSet<Person> getGroup();
}
