package oop.focus.calendarhomepage.view;

import javafx.scene.control.Alert;

public interface AlertFactory {

    /**
     * This method is use to create an alert when the fields are not filled.
     * @return an Alert of warning type.
     */
    Alert createIncompleteFieldAlert();

    /**
     * This method is use to create an alert when it is impossible to save items.
     * @return an Alert of warning type.
     */
    Alert createImpossibleSaveElement();

    /**
     * This method is use to create an alert when when the time or date is incorrect.
     * @return an Alert of warning type.
     */
    Alert createHourOrDateError();

    /**
     * This method is use to create an alert when when the item we want to save is already present.
     * @return an Alert of warning type.
     */
    Alert createAlreadyPresentItem();

    /**
     * This method is use to create an alert when when it's not possible to save an event.
     * @return an Alert of warning type.
     */
    Alert createEventWarning();
}
