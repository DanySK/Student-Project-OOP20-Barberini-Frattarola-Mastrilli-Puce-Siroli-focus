package oop.focus.homepage.model;

import java.util.HashSet;
import java.util.Set;

public class ManagerDegreeOfKinshipImpl implements ManagerDegreeOfKinship {

    private final Set<String> degreeOfKinship;

    public ManagerDegreeOfKinshipImpl() {
        this.degreeOfKinship = new HashSet<>();
    }

    public final void add(final String degree) {
        if (!this.degreeOfKinship.contains(degree)) {
            this.degreeOfKinship.add(degree);
        }
    }

    public final Set<String> getAll() {
        return this.degreeOfKinship;
    }

    public final void remove(final String degree) {
        if (!this.degreeOfKinship.contains(degree)) {
            this.degreeOfKinship.remove(degree);
        }
    }

}
