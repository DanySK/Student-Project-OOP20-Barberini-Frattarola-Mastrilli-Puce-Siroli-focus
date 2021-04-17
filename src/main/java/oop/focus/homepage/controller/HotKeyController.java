package oop.focus.homepage.controller;

import javafx.collections.ObservableList;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.view.HotKeyMenuView;

public interface HotKeyController {

    /**
     * This method is used to get the data source.
     * @return the data source.
     */
    DataSource getDsi();

    /**
     * This method is used to get the hot key menu view.
     * @return HotKeyMenuView that represent the view of the hot key menu.
     */
    HotKeyMenuView getView();

    /**
     * This method is used to get all the saved hot keys sorted by name.
     * @return an observable list of hot keys.
     */
    ObservableList<HotKey> getSortedHotKey();

    /**
     * This method is used to save a specific hot key into the database.
     * @param hotKey is the hot key to save.
     */
    void saveHotKey(HotKey hotKey);

    /**
     * This method is used to delete a specific hot key into the database.
     * @param selectedItem hotKey is the hot key to delete.
     */
    void deleteHotKey(HotKey selectedItem);
}
