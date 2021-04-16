package oop.focus.homepage.view;

import javafx.scene.control.Alert;

public interface AlertFactory {

    Alert createWarningAlert();

    Alert createConfirmationAlert();
}
