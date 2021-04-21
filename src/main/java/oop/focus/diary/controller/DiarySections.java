package oop.focus.diary.controller;

import javafx.util.Pair;
import oop.focus.application.controller.Sections;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.event.model.EventManager;
import oop.focus.event.model.EventManagerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link Sections}. It returns a list of all Controllers relative to all under-sections of
 * Diary's section.
 */
public class DiarySections implements Sections {
    private final List<Pair<Controller, String>> list;
    private final DiarySectionsControllerFactory factory;
    private final DataSource dataSource;
    private final EventManager eventManager;
    private final Controller diaryController;
    /**
     * Instantiates a new diary sections.
     *
     * @param dataSource    the {@link DataSource} from which to retrieve data
     */
    public DiarySections(final DataSource dataSource)  {
        this.list = new ArrayList<>();
        this.factory = new DiarySectionsControllerFactoryImpl();
        this.dataSource = dataSource;
        this.eventManager = new EventManagerImpl(dataSource);
        this.diaryController = this.factory.getDiaryController(this.dataSource);
        this.putControllers();
    }

    /**
     * The method fills a {@link List} with all Controllers relative to diary's section.
     */
    private void putControllers() {
        this.list.add(new Pair<>(this.diaryController, "Diario"));
        this.list.add(new Pair<>(this.factory.getMoodCalendarController(this.dataSource), "Statistiche umore"));
        this.list.add(new Pair<>(this.factory.getStopwatchController(this.eventManager), "Cronometro"));
        this.list.add(new Pair<>(this.factory.getTimerController(this.eventManager), "Timer"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getStarterController() {
        return this.diaryController;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Controller, String>> getList() {
        return this.list;
    }
}
