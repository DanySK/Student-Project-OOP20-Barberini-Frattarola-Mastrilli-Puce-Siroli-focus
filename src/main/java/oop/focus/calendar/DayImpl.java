package oop.focus.calendar;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.LocalDate;

import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.ManagerEventImpl;

public class DayImpl implements Day {

    private final LocalDate date;
    private final List<Event> events = new ArrayList<>();
    private final DataSourceImpl datasource = new DataSourceImpl();
    private final ManagerEventImpl manager = new ManagerEventImpl(datasource);

    public DayImpl(final LocalDate date) {
        this.date = date;
        final List<Event> temp = manager.findByDate(date);
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
        return  this.events;
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

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DayImpl other = (DayImpl) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        return true;
    }



}
