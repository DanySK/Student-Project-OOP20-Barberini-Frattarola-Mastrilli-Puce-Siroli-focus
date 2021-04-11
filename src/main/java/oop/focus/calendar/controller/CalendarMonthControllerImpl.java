package oop.focus.calendar.controller;



import java.util.List;
import oop.focus.calendar.model.CalendarLogic;
import oop.focus.calendar.model.CalendarLogicImpl;
import oop.focus.calendar.model.DayImpl;
import oop.focus.calendar.model.Format;
import oop.focus.calendar.view.CalendarMonthView;
import oop.focus.calendar.view.CalendarMonthViewImpl;
import oop.focus.common.View;
import oop.focus.db.DataSource;



public class CalendarMonthControllerImpl implements CalendarMonthController {


    //Classes
    private final CalendarLogic calendarlogic;
    private final CalendarMonthView monthview;

    //Variables

    private Format format;
    private double spacing;
    private double fontsize;

    //List
    private List<DayImpl> month;


    //Costants
    private static final double SPACING = 50; 
    private static final int DEFAULTFONTSIZE = 18;


    public CalendarMonthControllerImpl(final DataSource datasource, final double daywidth, final double dayheight) {
        this.format = Format.NORMAL;
        this.spacing = SPACING;
        this.fontsize = DEFAULTFONTSIZE;

        calendarlogic = new CalendarLogicImpl(datasource);
        this.month = calendarlogic.getMonth();

        monthview = new CalendarMonthViewImpl(this, daywidth, dayheight);
    }

    public final void configureday(final CalendarDayController daycontroller) {
        daycontroller.setFormat(this.format);
        daycontroller.setSpacing(this.spacing);
    }

    public final CalendarLogic getCalendarLogic() {
        return calendarlogic;
    }

    public final void setFontSize(final double fontsize) {
        this.fontsize = fontsize;
    }

    public final double getFontSize() {
        return this.fontsize;
    }

    public final void setFormat(final Format format) {
        this.format = format;
    }


    public final void setSpacing(final double spacing) {
        this.spacing = spacing;
    }


    public final List<DayImpl> getMonth() {
        return this.month;
    }

    public final void setMonth() {
        this.month = this.calendarlogic.getMonth();
    }

    public final void updateView() {
        monthview.updateView(monthview);
    }

    public final View getView() {
        return monthview;
    }

    public final void disableButton(final boolean flag) {
        monthview.disableButton(flag);
    }

}
