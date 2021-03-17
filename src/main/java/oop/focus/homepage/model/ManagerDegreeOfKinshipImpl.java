package oop.focus.homepage.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagerDegreeOfKinshipImpl implements ManagerDegreeOfKinship {

    private final Set<String> degreeOfKinship;

    /**
     * This is the class constructor.
     */
    public ManagerDegreeOfKinshipImpl() {
        this.degreeOfKinship = new HashSet<>();
    }

    /**
     * This method is use to add a new degree of kinship.
     * @param degree is the degree of kinship to add.
     */
    public final void add(final String degree) {
        this.degreeOfKinship.add(degree);
    }

    /**
     * This method is used to add new degrees of relationship from a list of persons.
     * @param personsList is the list from which to take the degrees of kinship to add. 
     */
    public final void addAll(final List<Person> personsList) {
        for (final Person person : personsList) {
            if (!this.degreeOfKinship.contains(person.getDegreeOfKinship())) {
                this.add(person.getDegreeOfKinship());
            }
        }
    }

    /**
     * This method is use to get all degrees of relationship saved.
     * @return a set of string that represent all the saved degrees of relationship.
     */
    public final Set<String> getAll() {
        return this.degreeOfKinship;
    }

    /**
     * This method is use to remove a degree of kinship from all the saved degrees of kinship.
     * @param degree is the degree to remove.
     */
    public final void remove(final String degree) {
        if (!this.degreeOfKinship.contains(degree)) {
            this.degreeOfKinship.remove(degree);
        }
    }

}
