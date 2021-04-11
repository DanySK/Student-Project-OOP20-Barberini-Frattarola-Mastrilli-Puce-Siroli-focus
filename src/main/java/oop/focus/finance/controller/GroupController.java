package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.common.Controller;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface GroupController extends Controller {

    /**
     * Adds a new group transaction to the database.
     *
     * @param description  of the transaction to add
     * @param madeBy person who made the transaction
     * @param forSet persons for whom the transaction was made
     * @param amount  of the transaction to add
     * @param date of the transaction to add (dafult today)
     * @param hours at which the transaction was made (default 00)
     * @param minutes at which the transaction was made (default 00)
     */
    void newGroupTransaction(String description, Person madeBy, Set<Person> forSet, String amount,
                             LocalDate date, String hours, String minutes);

    /**
     * Adds the person to the group.
     *
     * @param person to be added to the group
     */
    void addPerson(Person person);

    /**
     * @param transaction to be deleted
     */
    void deleteTransaction(GroupTransaction transaction);

    /**
     * @param person to be deleted
     */
    void deletePerson(Person person);

    /**
     * Show group transactions in view.
     */
    void showTansactions();

    /**
     * Show people of group in view.
     */
    void showPeople();

    /**
     * @param person whose credits I want to know
     * @return returns person's credits
     */
    String getCredit(Person person);

    /**
     * Performs group transactions that settle all debts.
     */
    void resolve();

    /**
     * If there are no more debts, delete the group and all transactions.
     */
    void reset();

    /**
     * @return the list of all group transactions saved in the database, sorted by time
     */
    List<GroupTransaction> getSortedGroupTransactions();

    /**
     * @return the list of all persons added to the froup saved in the database, sorted by name
     */
    List<Person> getSortedGroup();

    /**
     * @return a list of group transactions which, if carried out, resolve all debts
     */
    List<GroupTransaction> getResolvingTransactions();

    /**
     * @return people saved in the database but not yet added to the group transaction group
     */
    ObservableList<Person> getPersonsToAdd();

    /**
     * @return people saved in the group
     */
    ObservableSet<Person> getGroup();

    /**
     * @return people saved in the databse
     */
    ObservableSet<Person> getPersons();

    /**
     * @return finance manager
     */
    FinanceManager getManager();

    /**
     * @return an observable list of people saved in the group
     */
    ObservableList<Person> getGroupList();
}
