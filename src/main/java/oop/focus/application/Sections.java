package oop.focus.application;
import javafx.util.Pair;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.common.Controller;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.finance.controller.BaseControllerImpl;
import oop.focus.finance.model.FinanceManagerImpl;
import java.util.ArrayList;
import java.util.List;

public class Sections {
    private final List<Pair<Controller, String>> list;
    private final DataSource dataSource;
    public Sections() {
        this.dataSource = new DataSourceImpl();
        this.list = new ArrayList<>();
        this.putControllers();
    }
    private void putControllers() {
        this.list.add(new Pair<>(new BaseControllerImpl(new FinanceManagerImpl(this.dataSource)), "Finanza"));
        this.list.add(new Pair<>(new CalendarControllerImpl(dataSource), "Calendario"));
    }
    public final List<Pair<Controller, String>> getList() {
        return list;
    }
}
