package oop.focus.application;
import javafx.util.Pair;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;

import oop.focus.diary.controller.GeneralDiaryController;
import oop.focus.finance.controller.BaseControllerImpl;
import oop.focus.finance.model.FinanceManager;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.homepage.view.GeneralHomePageController;

import java.util.ArrayList;
import java.util.List;

public class SectionsImpl implements Sections {
    private final List<Pair<Controller, String>> list;
    private final DataSource dataSource;
    private final FinanceManager financeManager;
    public SectionsImpl() {
        this.dataSource = new DataSourceImpl();
        this.financeManager = new FinanceManagerImpl(this.dataSource);
        this.list = new ArrayList<>();
        this.putControllers();
    }
    private void putControllers() {
        this.list.add(new Pair<>(new GeneralHomePageController(this.dataSource, this.financeManager), "Home Page"));
        this.list.add(new Pair<>(new BaseControllerImpl(this.financeManager), "Finanza"));
        this.list.add(new Pair<>(new CalendarControllerImpl(this.dataSource), "Calendario"));
        this.list.add(new Pair<>(new GeneralDiaryController(this.dataSource), "Diario"));
    }
    @Override
    public final List<Pair<Controller, String>> getList() {
        return this.list;
    }
}
