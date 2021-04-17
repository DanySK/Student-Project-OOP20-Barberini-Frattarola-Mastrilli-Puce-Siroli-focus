package oop.focus.homepage.view;

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
}
