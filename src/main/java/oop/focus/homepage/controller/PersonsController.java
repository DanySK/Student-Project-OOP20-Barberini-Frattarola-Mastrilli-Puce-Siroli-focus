package oop.focus.homepage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonsManager;
import oop.focus.homepage.model.PersonsManagerImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;
import oop.focus.homepage.view.PersonsView;

public class PersonsController {

    private final PersonsView view;
    private final DataSourceImpl dsi;
    private final PersonsManager person;
    private final RelationshipsManager relationships;

    public PersonsController(final DataSourceImpl dsi) {
        this.dsi = dsi;
        this.person = new PersonsManagerImpl(this.dsi);
        this.relationships = new RelationshipsManagerImpl(this.dsi);
        this.view = new PersonsView(this);
    }

    public final void addPerson(final Person person) {
        this.person.addPerson(person);
    }

    public final void deletePerson(final Person person) {
        this.person.removePerson(person);
    }

    public final void addRelationship(final String relationship) {
        this.relationships.remove(relationship);
    }

    public final void deleteRelationship(final String relationship) {
        this.relationships.remove(relationship);
    }

    public final ObservableList<String> getDegree() {
        return FXCollections.observableArrayList(this.relationships.getAll());
    }

    public final ObservableList<Person> getPersons() {
        return FXCollections.observableArrayList(this.person.getPersons());
    }
}
