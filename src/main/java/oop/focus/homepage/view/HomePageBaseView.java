package oop.focus.homepage.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface HomePageBaseView extends Initializable, View {

    /**
     * This method is used to set the action when the modify button is clicked.
     * @param event is the action event.
     * @throws IOException is clicked when it is impossible to change the view.
     */
    void modifyClicked(ActionEvent event) throws IOException;

    /**
     * This method is used to set the day.
     */
    void setDay();

    /**
     * This method is use to full the vbox with the saved hot keys.
     */
    void fullVBoxHotKey();
}
