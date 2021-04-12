package oop.focus.calendar.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;

import oop.focus.db.DataSource;

public class CalendarLogicImpl implements CalendarLogic {

    //Classes
    private DayImpl day;
    private final DataSource dataSource;

    //Variables
    private final LocalDate today;
    private LocalDate current;

    //List
    private List<DayImpl> week;
    private List<DayImpl> month;
    private List<DayImpl> year;

    //Costants
    private static final int DAYS_IN_WEEK = 7;

    /**
     * Used for Initialize the calendar logic.
     * @param dataSource
     */
    public CalendarLogicImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
        today = new LocalDate();
        this.current = this.today;
        this.week = new ArrayList<>();
        this.month = new ArrayList<>();
        this.year = new ArrayList<>();
    }


    public final DayImpl getDay(final LocalDate day) {
        if (!this.week.contains(new DayImpl(day, dataSource))) {
            if (!this.month.contains(new DayImpl(day, dataSource))) {
                if (!this.year.contains(new DayImpl(day, dataSource))) {
                    return generateDay(day);
                }
                return filter(this.year, day);
            }
            return filter(this.month, day);
        }
        return filter(this.week, day);
    }

    /**
     * Used for find an specific day in a list.
     * @param time : list with the days
     * @param day : day that we are searching
     * @return DayImpl : the day
     */
    private DayImpl filter(final List<DayImpl> time, final LocalDate day) {
        final DayImpl temp = this.generateDay(day);
        final List<DayImpl> dayset = time.stream()
                .filter(e -> e.equals(temp)).collect(Collectors.toList());
        return dayset.get(0);
    }


    public final DayImpl generateDay(final LocalDate day) {
        if (this.day == null) {
            this.day = new DayImpl(today, dataSource);
            return this.day;
        }
        this.day = new DayImpl(day, dataSource);
        return this.day;
    }


    public final List<DayImpl> getWeek() {
        if (this.week.isEmpty()) {
            this.week = generateWeek();
        }
         return this.week;
    }


    public final List<DayImpl> getMonth() {
        if (this.month.isEmpty()) {
            this.month = generateMonth();
        }
         return this.month;
    }


    public final List<DayImpl> getYear() {
        if (this.year.isEmpty()) {
            this.year = generateYear();
        }
         return this.year;
    }


    public final List<DayImpl> generate(final int numberOfDays, final LocalDate startingDate) {
        final List<DayImpl> time = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            time.add(new DayImpl(startingDate.plusDays(i), dataSource));
        }
        return time;
    }


    public final List<DayImpl> generateWeek() {
        this.getDay(this.current).getNumber();
        if (this.year.isEmpty()) {
        this.generateYear();
        }
        this.week.clear();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            this.week.add(this.getDay(this.current.minusDays(this.current.getDayOfWeek() - 1).plusDays(i)));
        }
        return this.week;
    }


    public final List<DayImpl> generateMonth() {
        final LocalDate day = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), 1);
        this.month = generate(day.dayOfMonth().getMaximumValue(), day);
        return this.month;
    }


    public final List<DayImpl> generateYear() {
        final LocalDate day = new LocalDate(this.current.getYear(), 1, 1);
        this.year = generate(day.dayOfYear().getMaximumValue(), day);
        return this.year;
    }


    public final void changeWeek(final boolean change) {
        if (change) { //previous
            this.current = this.current.minusDays(DAYS_IN_WEEK);
        } else { //next
            this.current = this.current.plusDays(DAYS_IN_WEEK);
        }
        this.week = generateWeek();
    }


    public final void changeMonth(final boolean change) {
        if (change) { //previous
                if (this.current.getMonthOfYear() == this.current.monthOfYear().getMinimumValue()) {
                    this.current = new LocalDate(this.current.getYear() - 1, this.current.monthOfYear().getMaximumValue(), 1);
                } else {
                this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear() - 1, 1);
                }
            } else { //next
                if (this.current.getMonthOfYear() == this.current.monthOfYear().getMaximumValue()) {
                    this.current = new LocalDate(this.current.getYear() + 1, this.current.monthOfYear().getMinimumValue(), 1);
                } else {
                this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear() + 1, 1);
                }
        }
        this.month = generateMonth();
    }


    public final void changeYear(final boolean change) {
        if (change) { //previous
            this.current = new LocalDate(this.current.getYear() - 1, this.current.getMonthOfYear(), this.current.getDayOfMonth());
        } else { //next
            this.current = new LocalDate(this.current.getYear() + 1, this.current.getMonthOfYear(), this.current.getDayOfMonth());
        }
        this.year = generateYear();
    }


}
