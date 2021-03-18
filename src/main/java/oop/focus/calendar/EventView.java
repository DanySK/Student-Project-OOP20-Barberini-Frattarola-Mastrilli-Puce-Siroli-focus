package oop.focus.calendar;

import javafx.scene.layout.VBox;

public interface EventView {

    /**
     * @param i qualxcosa
     * @return qualcosa
     */
    double getY(int i);

    /**
     *@param vbox set the events VBox.
     */
    void setVBox(VBox vbox);

    /**
     * @return get the events box.
     */
    VBox getVBox();

}
