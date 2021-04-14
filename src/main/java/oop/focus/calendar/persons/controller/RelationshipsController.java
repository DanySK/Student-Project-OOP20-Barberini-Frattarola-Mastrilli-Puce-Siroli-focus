package oop.focus.calendar.persons.controller;

import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;

public interface RelationshipsController {

    DataSource getDsi();

    void addRelationship(String relationship);

    void deleteRelationship(String relationship) throws DaoAccessException;

    ObservableList<String> getDegree();

    View getView();

    void setWidth(double width);

    void setHeight(double height);

    double getWidth();

    double getHeight();
}
