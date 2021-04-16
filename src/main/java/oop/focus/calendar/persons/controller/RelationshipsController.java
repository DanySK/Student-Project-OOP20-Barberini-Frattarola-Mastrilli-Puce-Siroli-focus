package oop.focus.calendar.persons.controller;

import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;

public interface RelationshipsController {

    void addRelationship(String relationship);

    void deleteRelationship(String relationship);

    ObservableList<String> getDegree();

    DataSource getDsi();

    View getView();
}
