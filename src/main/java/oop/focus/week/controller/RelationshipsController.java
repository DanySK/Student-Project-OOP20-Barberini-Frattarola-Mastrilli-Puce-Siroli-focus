package oop.focus.week.controller;

import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;

public interface RelationshipsController {

    DataSource getDsi();

    void addRelationship(String relationship);

    void deleteRelationship(String relationship);

    ObservableList<String> getDegree();

    View getView();
}
