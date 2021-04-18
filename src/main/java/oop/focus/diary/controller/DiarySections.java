package oop.focus.diary.controller;

import javafx.util.Pair;
import oop.focus.application.controller.Sections;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link Sections}. It returns a list of all Controllers relative to all under-sections of
 * Diary's section.
 */
public class DiarySections implements Sections {
    private final List<Pair<Controller, String>> list;
    private final DiarySectionsControllerFactory factory;

    /**
     * Instantiates a new diary sections.
     *
     * @param dataSource    dataSource the {@link DataSource} from which to retrieve data
     */
    public DiarySections(final DataSource dataSource)  {
        this.list = new ArrayList<>();
        this.factory = new DiarySectionsControllerFactoryImpl(dataSource);
        this.putControllers();
    }

    /**
     * The method fills a {@link List} with all Controllers relative to diary's section.
     */
    private void putControllers() {
        this.list.add(new Pair<>(this.factory.getDiaryController(), "Diario"));
        this.list.add(new Pair<>(this.factory.getMoodCalendarController(), "Statistiche umore"));
        this.list.add(new Pair<>(this.factory.getStopwatchController(), "Cronometro"));
        this.list.add(new Pair<>(this.factory.getTimerController(), "Timer"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Controller getStarterController() {
        return this.factory.getDiaryController();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Pair<Controller, String>> getList() {
        return this.list;
    }
}
