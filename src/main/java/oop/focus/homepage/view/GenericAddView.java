package oop.focus.homepage.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import oop.focus.common.View;

public interface GenericAddView extends Initializable, View {

    void save(ActionEvent event) throws IOException;

    void goBack(ActionEvent event) throws IOException; 

    void delete(ActionEvent event);

    Node getRoot();

}
