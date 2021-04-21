package oop.focus.calendar.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;

import oop.focus.db.DataSource;
/**
 * Implementation of {@link CalendarLogic}.
 */
public class CalendarLogicImpl implements CalendarLogic {

    //Classes
    private Day day;
    private final DataSource dataSource;

    //Variables
    private final LocalDate today;
    private LocalDate current;

    //List
    private List<Day> week;
    private List<Day> month;
    private List<Day> year;

    //Costants
    private static final int DAYS_IN_WEEK = 7;

    /**
     * Used for Initialize the calendar logic.
     * @param dataSource : dataSource the {@link DataSource} from which to retrieve data
     */
    public CalendarLogicImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
        this.today = new LocalDate();
        this.current = this.today;
        this.week = new ArrayList<>();
        this.month = new ArrayList<>();
        this.year = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    public final Day getDay(final LocalDate day) {
        if (!this.week.contains(new DayImpl(day, this.dataSource))) {
            if (!this.month.contains(new DayImpl(day, this.dataSource))) {
                if (!this.year.contains(new DayImpl(day, this.dataSource))) {
                    return this.generateDay(day);
                }
                return this.filter(this.year, day);
            }
            return this.filter(this.month, day);
        }
        return this.filter(this.week, day);
    }

    /**
     * Used for find an specific {@link Day} in a list.
     * @param time : list with the days
     * @param day : {@link LocalDate} that we are searching
     * @return {@link Day}
     */
    private Day filter(final List<Day> time, final LocalDate day) {
        final Day temp = this.generateDay(day);
        final List<Day> dayset = time.stream()
                .filter(e -> e.equals(temp)).collect(Collectors.toList());
        return dayset.get(0);
    }

    /**
     * Used for build a day from a date.
     * @param day : {@link LocalDate} of the day that we want to generate
     * @return Day : an generated {@link Day}
     */
    private Day generateDay(final LocalDate day) {
        if (this.day == null) {
            this.day = new DayImpl(this.today, this.dataSource);
            return this.day;
        }
        this.day = new DayImpl(day, this.dataSource);
        return this.day;
    }

    /**
     * {@inheritDoc}
     */
    public final List<Day> getWeek() {
        if (this.week.isEmpty()) {
            this.week = this.generateWeek();
        }
         return this.week;
    }

    /**
     * {@inheritDoc}
     */
    public final List<Day> getMonth() {
        if (this.month.isEmpty()) {
            this.month = this.generateMonth();
        }
         return this.month;
    }

    /**
     * {@inheritDoc}
     */
    public final List<Day> getYear() {
        if (this.year.isEmpty()) {
            this.year = this.generateYear();
        }
         return this.year;
    }

    /**
     * Used for generate one of the Calendar List.
     * @param numberOfDays : is the number of day of the list
     * @param startingDate : is the date of the day from it start to generate the calendar
     * @return List : a generated list of number number of {@link Day}.
     */
    private List<Day> generate(final int numberOfDays, final LocalDate startingDate) {
        final List<Day> time = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            time.add(new DayImpl(startingDate.plusDays(i), this.dataSource));
        }
        return time;
    }

    /**
     * {@inheritDoc}
     */
    public final List<Day> generateWeek() {
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

    /**
     * {@inheritDoc}
     */
    public final List<Day> generateMonth() {
        final LocalDate day = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), 1);
        this.month = this.generate(day.dayOfMonth().getMaximumValue(), day);
        return this.month;
    }

    /**
     * {@inheritDoc}
     */
    public final List<Day> generateYear() {
        final LocalDate day = new LocalDate(this.current.getYear(), 1, 1);
        this.year = this.generate(day.dayOfYear().getMaximumValue(), day);
        return this.year;
    }

    /**
     * {@inheritDoc}
     */
    public final void changeWeek(final boolean change) {
        if (change) { //previous
            this.current = this.current.minusDays(DAYS_IN_WEEK);
        } else { //next
            this.current = this.current.plusDays(DAYS_IN_WEEK);
        }
        this.week = this.generateWeek();
    }

    /**
     * {@inheritDoc}
     */
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
        this.month = this.generateMonth();
    }

    /**
     * {@inheritDoc}
     */
    public final void changeYear(final boolean change) {
        if (change) { //previous
            this.current = new LocalDate(this.current.getYear() - 1, this.current.getMonthOfYear(), this.current.getDayOfMonth());
        } else { //next
            this.current = new LocalDate(this.current.getYear() + 1, this.current.getMonthOfYear(), this.current.getDayOfMonth());
        }
        this.year = this.generateYear();
    }


}
