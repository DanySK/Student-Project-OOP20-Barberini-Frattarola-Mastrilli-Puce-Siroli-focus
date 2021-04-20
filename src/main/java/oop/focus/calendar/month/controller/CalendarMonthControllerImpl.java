package oop.focus.calendar.month.controller;



import java.util.List;

import oop.focus.calendar.day.controller.CalendarDayController;
import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.CalendarType;
import oop.focus.calendar.model.Day;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.month.view.CalendarMonthView;
import oop.focus.calendar.month.view.CalendarMonthViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;


/**
 * Implementation of {@link CalendarMonthController}.
 */
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
     * @param dataSource : the {@link DataSource} from which to retrieve data
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

    /**
     * {@inheritDoc}
     */
    public final void configureDay(final CalendarDayController dayController) {
        dayController.setFormat(this.format);
        dayController.setSpacing(this.spacing);
    }

    /**
     * {@inheritDoc}
     */
    public final CalendarLogic getCalendarLogic() {
        return this.calendarLogic;
    }

    /**
     * {@inheritDoc}
     */
    public final void setFontSize(final double fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * {@inheritDoc}
     */
    public final double getFontSize() {
        return this.fontSize;
    }

    /**
     * {@inheritDoc}
     */
    public final void setFormat(final Format format) {
        this.format = format;
    }

    /**
     * {@inheritDoc}
     */
    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }

    /**
     * {@inheritDoc}
     */
    public final List<Day> getMonth() {
        return this.month;
    }

    /**
     * {@inheritDoc}
     */
    public final void setMonth() {
        this.month = this.calendarLogic.getMonth();
    }

    /**
     * {@inheritDoc}
     */
    public final void updateView() {
        this.monthView.updateView();
    }

    /**
     * {@inheritDoc}
     */
    public final View getView() {
        return this.monthView;
    }

    /**
     * {@inheritDoc}
     */
    public final DataSource getDataSource() {
        return this.dataSource;
    } 

}
