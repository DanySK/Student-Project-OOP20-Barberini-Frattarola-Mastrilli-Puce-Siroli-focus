package oop.focus.application.controller;
import javafx.util.Pair;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * SectionsImpl has method to set and return sections' Controller, and gets the
 * Controller whose View is shown as first when application is launched.
 */
public class SectionsImpl implements Sections {
    private final List<Pair<Controller, String>> list;
    private final SectionsControllerFactory factory;
    private final DataSource dataSource;
    private final FinanceManager financeManager;
    private Controller homePageController;
    public SectionsImpl() {
        this.factory = new SectionsControllerFactoryImpl();
        this.list = new ArrayList<>();
        this.setControllers();
        this.dataSource = new DataSourceImpl();
        this.financeManager = new FinanceManagerImpl(this.dataSource);
    }

    /**
     * The method fills the {@link List} putting all Controllers of Focus' sections.
     */
    private void setControllers() {
        this.homePageController = this.factory.getHomePageController(this.dataSource, this.financeManager);
        this.list.add(new Pair<>(this.homePageController, "Home Page"));
        this.list.add(new Pair<>(this.factory.getFinanceController(this.financeManager), "Finanza"));
        this.list.add(new Pair<>(this.factory.getCalendarController(this.dataSource, this.homePageController),
                "Calendario"));
        this.list.add(new Pair<>(this.factory.getDiaryController(this.dataSource), "Diario"));
    }

    /**
     * Returns the Controller whose View is the first section to be shown when application starts.
     * @return  the Controller whose View is showed as first when application is launched.
     */
    public Controller getStarterController() {
        return this.homePageController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Controller, String>> getList() {
        return this.list;
    }
}
