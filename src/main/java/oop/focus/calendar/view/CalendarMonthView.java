package oop.focus.calendar.view;

import javafx.scene.control.Label;


public interface CalendarMonthView {



    /**
     * Used for get the month info label (years and name).
     * @return label
     */
    Label getMonthInfo();


    /**
     * Used for set the month view in the controller.
     */
    void setMonthView();



}
