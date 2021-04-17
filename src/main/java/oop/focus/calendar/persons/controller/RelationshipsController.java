package oop.focus.calendar.persons.controller;

import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public interface RelationshipsController {

    void addRelationship(String relationship);

    void deleteRelationship(String relationship) throws IllegalStateException;

    ObservableList<String> getDegree();

    DataSource getDsi();

    View getView();
}
