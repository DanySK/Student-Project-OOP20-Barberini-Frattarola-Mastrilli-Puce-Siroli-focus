package oop.focus.calendar.persons.controller;

import javafx.collections.ObservableSet;
import oop.focus.calendar.persons.view.PersonsView;
import oop.focus.calendar.persons.view.PersonsViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonsManager;
import oop.focus.homepage.model.PersonsManagerImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;

public class PersonsControllerImpl implements PersonsController {

    private final PersonsView view;
    private final DataSource dsi;
    private final PersonsManager person;
    private final RelationshipsManager relationships;

    public PersonsControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.person = new PersonsManagerImpl(this.dsi);
        this.relationships = new RelationshipsManagerImpl(this.dsi);
        this.view = new PersonsViewImpl(this);
    }

    public final void addPerson(final Person person) {
        this.person.addPerson(person);
    }

    public final void deletePerson(final Person person) {
        this.person.removePerson(person);
    }

    public final ObservableSet<String> getDegree() {
        return this.relationships.getAll();
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final ObservableSet<Person> getPersons() {
        return this.person.getPersons();
    }

    public final View getView() {
        return this.view;
    }

}
