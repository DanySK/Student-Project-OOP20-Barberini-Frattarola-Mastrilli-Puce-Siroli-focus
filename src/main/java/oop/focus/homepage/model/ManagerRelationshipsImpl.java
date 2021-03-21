package oop.focus.homepage.model;

import java.util.List;

import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public class ManagerRelationshipsImpl implements ManagerRelationships {

    private final Dao<String> sd;

    /**
     * This is the class constructor.
     * @param dsi is the data source.
     */
    public ManagerRelationshipsImpl(final DataSource dsi) {
        this.sd = dsi.getRelationships();
    }

    /**
     * This method is use to add a new degree of kinship.
     * @param degree is the degree of kinship to add.
     */
    public final void add(final String degree) {
        if (!this.sd.getAll().contains(degree)) {
            try {
                this.sd.save(degree);
            } catch (DaoAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is used to add new degrees of relationship from a list of persons.
     * @param personsList is the list from which to take the degrees of kinship to add. 
     */
    public final void addAll(final List<Person> personsList) {
        for (final Person person : personsList) {
            this.add(person.getDegreeOfKinship());
        }
    }

    /**
     * This method is use to get all degrees of relationship saved.
     * @return a set of string that represent all the saved degrees of relationship.
     */
    public final List<String> getAll() {
        return this.sd.getAll();
    }

    /**
     * This method is use to remove a degree of kinship from all the saved degrees of kinship.
     * @param degree is the degree to remove.
     */
    public final void remove(final String degree) {
        try {
            this.sd.delete(degree);
        } catch (DaoAccessException e) {
            e.printStackTrace();
        }
    }

}
