package oop.focus.event.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oop.focus.common.View;
import oop.focus.db.DataSource;
import oop.focus.event.view.EventMenuView;
import oop.focus.event.view.EventMenuViewImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

public class EventMenuControllerImpl implements EventMenuController {

    private final DataSource dsi;
    private final EventManager eventManager;
    private final EventMenuView view;

    public EventMenuControllerImpl(final DataSource dsi) {
        this.dsi = dsi;
        this.eventManager = new EventManagerImpl(dsi);
        this.view = new EventMenuViewImpl(this);
    }

    public final ObservableList<Event> getEvents() {
        final ObservableList<Event> list = FXCollections.observableArrayList();
        List<Event> arrayList = this.eventManager.getAll().stream().collect(Collectors.toList());
        arrayList = arrayList.stream().sorted(Comparator.comparing(Event :: getName)).collect(Collectors.toList());
        arrayList.stream().forEach(p -> list.add(p));
        return list;
    }

    @Override
    public final View getView() {
        return this.view;
    }

    @Override
    public final void remove(final Event selectedItem) {
        this.eventManager.removeEvent(selectedItem);
    }

    @Override
    public final DataSource getDsi() {
        return this.dsi;
    }
}
