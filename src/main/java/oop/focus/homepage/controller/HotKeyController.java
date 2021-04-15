package oop.focus.homepage.controller;

import javafx.collections.ObservableList;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.view.HotKeyMenuView;

public interface HotKeyController {

    void deleteHotKey(HotKeyImpl hotKeyImpl);

    DataSource getDsi();

    HotKeyMenuView getView();

    ObservableList<HotKey> getSortedHotKey();

    void saveHotKey(HotKey hotKey);
}
