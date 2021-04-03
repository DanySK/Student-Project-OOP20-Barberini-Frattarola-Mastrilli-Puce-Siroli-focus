package oop.focus.homepage.controller;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyType;
import oop.focus.homepage.view.HomePageBaseView;

public class HomePageController {

    private final HomePageBaseView view;
    private ObservableList<Event> event;
    private final ObservableList<HotKey> hotKeyList;

    public HomePageController() {
        this.view = new HomePageBaseView(this);
        hotKeyList = FXCollections.observableArrayList();
        this.hotKeyList.addAll(new HotKeyImpl("Shopping", HotKeyType.EVENT), new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY), new HotKeyImpl("Bere", HotKeyType.COUNTER), new HotKeyImpl("Addominali", HotKeyType.COUNTER), new HotKeyImpl("Allenamento", HotKeyType.ACTIVITY));
    }

    public final Parent getView() {
        return this.view.getRoot();
    }

    public final ObservableList<HotKey> getHotKey() {
        return this.hotKeyList;
    }

    public final HomePageBaseView getViewForChange() {
        return this.view;
    }

    public final void showAllert() {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("I campi non sono stati riempiti correttamente!");
        alert.setContentText("Riempire i campi o tornare indietro");

        final Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK || result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }


    public final void saveHotKey(final HotKey hotKey) {
        this.hotKeyList.add(hotKey);
    }

    public final void deleteHotKey(final HotKey selectedItem) {
        this.hotKeyList.remove(selectedItem);
    }

    public final void saveEvent(final EventImpl eventImpl) {
        this.event.add(eventImpl);
    }

}
