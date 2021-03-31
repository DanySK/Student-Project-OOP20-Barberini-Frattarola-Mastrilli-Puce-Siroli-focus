package oop.focus.homepage.model;

/**
 * 
 * This is a class that model a person , wich has a name , a surname and a degreeOfKinship.
 *
 */
public class PersonImpl implements Person {

    private final String name;
    private final String relationships;

    /**
     * This is the class constructor.
     * @param name is the name of the person to be created.
     * @param degree it is the degree of kinship of the person to be created.
     */
    public PersonImpl(final String name, final String degree) {
        this.name = name;
        this.relationships = degree;
    }

    /**
     * This method is used for get the degree of kinship of the person.
     * @return a String that represents the person degree of kinship.
     */
    public final String getDegreeOfKinship() {
        return this.relationships;
    }

    /**
     * This method is used for get the name of the person.
     * @return a String that represent the person name.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * This is the hasCode related to the equals method.
     * @return an integer.
     */
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((relationships == null) ? 0 : relationships.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * This method is used to verify if a person is equals as another.
     * Two persons are the same if their name and degree of kinship are the same.
     * @param obj is the person whose equality needs to be checked.
     * @return a boolean which will be true if the two persons are equal and false if the two persons are different.
     */
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonImpl other = (PersonImpl) obj;
        if (relationships == null) {
            if (other.relationships != null) {
                return false;
            }
        } else if (!relationships.equals(other.relationships)) {
              return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
