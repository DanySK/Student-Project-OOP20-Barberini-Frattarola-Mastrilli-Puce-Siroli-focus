package oop.focus.homepage;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import oop.focus.calendar.persons.model.Person;
import oop.focus.calendar.persons.model.PersonImpl;
import oop.focus.calendar.persons.model.PersonsManager;
import oop.focus.calendar.persons.model.PersonsManagerImpl;
import oop.focus.calendar.persons.model.RelationshipsManager;
import oop.focus.calendar.persons.model.RelationshipsManagerImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

public class PersonAndDegreeTest {
	
	private final DataSource dsi = new DataSourceImpl();
    private final PersonsManager persons = new PersonsManagerImpl(this.dsi);
    private final RelationshipsManager relationship = new RelationshipsManagerImpl(this.dsi);

    private final Person p1 = new PersonImpl("n1", "r1");
    private final Person p2 = new PersonImpl("n2", "r2");
    private final Person p3 = new PersonImpl("n3", "r3");

	/**
	 * This test is use to save and remove relationships from the database.
	 */
	@Test
	public void saveAndRemoveRelationshipTest() {
		this.relationship.add(this.p1.getRelationships());
		this.relationship.add(this.p2.getRelationships());
		this.relationship.add(this.p3.getRelationships());

		assertTrue(this.relationship.getAll().containsAll(Set.of(this.p1.getRelationships(), this.p2.getRelationships(), this.p3.getRelationships())));

		this.persons.addPerson(this.p1);
		this.persons.addPerson(this.p2);
		this.persons.addPerson(this.p3);

		this.persons.removePerson(this.p1);
		this.persons.removePerson(this.p2);
		this.persons.removePerson(this.p3);

		this.relationship.remove(this.p1.getRelationships());
		this.relationship.remove(this.p2.getRelationships());
		this.relationship.remove(this.p3.getRelationships());
	}

}
