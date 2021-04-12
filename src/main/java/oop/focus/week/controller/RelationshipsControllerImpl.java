package oop.focus.week.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.RelationshipsManager;
import oop.focus.homepage.model.RelationshipsManagerImpl;

public class RelationshipsControllerImpl {

    private final DataSource dsi;
    private final RelationshipsManager relationships;

    public RelationshipsControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.relationships = new RelationshipsManagerImpl(this.dsi);
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final void addRelationship(final String relationship) {
        this.relationships.add(relationship);
    }

    public final void deleteRelationship(final String relationship) {
        this.relationships.remove(relationship);
    }

    public final ObservableList<String> getDegree() {
        return FXCollections.observableArrayList(this.relationships.getAll());
    }
}
