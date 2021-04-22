package oop.focus.finance.controller;

import oop.focus.common.Controller;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.calendar.persons.model.Person;

import java.util.List;

/**
 * Implementation of a controller interface that deals with the group of group transactions.
 */
public interface GroupController extends Controller {

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
     * @return returns person's credits in euro
     */
    double getCredit(Person person);

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
     * @return finance manager
     */
    FinanceManager getManager();
}
