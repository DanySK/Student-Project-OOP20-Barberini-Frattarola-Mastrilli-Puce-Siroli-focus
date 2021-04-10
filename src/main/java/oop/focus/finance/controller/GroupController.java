package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.homepage.model.Person;

import java.util.List;
import java.util.Set;

public interface GroupController extends FinanceController {

    /**
     * Show people of group in view.
     */
    void showPeople();

    /**
     * Show group transactions in view.
     */
    void showTansactions();

    /**
     * Adds a new group transaction to the database.
     *
     * @param description of the transaction to add
     * @param madeBy person who made the transaction
     * @param forList persons for whom the transaction was made
     * @param amount of the transaction to add
     */
    void newGroupTransaction(String description, Person madeBy, Set<Person> forList, double amount);

    /**
     * Adds the person to the group.
     *
     * @param person to be added to the group
     */
    void addPerson(Person person);

    /**
     * @param person to be deleted
     */
    void deletePerson(Person person);

    /**
     * @param person whose credits I want to know
     * @return returns person's credits
     */
    int getCredit(Person person);

    /**
     * @param transaction to be deleted
     */
    void deleteTransaction(GroupTransaction transaction);

    /**
     * Performs group transactions that settle all debts.
     */
    void resolve();

    /**
     * If there are no more debts, delete the group and all transactions.
     */
    void reset();

    /**
     * @return finance manager
     */
    FinanceManager getManager();

    /**
     * @return people saved in the database but not yet added to the group transaction group
     */
    ObservableList<Person> getPersonsToAdd();

    /**
     * @return people saved in the databse
     */
    ObservableSet<Person> getGroup();

    /**
     * @return a list of group transactions which, if carried out, resolve all debts
     */
    List<GroupTransaction> getResolvingTransactions();
}
