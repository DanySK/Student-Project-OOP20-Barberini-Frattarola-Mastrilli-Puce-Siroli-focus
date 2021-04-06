package oop.focus.homepage.controller;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.*;
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

    public final void addRelationship(String relationship) {
        this.relationships.remove(relationship);
    }

    public final void deleteRelationship(String relationship) {
        this.relationships.remove(relationship);
    }
}
