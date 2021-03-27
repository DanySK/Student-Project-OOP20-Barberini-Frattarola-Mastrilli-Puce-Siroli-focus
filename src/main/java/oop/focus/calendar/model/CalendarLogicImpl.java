package oop.focus.calendar.model;


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
    private static final int DAYSINWEEK = 7;


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
                    return generateDay(day);
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
        this.getDay(this.current).getNumber();
        if (this.year.isEmpty()) {
        this.generateYear();
        }
        this.week.clear();
        for (int i = 0; i < DAYSINWEEK; i++) {
            this.week.add(this.getDay(this.current.minusDays(this.current.getDayOfWeek() - 1).plusDays(i)));
        }
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
            this.current = this.current.minusDays(DAYSINWEEK);
        } else { //next
            this.current = this.current.plusDays(DAYSINWEEK);
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
