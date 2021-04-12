package oop.focus.homepage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.homepage.view.HotKeyMenuView;
import oop.focus.homepage.view.HotKeyMenuViewImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HotKeyControllerImpl implements HotKeyController {

    private final HotKeyMenuView view;
    private final EventManager eventManager;
    private final HotKeyManager hotKeyManager;
    private final DataSource dsi;

    public HotKeyControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(this.dsi);
        this.hotKeyManager = new HotKeyManagerImpl(dsi, eventManager);
        this.view = new HotKeyMenuViewImpl(this);
    }

    public final View getView() {
        return this.view;
    }

    public final ObservableList<HotKey> getSortedHotKey() {
        final ObservableList<HotKey> list = FXCollections.observableArrayList();
        List<HotKey> arrayList = hotKeyManager.getAll().stream().collect(Collectors.toList());
        arrayList = arrayList.stream().sorted(Comparator.comparing(HotKey :: getName)).collect(Collectors.toList());
        arrayList.stream().forEach(h -> list.add(h));
        return list;
    }

    public final void saveHotKey(final HotKey hotKey) {
        this.hotKeyManager.add(hotKey);
        this.view.populate();
    }

    public final void deleteHotKey(final HotKeyImpl hotKeyImpl) {
        this.hotKeyManager.remove(hotKeyImpl);
    }

    public final DataSource getDsi() {
        return this.dsi;
    }
}
