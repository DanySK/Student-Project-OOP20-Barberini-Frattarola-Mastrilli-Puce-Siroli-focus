package oop.focus.calendar.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.LocalDate;
import oop.focus.db.DataSource;
import oop.focus.event.model.Event;
import oop.focus.event.model.EventManager;
import oop.focus.event.model.EventManagerImpl;
import oop.focus.event.model.Filter;
/**
 * Implementation of {@link Day}.
 */
public class DayImpl implements Day {

    private final LocalDate date;
    private final List<Event> events;
    private final List<Event> dailyEvents;

    /**
     * Used for Initialize the Day.
     * @param date : {@link LocalDate}} of the day
     * @param dataSource : dataSource the {@link DataSource} from which to retrieve data
     */
    public DayImpl(final LocalDate date, final DataSource dataSource) {
        final EventManager manager = new EventManagerImpl(dataSource);
        this.events = new ArrayList<>();
        this.dailyEvents = new ArrayList<>();
        this.date = date;
        final List<Event> temp = Filter.getEventsWithDuration(manager.findByDate(date));
        final List<Event> future = manager.getFutureEvent(date);
        future.forEach(e -> {
            if (!temp.contains(e)) {
                temp.add(e);
            }
        });
        this.events.addAll(Filter.takeOnly(temp));
        this.dailyEvents.addAll(Filter.takeOnlyDailyEvent(temp));
    }

    /**
     * {@inheritDoc}
     */
    public final int getNumber() {
        return this.date.getDayOfMonth();
    }

    /**
     * {@inheritDoc}
     */
    public final int getDayOfTheWeek() {
        return this.date.getDayOfWeek();
    }

    /**
     * {@inheritDoc}
     */
    public final String getName() {
        return this.date.dayOfWeek().getAsText(Locale.ITALY);
    }

    /**
     * {@inheritDoc}
     */
    public final String getMonth() {
        return this.date.monthOfYear().getAsText(Locale.ITALY);
    }

    /**
     * {@inheritDoc}
     */
    public final int getMonthNumber() {
        return this.date.getMonthOfYear();
    }

    /**
     * {@inheritDoc}
     */
    public final int getYear() {
        return this.date.getYear();
    }

    /**
     * {@inheritDoc}
     */
    public final List<Event> getEvents() {
        return  this.events;
    }

    /**
     * {@inheritDoc}
     */
    public final List<Event> getDailyEvents() {
        return  this.dailyEvents;
    }

    /**
     * {@inheritDoc}
     */
    public final boolean isSunday() {
        return "domenica".equalsIgnoreCase(this.getName());
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final DayImpl other = (DayImpl) obj;
        if (this.date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!this.date.equals(other.date)) {
            return false;
        }
        return true;
    }



}
