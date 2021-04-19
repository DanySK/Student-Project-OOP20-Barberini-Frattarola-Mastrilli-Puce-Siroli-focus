package oop.focus.homepage.model;

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

    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.relationships == null) ? 0 : this.relationships.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final PersonImpl other = (PersonImpl) obj;
        if (this.relationships == null) {
            if (other.relationships != null) {
                return false;
            }
        } else if (!this.relationships.equals(other.relationships)) {
              return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
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
