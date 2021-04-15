package oop.focus.diary.view;

import javafx.util.Pair;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.controller.DiarySectionsControllerImpl;
import oop.focus.diary.controller.CounterGeneralControllerImpl;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class SectionsDiary {
    private final List<Pair<Controller, String>> list;
    private final DataSource dataSource;
    private EventManager eventManager;
    public SectionsDiary(DataSource dataSource) {
        this.dataSource = new DataSourceImpl();
        this.eventManager = new EventManagerImpl(dataSource);
        this.list = new ArrayList<>();
        this.putControllers();
    }
    private void putControllers() {
        this.list.add(new Pair<>(new DiarySectionsControllerImpl(this.dataSource), "Diario"));
        this.list.add(new Pair<>(new CounterGeneralControllerImpl(eventManager, false), "Cronometro"));
        this.list.add(new Pair<>(new CounterGeneralControllerImpl(eventManager, true), "Timer"));
    }
    public final List<Pair<Controller, String>> getList() {
        return list;
    }
}
