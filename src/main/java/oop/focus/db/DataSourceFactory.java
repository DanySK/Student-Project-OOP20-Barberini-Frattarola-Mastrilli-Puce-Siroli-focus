package oop.focus.db;

import oop.focus.diary.model.DailyMood;
import oop.focus.diary.model.ToDoAction;
import oop.focus.fidelitycard.FidelityCard;
import oop.focus.finance.Account;
import oop.focus.finance.Category;
import oop.focus.finance.GroupTransaction;
import oop.focus.finance.Transaction;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.Person;

/**
 * Defines the methods to create an Object to access different types of data
 * that can be stored into a data source.
 */
public interface DataSourceFactory {
    /**
     * Gets a data access object for {@link Person} type elements.
     *
     * @return the persons dao
     */
    SingleDao<Person> getPersons();
    /**
     * Gets a data access object for {@link Category} type elements.
     *
     * @return the categories dao
     */
    SingleDao<Category> getCategories();
    /**
     * Gets a data access object for {@link Account} type elements.
     *
     * @return the accounts dao
     */
    SingleDao<Account> getAccounts();
    /**
     * Gets a data access object for {@link String} type elements, representing a color.
     *
     * @return the colors dao
     */
    SingleDao<String> getColors();
    /**
     * Gets a data access object for {@link String} type elements, representing a relationship.
     *
     * @return the relationships dao
     */
    SingleDao<String> getRelationships();
    /**
     * Gets a data access object for {@link DailyMood} type elements.
     *
     * @return the daily moods dao
     */
    SingleDao<DailyMood> getDailyMoods();
    /**
     * Gets a data access object for {@link Event} type elements.
     *
     * @return the events dao
     */
    SingleDao<Event> getEvents();
    /**
     * Gets a data access object for {@link HotKey} type elements.
     *
     * @return the hot keys dao
     */
    SingleDao<HotKey> getHotKeys();
    /**
     * Gets a data access object for {@link Transaction} type elements.
     *
     * @return the transactions dao
     */
    SingleDao<Transaction> getTransactions();
    /**
     * Gets a data access object for {@link GroupTransaction} type elements.
     *
     * @return the group transactions dao
     */
    SingleDao<GroupTransaction> getGroupTransactions();
    /**
     * Gets a data access object for {@link ToDoAction} type elements.
     *
     * @return the to do list dao
     */
    SingleDao<ToDoAction> getToDoList();
    /**
     * Gets a data access object for {@link FidelityCard} type elements.
     *
     * @return the fidelity cards dao
     */
    SingleDao<FidelityCard> getFidelityCards();
}
