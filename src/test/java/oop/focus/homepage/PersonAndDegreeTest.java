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
    	this.persons.addPerson(sara);
    	this.persons.addPerson(elisa);
    	this.persons.addPerson(luca);
    	this.persons.addPerson(ilaria);
    	this.persons.addPerson(loris);
    	
    	assertEquals(this.persons.getPersons(), List.of(sara, elisa, luca, ilaria, loris));

    	this.persons.removePerson(sara);
    	this.persons.removePerson(elisa);
    	this.persons.removePerson(luca);
    	this.persons.removePerson(ilaria);
    	this.persons.removePerson(loris);
    }

   /**
     * This test is use to save and remove relationships from the database.
     */
    @Test
    public void saveAndRemoveRelationshipTest() {
    	this.relationship.add(ilaria.getRelationships());
    	this.relationship.add(elisa.getRelationships());
    	this.relationship.add(loris.getRelationships());
    	
    	assertEquals(this.relationship.getAll(), List.of(ilaria.getRelationships(), elisa.getRelationships(), loris.getRelationships()));
    	this.relationship.remove(ilaria.getRelationships());
    	this.relationship.remove(elisa.getRelationships());
    	this.relationship.remove(loris.getRelationships());
    }

}
