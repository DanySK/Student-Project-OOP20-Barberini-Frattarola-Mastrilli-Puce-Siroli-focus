package oop.focus.homepage.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface HotKeyMenuView extends Initializable, View {

    void addNewHotKey(ActionEvent event) throws IOException;

    void deletSelectedRowItem(ActionEvent event);

    void goBack(ActionEvent event) throws IOException;

    void populate();
}
