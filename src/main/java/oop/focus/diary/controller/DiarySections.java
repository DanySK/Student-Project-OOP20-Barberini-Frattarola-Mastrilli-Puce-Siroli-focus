package oop.focus.diary.controller;

import javafx.util.Pair;
import oop.focus.application.controller.Sections;
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
    private final DiarySectionsControllerFactory factory;

    public DiarySections(final DataSource dataSource)  {
        this.list = new ArrayList<>();
        this.factory = new DiarySectionsControllerFactoryImpl(dataSource);
        this.putControllers();
    }
    private void putControllers() {
        this.list.add(new Pair<>(this.factory.getDiaryController(), "Diario"));
        this.list.add(new Pair<>(this.factory.getMoodCalendarController(), "Statistiche umore"));
        this.list.add(new Pair<>(this.factory.getStopwatchController(), "Cronometro"));
        this.list.add(new Pair<>(this.factory.getTimerController(), "Timer"));
    }

    @Override
    public final Controller getStarterController() {
        return this.factory.getDiaryController();
    }

    public final List<Pair<Controller, String>> getList() {
        return this.list;
    }
}
