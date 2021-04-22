package oop.focus.calendarhomepage.controller;

import javafx.collections.ObservableSet;
import oop.focus.db.DataSource;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventManager;
import oop.focus.event.model.EventManagerImpl;
import oop.focus.calendarhomepage.model.HotKey;
import oop.focus.calendarhomepage.model.HotKeyManager;
import oop.focus.calendarhomepage.model.HotKeyManagerImpl;
import oop.focus.calendarhomepage.view.HotKeyMenuView;
import oop.focus.calendarhomepage.view.HotKeyMenuViewImpl;
import java.util.List;

public class HotKeyControllerImpl implements HotKeyController {

    private final HotKeyMenuView view;
    private final EventManager eventManager;
    private final HotKeyManager hotKeyManager;
    private final DataSource dsi;

    public HotKeyControllerImpl(final HomePageController controller) {
        this.dsi = controller.getDsi();
        this.eventManager = new EventManagerImpl(this.dsi);
        this.hotKeyManager = new HotKeyManagerImpl(this.dsi);
        this.view = new HotKeyMenuViewImpl(this, controller);
    }

    public final void deleteHotKey(final HotKey hotKeyImpl) {
        this.hotKeyManager.remove(hotKeyImpl);
        final String name = hotKeyImpl.getName();
        final  List<Event> list = this.eventManager.getHotKeyEvents();
        for (final Event event : list) {
            if (event.getName().equals(name)) {
                this.eventManager.removeEvent(event);
            }
        }
    }

    public final DataSource getDsi() {
        return this.dsi;
    }

    public final ObservableSet<HotKey> getSortedHotKey() {
        return this.hotKeyManager.getAll();
    }

    public final HotKeyMenuView getView() {
        return this.view;
    }

    public final void saveHotKey(final HotKey hotKey) {
        if (this.hotKeyManager.getAll().contains(hotKey)) {
            throw new IllegalStateException();
        } else {
            this.hotKeyManager.add(hotKey);
        }
    }

}
