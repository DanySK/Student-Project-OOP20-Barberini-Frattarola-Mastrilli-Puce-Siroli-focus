package oop.focus.calendar.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.LocalDate;
import oop.focus.db.DataSource;
import oop.focus.homepage.model.Event;
import oop.focus.homepage.model.EventManager;
import oop.focus.homepage.model.EventManagerImpl;
/**
 * Implementation of {@link Day}.
 */
public class DayImpl implements Day {

    private final LocalDate date;
    private final List<Event> events;
    private final List<Event> dailyEvents;

    /**
     * Used for Initialize the Day.
     * @param date : date of the day
     * @param dataSource : dataSource the {@link DataSource} from which to retrieve data
     */
    public DayImpl(final LocalDate date, final DataSource dataSource) {
        final EventManager manager = new EventManagerImpl(dataSource);
        events = new ArrayList<>();
        dailyEvents = new ArrayList<>();
        this.date = date;
        final List<Event> temp = manager.getEventsWithDuration(manager.findByDate(date));
        final List<Event> future = manager.getFutureEvent(date);
        future.stream().forEach(e -> {
            if (!temp.contains(e)) {
                temp.add(e);
            }
        });
        this.events.addAll(manager.takeOnly(temp));
        this.dailyEvents.addAll(manager.takeOnlyDailyEvent(temp));
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
        return  this.dailyEvents;
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
