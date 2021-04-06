package oop.focus.homepage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.homepage.view.HomePageBaseView;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageController {

    private final HomePageBaseView view;
    private final EventManager eventManager;
    private final HotKeyManager hotKeyManager;
    private final DataSourceImpl dsi;

    public HomePageController(final DataSourceImpl dsi) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(dsi);
        this.hotKeyManager = new HotKeyManagerImpl(dsi, eventManager);
        this.view = new HomePageBaseView(this);
    }

    public final Parent getView() {
        return this.view.getRoot();
    }

    public final ObservableList<HotKey> getHotKey() {
        final ObservableList<HotKey> list = FXCollections.observableArrayList();
        hotKeyManager.getAll().forEach(hotKey -> list.add(hotKey));
        return list;
    }

    public final HomePageBaseView getViewForChange() {
        return this.view;
    }

    public final void saveHotKey(final HotKey hotKey) {
        this.hotKeyManager.add(hotKey);
    }

    public final void deleteHotKey(final HotKeyImpl hotKeyImpl) {
        this.hotKeyManager.remove(hotKeyImpl);
    }

    public final void saveEvent(final EventImpl eventImpl) {
        this.eventManager.addEvent(eventImpl);
    }

    public final String getClickTime(final HotKey hotKey) {
        final List<Event> list = this.eventManager.takeOnly(this.eventManager.findByDate(LocalDate.now()).stream().collect(Collectors.toList()));
        return String.valueOf(list.stream().filter(e -> e.getName().equals(hotKey.getName())).count());
    }

    public final boolean getActivitySelected(final HotKeyImpl hotKey) {
        List<Event> list = this.eventManager.takeOnly(this.eventManager.findByDate(LocalDate.now()).stream().collect(Collectors.toList()));
        list = list.stream().filter(e -> e.getName().equals(hotKey.getName())).collect(Collectors.toList());
        return list.isEmpty();

    }

    public final void refreshDailyEvents() {
        this.eventManager.generateRepeatedEvents(LocalDate.now());
    }
}
