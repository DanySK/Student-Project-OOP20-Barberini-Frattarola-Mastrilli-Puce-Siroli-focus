package oop.focus.calendar;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;

public class CalendarLogicImpl implements CalendarLogic {

    private final LocalDate today = new LocalDate();
    private LocalDate current;
    private DayImpl day;
    private List<DayImpl> week;
    private List<DayImpl> month;
    private List<DayImpl> year;
    private static final int DAYFIRSTWEEK = 7;
    private static final int DAYLASTWEEK = 24;

    public CalendarLogicImpl() {
        this.current = this.today;
        this.week = new ArrayList<>();
        this.month = new ArrayList<>();
        this.year = new ArrayList<>();
    }

    /**
     * Used for find an specific day.
     * @param day is the date of the day that we want to get
     * @return a Day if exist or null if not
     */
    public DayImpl getDay(final LocalDate day) {
        if (!this.week.contains(new DayImpl(day))) {
            if (!this.month.contains(new DayImpl(day))) {
                if (!this.year.contains(new DayImpl(day))) {
                    return null;
                }
                return filter(this.year, day);
            }
            return filter(this.month, day);
        }
        return filter(this.week, day);
    }

    //used for find an specific day in a list
    private DayImpl filter(final List<DayImpl> time, final LocalDate day) {
        final DayImpl temp = this.generateDay(day);
        final List<DayImpl> dayset = time.stream()
                .filter(e -> e.equals(temp)).collect(Collectors.toList());
        return dayset.get(0);
    }

    /**
     * Used for generate an Calendar Day from a date.
     * @param day is the date of the day that we want to generate
     * @return an generated day
     */
    public DayImpl generateDay(final LocalDate day) {
        if (this.day == null) {
            this.day = new DayImpl(today);
            return this.day;
        }
        this.day = new DayImpl(day);
        return this.day;
    }

    /**
     * Used for get a Week.
     * @return a list of 7 days
     */
    public List<DayImpl> getWeek() {
        if (this.week.isEmpty()) {
            this.week = generateWeek();
        }
         return this.week;
    }

    /**
     * Used for get a Month.
     * @return a list of x days
     */
    public List<DayImpl> getMonth() {
        if (this.month.isEmpty()) {
            this.month = generateMonth();
        }
         return this.month;
    }

    /**
     * Used for get a Year.
     * @return a list of 365 days
     */
    public List<DayImpl> getYear() {
        if (this.year.isEmpty()) {
            this.year = generateYear();
        }
         return this.year;
    }

    /**
     * Used for generate a list of days (Week, Month, Year).
     * @param numberofdays is the number of days 
     * @param startingday is the day from it start to generate the calendar
     * @return a generated list of numberofdays days
     */
    public List<DayImpl> generate(final int numberofdays, final LocalDate startingday) {
        final List<DayImpl> time = new ArrayList<>();
        for (int i = 0; i < numberofdays; i++) {
            time.add(new DayImpl(startingday.plusDays(i)));
        }
        return time;
    }

    /**
    * Generate a list of 7 day. 
    * @return List of 7 generated days 
    */
    public List<DayImpl> generateWeek() {
        final LocalDate day = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), this.current.getDayOfMonth() - this.current.getDayOfWeek() + 1);
        this.week = generate(day.dayOfWeek().getMaximumValue(), day);
        return this.week;
    }

    /**
     * Generate a list of x day.
     * @return Set of x generated days 
     */
    public List<DayImpl> generateMonth() {
        final LocalDate day = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), 1);
        this.month = generate(day.dayOfMonth().getMaximumValue(), day);
        return this.month;
    }

    /**
     * Generate a list of 365 day.
     * @return Set of 365 generated days 
     */
    public List<DayImpl> generateYear() {
        final LocalDate day = new LocalDate(this.current.getYear(), 1, 1);
        this.year = generate(day.dayOfYear().getMaximumValue(), day);
        return this.year;
    }

    /**
     * Used for generate the next or the previous week.
     * @param change  ,if is true get the previous week, if is false the next one
     */
    public void  changeWeek(final boolean change) {
        if (change) { //previous
            if (this.current.getMonthOfYear() == 1 && this.current.getDayOfMonth() < DAYFIRSTWEEK) {
                this.current = new LocalDate(this.current.minusYears(1).getYear(), this.current.monthOfYear().getMaximumValue(), this.current.withMonthOfYear(this.current.monthOfYear().getMaximumValue()).dayOfMonth().getMaximumValue());
            } else {
                if (this.current.getDayOfMonth() - this.current.dayOfWeek().getMaximumValue() <= 1) {
                    this.current = new LocalDate(this.current.getYear(), this.current.minusMonths(1).getMonthOfYear(), this.current.minusMonths(1).dayOfMonth().getMaximumValue());
                } else {
                this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), this.current.getDayOfMonth() - this.current.dayOfWeek().getMaximumValue());
                }
            }
        } else { //next
            if (this.current.getMonthOfYear() == this.current.monthOfYear().getMaximumValue() && this.current.getDayOfMonth() >= DAYLASTWEEK) {
                this.current = new LocalDate(this.current.plusYears(1).getYear(), this.current.monthOfYear().getMinimumValue(), this.current.withMonthOfYear(this.current.monthOfYear().getMinimumValue()).dayOfMonth().getMinimumValue());
            } else {
                if (this.current.getDayOfMonth() + this.current.dayOfWeek().getMaximumValue() >= this.current.dayOfMonth().getMaximumValue()) {
                    this.current = new LocalDate(this.current.getYear(), this.current.plusMonths(1).getMonthOfYear(), this.current.plusMonths(1).dayOfMonth().getMinimumValue() + this.current.dayOfWeek().getMaximumValue());
                } else {
                this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), this.current.getDayOfMonth() + this.current.dayOfWeek().getMaximumValue());
                }
            }
        }
        this.week = generateWeek();
    }

    /**
     * Used for generate the next or the previous month.
     *  @param change ,if is true get the previous month, if is false the next one
     */
    public void  changeMonth(final boolean change) {
        if (change) { //previous
                if (this.current.getMonthOfYear() == this.current.monthOfYear().getMinimumValue()) {
                    this.current = new LocalDate(this.current.getYear() - 1, this.current.monthOfYear().getMaximumValue(), this.current.getDayOfMonth());
                } else {
                this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear() - 1, this.current.getDayOfMonth());
                }
            } else { //next
                if (this.current.getMonthOfYear() == this.current.monthOfYear().getMaximumValue()) {
                    this.current = new LocalDate(this.current.getYear() + 1, this.current.monthOfYear().getMinimumValue(), this.current.getDayOfMonth());
                } else {
                this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear() + 1, this.current.getDayOfMonth());
                }
        }
        this.month = generateMonth();
    }

    /**
     * Used for generate the next or the previous year.
     * @param change ,if is true get the previous year, if is false the next one
     */
    public void  changeYear(final boolean change) {
        if (change) { //previous
            this.current = new LocalDate(this.current.getYear() - 1, this.current.getMonthOfYear(), this.current.getDayOfMonth());
        } else { //next
            this.current = new LocalDate(this.current.getYear() + 1, this.current.getMonthOfYear(), this.current.getDayOfMonth());
        }
        this.year = generateYear();
    }


}
