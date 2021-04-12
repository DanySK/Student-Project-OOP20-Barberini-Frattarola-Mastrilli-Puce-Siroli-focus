package oop.focus.application;

import javafx.scene.Node;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.db.DataSource;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.view.GeneralDiaryView;
import oop.focus.finance.controller.BaseControllerImpl;
import oop.focus.finance.model.FinanceManagerImpl;


public class CommonControllersImpl implements CommonControllers {
    public GeneralDiaryView generalDiaryView;
    public BaseControllerImpl baseController;
    public CalendarControllerImpl calendarController;
    public CommonControllersImpl() {
        DataSource dataSource = new DataSourceImpl();
        this.generalDiaryView = new GeneralDiaryView(dataSource);
        this.baseController = new BaseControllerImpl(new FinanceManagerImpl(dataSource));
        this.calendarController = new CalendarControllerImpl(dataSource);
    }
    @Override
    public final Node getDiary() {
        return this.generalDiaryView.getRoot();
    }
    @Override
    public final Node getFinance() {
        return this.baseController.getView().getRoot();
    }
    @Override
    public final Node getCalendar() {
        return this.calendarController.getView().getRoot();
    }

}
