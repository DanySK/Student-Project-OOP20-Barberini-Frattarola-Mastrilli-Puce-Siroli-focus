package oop.focus.application;

import javafx.scene.Node;
import oop.focus.calendar.controller.CalendarControllerImpl;
import oop.focus.db.DataSourceImpl;
import oop.focus.diary.view.GeneralView;
import oop.focus.finance.controller.BaseControllerImpl;
import oop.focus.finance.model.FinanceManagerImpl;
import oop.focus.homepage.model.EventManagerImpl;

public enum Sections {
    /**
     * HomePage's section.
     */
    HOMEPAGE("HomePage", "bg-1", new GeneralView(new DataSourceImpl(), new EventManagerImpl(new DataSourceImpl())).getRoot()),
    /**
     * Finance's section.
     */
    FINANCE("Finanze", "", new BaseControllerImpl(new FinanceManagerImpl(UseCommonController.getDataSource())).getView().getRoot()),
    /**
     * Diary's section.
     */
    DIARY("Diario", "", new GeneralView(new DataSourceImpl(), new EventManagerImpl(new DataSourceImpl())).getRoot()),
    /**
     * Calendar's section.
     */
    CALENDAR("Calendario", "", new CalendarControllerImpl(300, 150, 200, 500).getCalendarPage());


    private final String name;
    private final String style;
    private final Node view;
    Sections(final String name, final String style,  final Node view) {
        this.name = name;
        this.style = style;
        this.view = view;
    }
    public String getName() {
        return this.name;
    }
    public String getStyle() {
        return this.style;
    }

    public Node getView() {
        return this.view;
    }
}
