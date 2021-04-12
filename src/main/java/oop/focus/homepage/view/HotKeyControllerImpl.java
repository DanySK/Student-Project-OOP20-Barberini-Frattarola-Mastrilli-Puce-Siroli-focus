package oop.focus.homepage.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;

public class HotKeyControllerImpl {

    private final HotKeyMenuView view;
    private final EventManager eventManager;
    private final HotKeyManager hotKeyManager;
    private final DataSource dsi;


    private final ObservableSet<HotKey> hotKeys;

    public HotKeyControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(this.dsi);
        this.hotKeyManager = new HotKeyManagerImpl(dsi, eventManager);
        this.view = new HotKeyMenuViewImpl(this);
        this.hotKeys = this.hotKeyManager.getAll();
        this.addListener();
    }

    public final void addListener() {
        this.hotKeys.addListener((SetChangeListener<HotKey>) change -> this.view.populate());
    }

    public final View getView() {
        return this.view;
    }

    public final ObservableList<HotKey> getHotKey() {
        final ObservableList<HotKey> list = FXCollections.observableArrayList();
        hotKeyManager.getAll().forEach(hotKey -> list.add(hotKey));
        return list;
    }

    public final void saveHotKey(final HotKey hotKey) {
        this.hotKeyManager.add(hotKey);
        this.view.populate();
    }

    public final void deleteHotKey(final HotKeyImpl hotKeyImpl) {
        this.hotKeyManager.remove(hotKeyImpl);
    }

    public final void refreshMenu() {
        this.view.populate();
    }
    
    public final DataSource getDsi() {
    	return this.dsi;
    }
}
