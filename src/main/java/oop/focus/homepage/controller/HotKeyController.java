package oop.focus.homepage.controller;

import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;

public interface HotKeyController {

    View getView();

    ObservableList<HotKey> getSortedHotKey();

    void saveHotKey(HotKey hotKey);

    void deleteHotKey(HotKeyImpl hotKeyImpl);

    DataSource getDsi();
}
