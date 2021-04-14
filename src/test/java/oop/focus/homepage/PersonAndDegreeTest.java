package oop.focus.homepage;

import static org.junit.Assert.assertTrue;

import java.util.Set;

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

    private final Person p1 = new PersonImpl("n1", "r1");
    private final Person p2 = new PersonImpl("n2", "r2");
    private final Person p3 = new PersonImpl("n3", "r3");
    private final Person p4 = new PersonImpl("n4", "r4");
    private final Person p5 = new PersonImpl("n5", "r5");

	/**
	 * This test is use to save and remove relationships from the database.
	 */
	@Test
	public void saveAndRemoveRelationshipTest() {
		this.relationship.add(p1.getRelationships());
		this.relationship.add(p2.getRelationships());
		this.relationship.add(p3.getRelationships());

		assertTrue(this.relationship.getAll().containsAll(Set.of(p1.getRelationships(), p2.getRelationships(), p3.getRelationships())));

		this.persons.addPerson(p1);
		this.persons.addPerson(p2);
		this.persons.addPerson(p3);

		this.persons.removePerson(p1);
		this.persons.removePerson(p2);
		this.persons.removePerson(p3);

		this.relationship.remove(p1.getRelationships());
		this.relationship.remove(p2.getRelationships());
		this.relationship.remove(p3.getRelationships());
	}

}
