package oop.focus.finance.controller;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
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
     * @return relationships saved in the databse
     */
    ObservableList<String> getRelationships();

    /**
     * @return people saved in the databse
     */
    ObservableSet<Person> getGroup();

    /**
     * Adds the person to the group.
     *
     * @param name of the person added
     * @param relationship of the person added
     */
    void newPerson(String name, String relationship);

    /**
     * Adds a new group transaction to the database.
     *
     * @param description of the transaction to add
     * @param madeBy person who made the transaction
     * @param forList persons for whom the transaction was made
     * @param amount of the transaction to add
     */
    void newGroupTransaction(String description, Person madeBy, Set<Person> forList, int amount);

    /**
     * @return a list of group transactions which, if carried out, resolve all debts
     */
    List<GroupTransaction> getResolvingTransactions();

    /**
     * Performs group transactions that settle all debts.
     */
    void resolve();
}
