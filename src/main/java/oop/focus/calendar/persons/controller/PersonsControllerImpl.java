package oop.focus.calendar.persons.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.calendar.persons.view.PersonsView;
import oop.focus.calendar.persons.view.PersonsViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Person;
import oop.focus.homepage.model.PersonsManager;
import oop.focus.homepage.model.PersonsManagerImpl;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public final ObservableList<String> getDegree() {
        return FXCollections.observableArrayList(this.relationships.getAll());
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final ObservableList<Person> getPersons() {
        final ObservableList<Person> list = FXCollections.observableArrayList();
        List<Person> arrayList = this.person.getPersons().stream().collect(Collectors.toList());
        arrayList = arrayList.stream().sorted(Comparator.comparing(Person :: getName)).collect(Collectors.toList());
        arrayList.stream().forEach(p -> list.add(p));
        return list;
    }

    public final View getView() {
        return this.view;
    }
}
