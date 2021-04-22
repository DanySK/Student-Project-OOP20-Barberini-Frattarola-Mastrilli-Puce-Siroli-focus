package oop.focus.db;

import oop.focus.diary.model.DailyMood;
import oop.focus.diary.model.ToDoAction;
import oop.focus.fidelitycard.FidelityCard;
import oop.focus.finance.model.Account;
import oop.focus.finance.model.Category;
import oop.focus.finance.model.GroupTransaction;
import oop.focus.finance.model.QuickTransaction;
import oop.focus.finance.model.Transaction;
import oop.focus.event.model.Event;
import oop.focus.calendarhomepage.model.HotKey;
import oop.focus.calendar.persons.model.Person;

/**
 * Defines the methods to create an Object to access different types of data
 * needed by the application context, that can be stored into a data source.
 */
public interface DataSource {
    /**
     * Gets a data access object for {@link Person} type elements.
     *
     * @return the persons dao
     */
    Dao<Person> getPersons();

    /**
     * Gets a data access object for {@link Category} type elements.
     *
     * @return the categories dao
     */
    Dao<Category> getCategories();

    /**
     * Gets a data access object for {@link Account} type elements.
     *
     * @return the accounts dao
     */
    Dao<Account> getAccounts();

    /**
     * Gets a data access object for {@link String} type elements, representing a color.
     *
     * @return the colors dao
     */
    Dao<String> getColors();

    /**
     * Gets a data access object for {@link String} type elements, representing a relationship.
     *
     * @return the relationships dao
     */
    Dao<String> getRelationships();

    /**
     * Gets a data access object for {@link DailyMood} type elements.
     *
     * @return the daily moods dao
     */
    Dao<DailyMood> getDailyMoods();

    /**
     * Gets a data access object for {@link Event} type elements.
     *
     * @return the events dao
     */
    Dao<Event> getEvents();

    /**
     * Gets a data access object for {@link HotKey} type elements.
     *
     * @return the hot keys dao
     */
    Dao<HotKey> getHotKeys();

    /**
     * Gets a data access object for {@link Transaction} type elements.
     *
     * @return the transactions dao
     */
    Dao<Transaction> getTransactions();

    /**
     * Gets a data access object for {@link GroupTransaction} type elements.
     *
     * @return the group transactions dao
     */
    Dao<GroupTransaction> getGroupTransactions();

    /**
     * Gets a data access object for {@link ToDoAction} type elements.
     *
     * @return the to do list dao
     */
    Dao<ToDoAction> getToDoList();

    /**
     * Gets a data access object for {@link FidelityCard} type elements.
     *
     * @return the fidelity cards dao
     */
    Dao<FidelityCard> getFidelityCards();

    /**
     * Gets a data access object for a group of {@link Person} type elements.
     *
     * @return the persons group dao
     */
    Dao<Person> getGroup();

    /**
     * Gets a data access object for  {@link QuickTransaction} type elements.
     *
     * @return the quick transactions dao
     */
    Dao<QuickTransaction> getQuickTransactions();

}
