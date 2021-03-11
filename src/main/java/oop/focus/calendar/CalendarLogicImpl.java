package oop.focus.calendar;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;

public class CalendarLogicImpl implements CalendarLogic {

    private final LocalDate today = new LocalDate();
    private LocalDate current;
    private final int dayinaweek = today.dayOfWeek().getMaximumValue();
    private final int dayinayear = today.dayOfYear().getMaximumValue();
    private DayImpl day;
    private List<DayImpl> week;
    private List<DayImpl> month;
    private List<DayImpl> year;

    public CalendarLogicImpl() {
        this.current = this.today;
        this.week = new ArrayList<>();
        this.month = new ArrayList<>();
        this.year = new ArrayList<>();
    }

    /**
     * @param day is the number of the day that we want to get
     * @return all the information about a day
     */
    public DayImpl getDay(final LocalDate day) {
        if (this.week.isEmpty()) {
            if (this.month.isEmpty()) {
                if (this.year.isEmpty()) {
                    return null;
                }
                return filter(this.year, day);
            }
            return filter(this.month, day);
        }
        return filter(this.week, day);
    }

    private DayImpl filter(final List<DayImpl> time, final LocalDate day) {
        final List<DayImpl> dayset = time.stream()
                .filter(e -> e.getMonth().contentEquals(day.monthOfYear().getAsText(Locale.ITALY)))
                .filter(e -> e.getNumber() == day.getDayOfMonth()).collect(Collectors.toList());
        return dayset.get(0);
    }

    /**
     * @param day is the number of the day that we want to generate
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
     * 
     * @return a list of 7 days
     */
    public List<DayImpl> getWeek() {
        if (this.week.isEmpty()) {
            this.week = generateWeek();
        }
         return this.week;
    }

    /**
     * 
     * @return a list of x days
     */
    public List<DayImpl> getMonth() {
        if (this.month.isEmpty()) {
            this.month = generateMonth();
        }
         return this.month;
    }

    /**
     * 
     * @return a list of 365 days
     */
    public List<DayImpl> getYear() {
        if (this.year.isEmpty()) {
            this.year = generateYear();
        }
         return this.year;
    }

    /**
     * @param numberofdays is the number of day of the list
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
    * generate a list of 7 day.
    * @return Set of 7 generated days 
    */
    public List<DayImpl> generateWeek() {
        final LocalDate day = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), this.current.getDayOfMonth() - this.current.getDayOfWeek() + 1);
        this.week = generate(dayinaweek, day);
        return this.week;
    }

    /**
     * generate a list of x day.
     * @return Set of x generated days 
     */
    public List<DayImpl> generateMonth() {
        final LocalDate day = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), 1);
        this.month = generate(this.current.dayOfMonth().getMaximumValue(), day);
        return this.month;
    }

    /**
     * generate a list of 365 day.
     * @return Set of 365 generated days 
     */
    public List<DayImpl> generateYear() {
        final LocalDate day = new LocalDate(this.current.getYear(), 1, 1);
        this.year = generate(dayinayear, day);
        return this.year;
    }

    /**
     * ask to generate the next week.
     * @param change  ,if is true get the previous week, if is false the next one
     */
    public void  changeWeek(final boolean change) {
        if (change) { //previous
            this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), this.current.getDayOfMonth() - dayinaweek);
        } else { //next
            this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear(), this.current.getDayOfMonth() + dayinaweek);
        }
        this.week = generateWeek();
    }

    /**
     * ask to generate the next month.
     *  @param change  ,if is true get the previous month, if is false the next one
     */
    public void  changeMonth(final boolean change) {
        if (change) { //previous
            this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear() - 1, this.current.getDayOfMonth());
        } else { //next
            this.current = new LocalDate(this.current.getYear(), this.current.getMonthOfYear() + 1, this.current.getDayOfMonth());
        }
        this.month = generateMonth();
    }

    /**
     * ask to generate the next year.
     * @param change  ,if is true get the previous year, if is false the next one
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
