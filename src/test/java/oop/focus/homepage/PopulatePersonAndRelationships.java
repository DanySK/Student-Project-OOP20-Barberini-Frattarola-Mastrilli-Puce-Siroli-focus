package oop.focus.homepage;

import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.*;
import org.junit.Test;

public class PopulatePersonAndRelationships {
    private DataSourceImpl dsi = new DataSourceImpl();
    private RelationshipsManager relationships = new RelationshipsManagerImpl(dsi);
    private PersonsManager persons = new PersonsManagerImpl(dsi);

    @Test
    public void populatePerson(){
        Person first = new PersonImpl("Alice", "Sorella");
        Person second = new PersonImpl("Loris", "Padre");
        Person third = new PersonImpl("Cristina", "Mamma");
        Person fourth = new PersonImpl("Mario", "Zio");
        this.persons.addPerson(first);
        this.persons.addPerson(second);
        this.persons.addPerson(third);
        this.persons.addPerson(fourth);
    }

    @Test
    public void populateDegree(){
        String padre = new String("Padre");
        String madre = new String("Mamma");
        String sorella = new String("Sorella");
        String zio = new String("Zio");
        String cugino = new String("cugino");
        String cugina = new String("cugina");
    }
}
