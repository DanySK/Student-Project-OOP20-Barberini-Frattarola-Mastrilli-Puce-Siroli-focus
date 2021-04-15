package oop.focus.homepage.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface HomePageBaseView extends Initializable, View {

    void modifyClicked(ActionEvent event) throws IOException;

    void setDay();

    void fullVBoxHotKey();
}
