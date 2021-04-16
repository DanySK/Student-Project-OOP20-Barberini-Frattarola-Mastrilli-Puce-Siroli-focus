package oop.focus.diary.controller;

import javafx.util.Pair;
import oop.focus.application.Sections;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.month.controller.CalendarMonthControllerImpl;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class DiarySections implements Sections {
    private final List<Pair<Controller, String>> list;
    private final DataSource dataSource;
    private final EventManager eventManager;
    private final Controller baseDiary;

    public DiarySections(final DataSource dataSource)  {
        this.list = new ArrayList<>();
        this.dataSource = dataSource;
        this.eventManager = new EventManagerImpl(dataSource);
        this.baseDiary = new BaseDiaryController(this.dataSource);
        this.putControllers();
    }
    private void putControllers() {
        this.list.add(new Pair<>(this.baseDiary, "Diario"));
        this.list.add(new Pair<>(new CalendarMonthControllerImpl(CalendarType.DIARY, this.dataSource), "Statistiche umore"));
        this.list.add(new Pair<>(new CounterGeneralControllerImpl(this.eventManager, false),
                "Cronometro"));
        this.list.add(new Pair<>(new CounterGeneralControllerImpl(this.eventManager, true),
                "Timer"));
    }

    @Override
    public Controller getFirstWindow() {
        return this.baseDiary;
    }

    public final List<Pair<Controller, String>> getList() {
        return this.list;
    }
}
