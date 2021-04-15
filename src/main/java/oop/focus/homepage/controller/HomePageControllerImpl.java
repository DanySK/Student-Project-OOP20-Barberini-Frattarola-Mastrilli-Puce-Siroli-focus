package oop.focus.homepage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
import oop.focus.homepage.model.HotKey;
import oop.focus.homepage.model.HotKeyImpl;
import oop.focus.homepage.model.HotKeyManager;
import oop.focus.homepage.model.HotKeyManagerImpl;
import oop.focus.homepage.view.HomePageBaseView;
import oop.focus.homepage.view.HomePageBaseViewImpl;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

public class HomePageControllerImpl implements HomePageController {

    private final HomePageBaseView view;
    private final EventManager eventManager;
    private final HotKeyManager hotKeyManager;
    private String nameEvent;
    private final DataSource dsi;

    public HomePageControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(dsi);
        this.hotKeyManager = new HotKeyManagerImpl(dsi, eventManager);
        this.nameEvent = " ";
        this.view = new HomePageBaseViewImpl(this);
    }

    public final boolean canBeAdded(final Event event) {
        return event.getStartHour().isBefore(event.getEndHour());
    }

    public final void deleteHotKey(final HotKeyImpl hotKeyImpl) {
        this.hotKeyManager.remove(hotKeyImpl);
    }

    public final boolean getActivitySelected(final String hotKeyName) {
        List<Event> list = this.eventManager.getHotKeyEvents();
        list = list.stream().filter(e -> {
            return e.getName().equals(hotKeyName) && e.getStartDate().isEqual(LocalDate.now());
        }).collect(Collectors.toList());
        return list.isEmpty();

    }

    public final String getClickTime(final HotKey hotKey) {
        final List<Event> list = this.eventManager.getHotKeyEvents();
        return String.valueOf(list.stream().filter(e -> {
            return e.getName().equals(hotKey.getName()) && e.getStartDate().isEqual(LocalDate.now());
        }).count());
    }

    public final DataSourceImpl getDsi() {
        return (DataSourceImpl) this.dsi;
    }

    public final ObservableList<Event> getEvents() {
        final ObservableList<Event> list = FXCollections.observableArrayList();
        eventManager.getHotKeyEvents().forEach(event -> list.add(event));
        return list;
    }

    public final ObservableList<HotKey> getHotKey() {
        final ObservableList<HotKey> list = FXCollections.observableArrayList();
        hotKeyManager.getAll().forEach(hotKey -> list.add(hotKey));
        return list;
    }

    public final HotKeyManager getHotKeyManager() {
        return this.hotKeyManager;
    }

    public final String getText() {
        return this.nameEvent;
    }

    public final HomePageBaseView getView() {
        return this.view;
    }

    public final void refreshDailyEvents() {
        //this.eventManager.generateRepeatedEvents(LocalDate.now());
    }

    public final void saveEvent(final Event eventImpl) {
        try {
            this.eventManager.addEvent(eventImpl);
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        }
    }

    public final void saveHotKey(final HotKey hotKey) {
        this.hotKeyManager.add(hotKey);
    }

    public final void setText(final String text) {
        this.nameEvent = text;
    }
}
