package oop.focus.calendar.day.view;

import javafx.scene.layout.VBox;

/**
 * Interface that models an DayVBox.
 */
public interface VBoxManager {

    /**
     * Used for get the Y position of the hours in the VBox.
     * @param i  Index : of the hours
     * @return double : position of the object in the VBox
     */
    double getY(int i);

    /**
     * Used for get the VBox.
     * @return VBox : the vbox.
     */
    VBox getVBox();

    /**
     * Used for build the Box.
     * build the vbox.
     */
    void buildVBox();

}
