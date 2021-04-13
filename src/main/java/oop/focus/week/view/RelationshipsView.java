package oop.focus.week.view;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface RelationshipsView extends Initializable, View {

    void addRelationships();

    void deleteRelationships();

    void populateTableView();

    Node getRoot();
}
