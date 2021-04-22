package oop.focus.homepage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.Repetition;
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

import java.util.ArrayList;
import java.util.Arrays;
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
        this.hotKeyManager = new HotKeyManagerImpl(dsi);
        this.nameEvent = " ";
        this.view = new HomePageBaseViewImpl(this);
    }

    public final void deleteHotKey(final HotKeyImpl hotKeyImpl) {
        this.hotKeyManager.remove(hotKeyImpl);
    }

    public final boolean getActivitySelected(final String hotKeyName) {
        List<Event> list = this.eventManager.getHotKeyEvents();
        list = list.stream().filter(e -> e.getName().equals(hotKeyName) && e.getStartDate().isEqual(LocalDate.now())).collect(Collectors.toList());
        list.forEach(h -> h.getName());
        return list.isEmpty();

    }

    public final String getClickTime(final HotKey hotKey) {
        final List<Event> list = this.eventManager.getHotKeyEvents();
        return String.valueOf(list.stream().filter(e -> e.getName().equals(hotKey.getName())
            && e.getStartDate().isEqual(LocalDate.now())).count());
    }

    public final DataSourceImpl getDsi() {
        return (DataSourceImpl) this.dsi;
    }

    public final ObservableList<Event> getEvents() {
        final ObservableList<Event> list = FXCollections.observableArrayList();
        list.addAll(this.eventManager.getHotKeyEvents());
        return list;
    }

    public final ObservableList<HotKey> getHotKey() {
        final ObservableList<HotKey> list = FXCollections.observableArrayList();
        list.addAll(this.hotKeyManager.getAll());
        return list;
    }

    public final String getText() {
        return this.nameEvent;
    }

    public final HomePageBaseView getView() {
        return this.view;
    }

    public final void refreshDailyEvents() {
        this.eventManager.generateRepeatedEvents(LocalDate.now());
    }

    public final void saveEvent(final Event eventImpl) {
        this.eventManager.addEvent(eventImpl);
        this.eventManager.generateRepeatedEvents(LocalDate.now());
    }

    public final void saveHotKey(final HotKey hotKey) {
        this.hotKeyManager.add(hotKey);
    }

    public final void setText(final String text) {
        this.nameEvent = text;
    }

    @Override
    public final ObservableList<Repetition> getRep() {
        final ObservableList<Repetition> list = FXCollections.observableArrayList();
        final List<Repetition> arrayList = new ArrayList<>(Arrays.asList(Repetition.values()));
        list.addAll(arrayList);
        return list;
    }
}
