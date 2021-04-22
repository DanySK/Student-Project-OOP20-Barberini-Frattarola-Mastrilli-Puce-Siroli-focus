package oop.focus.calendar.persons.model;

import java.util.Objects;

/**
 * 
 * This is a class that model a person , wich has a name , a surname and a degreeOfKinship.
 *
 */
public class PersonImpl implements Person {

    private String name;
    private String relationships;

    /**
     * This is the class constructor.
     * @param name is the name of the person to be created.
     * @param degree it is the degree of kinship of the person to be created.
     */
    public PersonImpl(final String name, final String degree) {
        this.name = name;
        this.relationships = degree;
    }

    public final String getRelationships() {
        return this.relationships;
    }

    public final String getName() {
        return this.name;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final PersonImpl person = (PersonImpl) o;
        return this.name.equals(person.name) && this.relationships.equals(person.relationships);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name, this.relationships);
    }

    @Override
    public final void setName(final String newValue) {
        this.name = newValue;
    }

    @Override
    public final void setRelationships(final String newValue) {
        this.relationships = newValue;
    }

    public final String toString() {
        return this.name + " " + this.relationships;
    }

    public final String toStringForName() {
        return " " + this.name;
    }
}
