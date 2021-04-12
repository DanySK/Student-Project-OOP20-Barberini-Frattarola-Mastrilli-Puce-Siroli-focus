package oop.focus.calendar.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.LocalDate;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;

public class DayImpl implements Day {

    private final LocalDate date;
    private final List<Event> events;
    private final List<Event> dailyevents;


    public DayImpl(final LocalDate date, final DataSource datasource) {
        final EventManager manager = new EventManagerImpl(datasource);
        events = new ArrayList<>();
        dailyevents = new ArrayList<>();
        this.date = date;
        final List<Event> temp = manager.findByDate(date);
        this.events.addAll(manager.takeOnly(temp));
        this.dailyevents.addAll(manager.takeOnlyDailyEvent(temp));
    }

    public final int getNumber() {
        return  date.getDayOfMonth();
    }


    public final int getDayOfTheWeek() {
        return  date.getDayOfWeek();
    }


    public final String getName() {
        return date.dayOfWeek().getAsText(Locale.ITALY);
    }


    public final String getMonth() {
        return date.monthOfYear().getAsText(Locale.ITALY);
    }

    public final int getMonthNumber() {
        return date.getMonthOfYear();
    }

    public final int getYear() {
        return date.getYear();
    }


    public final List<Event> getEvents() {
        return  this.events;
    }

    public final List<Event> getDailyEvents() {
        return  this.dailyevents;
    }


    public final boolean isSunday() {
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
