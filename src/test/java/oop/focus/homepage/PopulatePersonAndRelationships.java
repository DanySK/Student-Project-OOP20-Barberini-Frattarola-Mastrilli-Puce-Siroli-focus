package oop.focus.homepage;

import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonImpl;
import oop.focus.homepage.model.PersonsManager;
import oop.focus.homepage.model.PersonsManagerImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;

import org.junit.Test;

public class PopulatePersonAndRelationships {

    private final DataSource dsi = new DataSourceImpl();
    private final RelationshipsManager relationships = new RelationshipsManagerImpl(dsi);
    private final PersonsManager persons = new PersonsManagerImpl(dsi);

    @Test
    public void populatePerson(){
        final Person first = new PersonImpl("Alice", "Sorella");
        final Person second = new PersonImpl("Loris", "Padre");
        final Person third = new PersonImpl("Cristina", "Mamma");
        final Person fourth = new PersonImpl("Mario", "Zio");

        this.persons.addPerson(first);
        this.persons.addPerson(second);
        this.persons.addPerson(third);
        this.persons.addPerson(fourth);
    }

    @Test
    public void populateDegree(){

        this.relationships.add("Padre");
        this.relationships.add("Mamma");
        this.relationships.add("Sorella");
        this.relationships.add("Zio");
        this.relationships.add("cugino");
        this.relationships.add("cugina");
    }
}
