package oop.focus.calendarhomepage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.Repetition;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventManager;
import oop.focus.event.model.EventManagerImpl;
import oop.focus.calendarhomepage.model.HotKey;
import oop.focus.calendarhomepage.model.HotKeyManager;
import oop.focus.calendarhomepage.model.HotKeyManagerImpl;
import oop.focus.calendarhomepage.view.HomePageBaseView;
import oop.focus.calendarhomepage.view.HomePageBaseViewImpl;

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
        this.eventManager = new EventManagerImpl(this.dsi);
        this.hotKeyManager = new HotKeyManagerImpl(this.dsi);
        this.nameEvent = " ";
        this.view = new HomePageBaseViewImpl(this);
    }

    public final boolean getActivitySelected(final String hotKeyName) {
        List<Event> list = this.eventManager.getHotKeyEvents();
        list = list.stream().filter(e -> e.getName().equals(hotKeyName) && e.getStartDate().isEqual(LocalDate.now())).collect(Collectors.toList());
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
