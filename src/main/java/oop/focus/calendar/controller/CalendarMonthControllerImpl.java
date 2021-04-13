package oop.focus.calendar.controller;



import java.util.List;
import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;



public class CalendarMonthControllerImpl implements CalendarMonthController {


    //Classes
    private final CalendarLogic calendarLogic;
    private final CalendarMonthView monthView;
    private final DataSource dataSource;

    //Variables
    private Format format;
    private double spacing;
    private double fontSize;

    //List
    private List<Day> month;


    //Costants
    private static final double SPACING = 50; 
    private static final int DEFAULT_FONT_SIZE = 18;

    /**
     * Used for Initialize the month controller.
     * @param type : type of calendar to build
     * @param dataSource
     */
    public CalendarMonthControllerImpl(final CalendarType type, final DataSource dataSource) {
        this.format = Format.NORMAL;
        this.spacing = SPACING;
        this.fontSize = DEFAULT_FONT_SIZE;
        calendarLogic = new CalendarLogicImpl(dataSource);
        this.month = calendarLogic.getMonth();
        this.dataSource = dataSource;
        monthView = new CalendarMonthViewImpl(type, this);
    }


    public final void configureDay(final CalendarDayController dayController) {
        dayController.setFormat(this.format);
        dayController.setSpacing(this.spacing);
    }

    public final CalendarLogic getCalendarLogic() {
        return this.calendarLogic;
    }

    public final void setFontSize(final double fontSize) {
        this.fontSize = fontSize;
    }

    public final double getFontSize() {
        return this.fontSize;
    }

    public final void setFormat(final Format format) {
        this.format = format;
    }


    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }


    public final List<Day> getMonth() {
        return this.month;
    }

    public final void setMonth() {
        this.month = this.calendarLogic.getMonth();
    }

    public final void updateView() {
        this.monthView.updateView(this.monthView);
    }

    public final View getView() {
        return this.monthView;
    }


    public final DataSource getDataSource() {
        return this.dataSource;
    } 

}
