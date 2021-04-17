package oop.focus.application.controller;
import javafx.util.Pair;
import oop.focus.common.Controller;
import java.util.ArrayList;
import java.util.List;

/**
 * SectionsImpl has method to set and return sections' Controller, and gets the
 * Controller whose View is shown as first when app is launched.
 */
public class SectionsImpl implements Sections {
    private final List<Pair<Controller, String>> list;
    private final SectionsControllerFactory factory;
    public SectionsImpl() {
        this.factory = new SectionsControllerFactoryImpl();
        this.list = new ArrayList<>();
        this.setControllers();
    }

    /**
     * The method fills the {@link List} putting all Controllers of Focus' sections.
     */
    private void setControllers() {
        this.list.add(new Pair<>(this.factory.getHomePageController(), "Home Page"));
        this.list.add(new Pair<>(this.factory.getFinanceController(), "Finanza"));
        this.list.add(new Pair<>(this.factory.getCalendarController(), "Calendario"));
        this.list.add(new Pair<>(this.factory.getDiaryController(), "Diario"));
    }

    /**
     * Returns the Controller whose View is the first section to be shown when app starts.
     * @return  the Controller whose View is showed as first when application is launched.
     */
    public final Controller getStarterController() {
        return this.factory.getHomePageController();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Pair<Controller, String>> getList() {
        return this.list;
    }
}
