package oop.focus.homepage.controller;

import javafx.collections.ObservableList;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.view.HotKeyMenuView;

public interface HotKeyController {

    DataSource getDsi();

    HotKeyMenuView getView();

    ObservableList<HotKey> getSortedHotKey();

    void saveHotKey(HotKey hotKey);

    void deleteHotKey(HotKey selectedItem);
}
