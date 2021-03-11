package oop.focus.calendar;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;

public class CalendarLogicImpl implements CalendarLogic {

    private final LocalDate today = new LocalDate();
    private DayImpl day;
    private List<DayImpl> week;
    private List<DayImpl> month;
    private List<DayImpl> year;

    public CalendarLogicImpl() {
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
            this.week = generateWeek(today);
        }
         return this.week;
    }

    /**
     * 
     * @return a list of x days
     */
    public List<DayImpl> getMonth() {
        if (this.month.isEmpty()) {
            this.month = generateMonth(today);
        }
         return this.month;
    }

    /**
     * 
     * @return a list of 365 days
     */
    public List<DayImpl> getYear() {
        if (this.year.isEmpty()) {
            this.year = generateYear(today);
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
    * @param startingday is the day from it start to generate the week
    * @return Set of 7 generated days 
    */
    public List<DayImpl> generateWeek(final LocalDate startingday) {
        final LocalDate day = new LocalDate(startingday.getYear(), startingday.getMonthOfYear(), startingday.getDayOfMonth() - startingday.getDayOfWeek() + 1);
        this.week = generate(startingday.dayOfWeek().getMaximumValue(), day);
        return this.week;
    }

    /**
     * generate a list of x day.
     * @param startingday is the day from it start to generate the month
     * @return Set of x generated days 
     */
    public List<DayImpl> generateMonth(final LocalDate startingday) {
        final LocalDate day = new LocalDate(startingday.getYear(), startingday.getMonthOfYear(), 1);
        this.month = generate(startingday.dayOfMonth().getMaximumValue(), day);
        return this.month;
    }

    /**
     * generate a list of 365 day.
     * @param startingday is the day from it start to generate the year
     * @return Set of 365 generated days 
     */
    public List<DayImpl> generateYear(final LocalDate startingday) {
        final LocalDate day = new LocalDate(startingday.getYear(), 1, 1);
        this.year = generate(startingday.dayOfYear() .getMaximumValue(), day);
        return this.year;
    }

    /**
     * ask to generate the next week.
     * @param firstdayoftheweek is the day from it start to generate the week
     * @param change  ,if is true get the previous week, if is false the next one
     */
    public void  changeWeek(final LocalDate firstdayoftheweek, final boolean change) {
        final LocalDate changeweek;
        if (change) { //previous
            changeweek = new LocalDate(firstdayoftheweek.getYear(), firstdayoftheweek.getMonthOfYear(), firstdayoftheweek.getDayOfWeek() - 1);
        } else { //next
            changeweek = new LocalDate(firstdayoftheweek.getYear(), firstdayoftheweek.getMonthOfYear(), firstdayoftheweek.getDayOfWeek() + 1);
        }
        this.week = generateWeek(changeweek);
    }

    /**
     * ask to generate the next month.
     * @param firstdayofthemonth is the day from it start to generate the month
     *  @param change  ,if is true get the previous month, if is false the next one
     */
    public void  changeMonth(final LocalDate firstdayofthemonth, final boolean change) {
        final LocalDate changemonth;
        if (change) { //previous
            changemonth = new LocalDate(firstdayofthemonth.getYear(), firstdayofthemonth.getMonthOfYear() - 1, firstdayofthemonth.getDayOfWeek());
        } else { //next
            changemonth = new LocalDate(firstdayofthemonth.getYear(), firstdayofthemonth.getMonthOfYear() + 1, firstdayofthemonth.getDayOfWeek());
        }
        this.month = generateMonth(changemonth);
    }

    /**
     * ask to generate the next year.
     * @param firstdayoftheyear is the day from it start to generate the year
     * @param change  ,if is true get the previous year, if is false the next one
     */
    public void  changeYear(final LocalDate firstdayoftheyear, final boolean change) {
        final LocalDate changeyear;
        if (change) { //previous
            changeyear = new LocalDate(firstdayoftheyear.getYear() - 1, firstdayoftheyear.getMonthOfYear(), firstdayoftheyear.getDayOfWeek());
        } else { //next
            changeyear = new LocalDate(firstdayoftheyear.getYear() + 1, firstdayoftheyear.getMonthOfYear(), firstdayoftheyear.getDayOfWeek());
        }
        this.year = generateYear(changeyear);
    }


}
