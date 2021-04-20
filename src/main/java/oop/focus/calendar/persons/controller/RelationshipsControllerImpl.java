package oop.focus.calendar.persons.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableSet;
import oop.focus.calendar.persons.view.RelationshipsView;
import oop.focus.calendar.persons.view.RelationshipsViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonsManager;
import oop.focus.homepage.model.PersonsManagerImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;


public class RelationshipsControllerImpl implements RelationshipsController {

    private final DataSource dsi;
    private final RelationshipsManager relationships;
    private final PersonsManager persons;
    private final RelationshipsView view;

    public RelationshipsControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.relationships = new RelationshipsManagerImpl(this.dsi);
        this.persons = new PersonsManagerImpl(dsi);
        this.view = new RelationshipsViewImpl(this);
    }

    public final void addRelationship(final String relationship) {
        this.relationships.add(relationship);
    }

    public final void deleteRelationship(final String relationship) {
        this.relationships.remove(relationship);
    }

    public final ObservableSet<String> getDegree() {
        return this.relationships.getAll();
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final View getView() {
        return this.view;
    }

    @Override
    public final List<String> getPersons() {
        final List<Person> person = this.persons.getPersons().stream().collect(Collectors.toList());
        final List<String> degree = new ArrayList<>();
        person.forEach(p -> {
            if (!degree.contains(p.getRelationships())) {
                degree.add(p.getRelationships());
            }
        });
        return degree;
    }
}
