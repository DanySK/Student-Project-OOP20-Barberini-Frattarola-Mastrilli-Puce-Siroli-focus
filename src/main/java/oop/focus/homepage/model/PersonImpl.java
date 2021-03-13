package oop.focus.homepage.model;

/**
 * 
 * This is a class that model a person , wich has a name , a surname and a degreeOfKinship.
 *
 */
public class PersonImpl implements Person {

    private final String name;
    private final String degreeOfKinship;

    /**
     * This is the class constructor.
     * @param name is the name of the person to be created.
     * @param degree it is the degree of kinship of the person to be created.
     * @param manager ;
     */
    public PersonImpl(final String name, final String degree, final ManagerDegreeOfKinship manager) {
        this.name = name;
        this.degreeOfKinship = degree;
        manager.add(this.getDegreeOfKinship());
    }

    /**
     * This method is use for get the degree of kinship of the person.
     * @return a String that rappresent the person degree of kinship.
     */
    public final String getDegreeOfKinship() {
        return this.degreeOfKinship;
    }

    /**
     * This method is use for get the name of the person.
     * @return a String that rappresent the person name.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * This is the hasCode related to the equals method.
     * @return an int.
     */
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((degreeOfKinship == null) ? 0 : degreeOfKinship.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * This method is use to verify if a person is equals as another.
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
        if (degreeOfKinship == null) {
            if (other.degreeOfKinship != null) {
                return false;
            }
        } else if (!degreeOfKinship.equals(other.degreeOfKinship)) {
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
