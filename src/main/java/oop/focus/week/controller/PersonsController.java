package oop.focus.week.controller;

import javafx.collections.ObservableList;
import oop.focus.homepage.model.Person;

public interface PersonsController {

    void addPerson(Person person);

    void deletePerson(Person person);

    void addRelationship(String relationship);

    void deleteRelationship(String relationship);

    ObservableList<String> getDegree();

    ObservableList<Person> getPersons();

}
