package oop.focus.calendar;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.joda.time.LocalDate;
import oop.focus.homePage.model.Event;
import oop.focus.homePage.model.ManagerEventImpl;

public class DayImpl implements Day {

    private final LocalDate date;
    private final List<Event> events = new ArrayList<>();
    private final ManagerEventImpl manager = new ManagerEventImpl();

    public DayImpl(final LocalDate date) {
        this.date = date;
        final Set<Event> temp = manager.findByDate(date);
        this.events.addAll(temp);
    }
 
    /**
     * Can be used to get the number of the day.
     *
     * @return the number of the day 
     *
     */
    public int getNumber() {
        return  date.getDayOfMonth();
    }

    /**
     * Can be used to get the name of the day.
     *
     * @return the name of the day 
     *
     */
    public String getName() {
        return date.dayOfWeek().getAsText(Locale.ITALY);
    }

    /**
     * Can be used to get the Month of the day.
     *
     * @return the name of the Month 
     *
     */
    public String getMonth() {
        return date.monthOfYear().getAsText(Locale.ITALY);
    }

    /**
     * Can be used to get the Year of the day.
     *
     * @return the number of the Year 
     *
     */
    public int getYear() {
        return date.getYear();
    }

    /**
     * Can be used to get the list of the events of the day.
     *
     * @return the list of event of the day 
     *
     */
    public List<Event> getEvents() {
        return  events;
    }

    /**
     * Can be used to see if is Sunday.
     *
     * @return true if is Sunday 
     *
     */
    public boolean isSunday() {
        return "domenica".equalsIgnoreCase(getName());
    }



}
