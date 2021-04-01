package oop.focus.homepage.model;

import java.util.Set;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class PersonsManagerImpl implements PersonsManager {

    private final Dao<Person> sd;

    /**
     * This is the class constructor.
     * @param dsi is the DataSource.
     */
    public PersonsManagerImpl(final DataSource dsi) {
        this.sd = dsi.getPersons();
    }

    /**
     * This method is used to add new Person to the database.
     * @param person is the person to add.
     */
    public final void addPerson(final Person person) {
        if (!this.sd.getAll().contains(person)) {
            try {
                this.sd.save(person);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is used to get all the saved persons.
     * @return a list of persons.
     */
    public final Set<Person> getPersons() {
        return this.sd.getAll();
    }

    /**
     * This method is used to remove a person from the database if it's already saved.
     * @param person is the person to remove from the database.
     */
    public final void removePerson(final Person person) {
        try {
            this.sd.delete(person);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }
}
