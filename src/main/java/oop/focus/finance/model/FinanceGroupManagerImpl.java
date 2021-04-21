package oop.focus.finance.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.calendar.persons.model.Person;

/**
 * Class that implements group transaction group management.
 */
public class FinanceGroupManagerImpl implements Manager<Person> {

    private final Dao<Person> group;

    public FinanceGroupManagerImpl(final DataSource db) {
        this.group = db.getGroup();
    }

    /**
     * Saves a person who already exists in the database in the group transaction group.
     *
     * @param person added to the group
     */
    @Override
    public final void add(final Person person) {
        try {
            this.group.save(person);
        } catch (final DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a person from the group transaction group.
     *
     * @param person removed from group transactions
     */
    @Override
    public final void remove(final Person person) {
        try {
            this.group.delete(person);
        } catch (final DaoAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the list of people in the group
     */
    @Override
    public final ObservableSet<Person> getElements() {
        return this.group.getAll();
    }

}

