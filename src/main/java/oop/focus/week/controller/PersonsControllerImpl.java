package oop.focus.week.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonsManager;
import oop.focus.homepage.model.PersonsManagerImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;
import oop.focus.week.view.PersonsView;
import oop.focus.week.view.PersonsViewImpl;

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
    public final ObservableList<Person> getPersons() {
        return FXCollections.observableArrayList(this.person.getPersons());
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    @Override
    public final ObservableList<String> getDegree() {
        return FXCollections.observableArrayList(this.relationships.getAll());
    }
}
