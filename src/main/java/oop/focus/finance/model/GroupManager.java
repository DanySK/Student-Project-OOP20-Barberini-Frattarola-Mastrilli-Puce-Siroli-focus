package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.calendar.persons.model.Person;

import java.util.List;

/**
 * Interface that models a group transaction manager,
 * saving the debts and credits of the users.
 */
public interface GroupManager {

    /**
     * Saves a person who already exists in the database in the group transaction group.
     *
     * @param person added to the group
     */
    void addPerson(Person person);

    /**
     * Removes a person from the group transaction group and not the database.
     * If the person has yet to pay off debts or credits, he cannot be eliminated.
     *
     * @param person removed to the group
     */
    void removePerson(Person person);

    /**
     *
     * @param person whose we want to know credit amount
     * @return the credit amount
     */
    int getCredit(Person person);

    /**
     * Saves a group transaction in the database.
     *
     * @param groupTransaction added to group transactions
     */
    void addTransaction(GroupTransaction groupTransaction);

    /**
     * Removes a group transaction from the database.
     *
     * @param groupTransaction removed from group transactions
     */
    void removeTransaction(GroupTransaction groupTransaction);

    /**
     * If there are no more debts, delete the group and all group transactions.
     */
    void reset();

    /**
     * @return the list with the minimum transactions to be carried out to settle the debts.
     */
    List<GroupTransaction> resolveList();

    /**
     * @return the list of people in the group
     */
    ObservableSet<Person> getGroup();

    /**
     * @return the list of group transactions
    */
    ObservableSet<GroupTransaction> getTransactions();

    /**
     * @return the list of all the people saved in database
     */
    ObservableSet<Person> getPersons();
}
