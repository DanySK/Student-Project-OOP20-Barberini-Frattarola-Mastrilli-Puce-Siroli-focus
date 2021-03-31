package oop.focus.homepage;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.homepage.model.PersonsManager;
import oop.focus.homepage.model.PersonsManagerImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

public class PersonAndDegreeTest {
	
	private final DataSource dsi = new DataSourceImpl();
    private final PersonsManager persons = new PersonsManagerImpl(dsi);
    private final RelationshipsManager relationship = new RelationshipsManagerImpl(dsi);
    private final Person sara = new PersonImpl("Sara", "Cugina");
    private final Person elisa = new PersonImpl("Elisa", "me");
    private final Person luca = new PersonImpl("Luca", "Fratello");
    private final Person ilaria = new PersonImpl("Ilaria", "Sorella");
    private final Person loris = new PersonImpl("Loris", "Padre");

    /**
     * This test is use to save and remove person from the database.
     */
    @Test
    public void saveAndRemovePersonTest() {
    	this.persons.addPerson(new PersonImpl("Sara", "Cugina"));
    	this.persons.addPerson(new PersonImpl("Elisa", "me"));
    	this.persons.addPerson(new PersonImpl("Luca", "Fratello"));
    	this.persons.addPerson(new PersonImpl("Ilaria", "Sorella"));
    	this.persons.addPerson(new PersonImpl("Loris", "Padre"));
    	
    	assertEquals(this.persons.getPersons(), List.of(sara, elisa, luca, ilaria, loris));
    	
    	this.persons.removePerson(new PersonImpl("Andrea", "Zio"));
        this.persons.removePerson(sara);
    }

    /**
     * This test is use to save and remove relationships from the database.
     */
    @Test
    public void saveAndRemoveRelationshipTest() {
    	this.relationship.add(ilaria.getDegreeOfKinship());
    	this.relationship.add(elisa.getDegreeOfKinship());
    	this.relationship.add(loris.getDegreeOfKinship());
    	
    	assertEquals(this.relationship.getAll(), List.of(ilaria.getDegreeOfKinship(), elisa.getDegreeOfKinship(), loris.getDegreeOfKinship()));
    	this.relationship.remove("Zio");
    	this.relationship.remove(ilaria.getDegreeOfKinship());
    }
}
