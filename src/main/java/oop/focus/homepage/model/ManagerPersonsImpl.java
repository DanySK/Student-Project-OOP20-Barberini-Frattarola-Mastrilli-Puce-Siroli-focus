package oop.focus.homepage.model;

import java.util.List;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class ManagerPersonsImpl implements ManagerPersons {

    private final Dao<Person> sd;

    /**
     * This is the class constructor.
     * @param dsi is the DataSource.
     */
    public ManagerPersonsImpl(final DataSource dsi) {
        this.sd = dsi.getPersons();
    }

    /**
     * This method is use to add new Person to the database.
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
     * This method is use to get all the saved persons.
     * @return a list of persons.
     */
    public final List<Person> getPersons() {
        return this.sd.getAll();
    }

    /**
     * This method is use to remove a person from the database if it's already saved.
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
