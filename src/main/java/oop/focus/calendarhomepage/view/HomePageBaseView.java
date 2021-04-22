package oop.focus.calendarhomepage.view;
import javafx.fxml.Initializable;
import oop.focus.common.View;

public interface HomePageBaseView extends Initializable, View {

    /**
     * This method is used to set the day.
     */
    void setDay();

    /**
     * This method is use to full the vbox with the saved hot keys.
     */
    void fullVBoxHotKey();
}
