package oop.focus.homepage.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface HomePageBaseView extends Initializable, View {

    /**
     * This method is used to set the action when the modify button is clicked.
     * @param event is the action event.
     */
    void modifyClicked(ActionEvent event);

    /**
     * This method is used to set the day.
     */
    void setDay();

    /**
     * This method is use to full the vbox with the saved hot keys.
     */
    void fullVBoxHotKey();
}
