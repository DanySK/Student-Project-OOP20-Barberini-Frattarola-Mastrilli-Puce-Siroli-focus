package oop.focus.calendar.settings.controller;



import oop.focus.calendar.model.Format;
import oop.focus.common.Controller;

/**
 * Interface that models a Settings Controller.
 * Is used for change the spacing and the format of the {@link oop.focus.calendar.day.view.HoursView} (day view)
 */
public interface CalendarSettingsController extends Controller {

    /**
     * Used for set the format of the hours.
     * @param format : the {@link Format} of the hours of the hours box
     */
    void setFormat(Format format);

    /**
     * Used for get the format of the hours.
     * @return Format
     */
    Format getFormat();

    /**
     * Used for check if the spacing isn't under a certain threshold and if is a number.
     * @param spacing : is the string of the TextField
     * @return true if pass the check or false if not
     */
    boolean checkSpacing(String spacing);

    /**
     * Used for set the space between the hours.
     * @param spacing : space between two hours in the Hours Box
     */
    void setSpacing(double spacing);

    /**
     * Used for get the spacing between the hours.
     * @return double : spacing
     */
    double getSpacing();


    /**
     * Used for update all the view that are connected with the settings.
     */
    void updateView();

}
