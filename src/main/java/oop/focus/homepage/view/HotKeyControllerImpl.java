package oop.focus.homepage.view;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import oop.focus.common.View;
import oop.focus.homepage.controller.HomePageController;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;

public class HotKeyControllerImpl {

    private final HotKeyMenuView view;
    private final HomePageController homePageController;
    private final HotKeyManager hotKeyManager;
    private final ObservableSet<HotKey> hotKeys;

    public HotKeyControllerImpl(final HomePageController homePageController) {
        this.homePageController = homePageController;
        this.view = new HotKeyMenuViewImpl(this);
        this.hotKeyManager = this.homePageController.getHotKeyManager();
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
        return this.homePageController.getHotKey();
    }

    public final void saveHotKey(final HotKey hotKey) {
        this.homePageController.saveHotKey(hotKey);
    }

    public final void deleteHotKey(final HotKeyImpl hotKeyImpl) {
        this.homePageController.deleteHotKey(hotKeyImpl);
    }

    public final HomePageController getController() {
        return this.homePageController;
    }

}
