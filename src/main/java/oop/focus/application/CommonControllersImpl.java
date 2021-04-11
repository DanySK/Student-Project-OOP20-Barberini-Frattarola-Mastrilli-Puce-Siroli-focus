package oop.focus.application;

import javafx.scene.Node;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.view.GeneralDiaryView;
import oop.focus.finance.controller.BaseControllerImpl;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.homepage.model.EventManagerImpl;


public class CommonControllersImpl implements CommonControllers {
    private final DataSourceImpl dataSource;
    private final EventManagerImpl eventManager;
    public CommonControllersImpl() {
        this.dataSource = new DataSourceImpl();
        this.eventManager = new EventManagerImpl(this.dataSource);
    }
    @Override
    public final Node getDiary() {
        return new GeneralDiaryView(this.dataSource, this.eventManager).getRoot();
    }
    @Override
    public final Node getFinance() {
        return new BaseControllerImpl(new FinanceManagerImpl(this.dataSource)).getView().getRoot();
    }
    @Override
    public final Node getCalendar() {
        return new CalendarControllerImpl(dataSource).getView().getRoot();
    }

}
