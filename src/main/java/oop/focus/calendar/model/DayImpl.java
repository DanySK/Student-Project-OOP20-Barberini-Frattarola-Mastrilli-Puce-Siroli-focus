package oop.focus.calendar.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import oop.focus.common.Repetition;
//import oop.focus.db.DataSourceImpl;
import oop.focus.homepage.model.Event;
//import oop.focus.homepage.model.ManagerEventImpl;
import oop.focus.homepage.model.EventImpl;

public class DayImpl implements Day {

    private final Event first = new EventImpl("Shopping", new LocalDateTime(2021, 9, 26, 9, 30), new LocalDateTime(2021, 9, 26, 10, 30), Repetition.ONCE);
    private final Event second = new EventImpl("Palestra", new LocalDateTime(2021, 9, 26, 11, 00), new LocalDateTime(2021, 9, 26, 11, 30), Repetition.ONCE);
    private final Event test = new EventImpl("Palestra", new LocalDateTime(2021, 9, 26, 11, 30), new LocalDateTime(2021, 9, 26, 12, 00), Repetition.ONCE);
    private final Event third = new EventImpl("Università", new LocalDateTime(2021, 9, 26, 14, 00), new LocalDateTime(2021, 9, 26, 17, 30), Repetition.ONCE);
    private final Event four = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 19, 30), new LocalDateTime(2021, 9, 26, 22, 45), Repetition.ONCE);
    private final Event five = new EventImpl("Cinema", new LocalDateTime(2021, 9, 26, 22, 45), new LocalDateTime(2021, 9, 26, 23, 30), Repetition.ONCE);

    private final LocalDate date;
    private final List<Event> events = new ArrayList<>();
    //private final DataSourceImpl datasource = new DataSourceImpl();
    //private final ManagerEventImpl manager = new ManagerEventImpl(datasource);

    public DayImpl(final LocalDate date) {
        this.date = date;
        subito();
        //final List<Event> temp = null; //manager.findByDate(date);
        //this.events.addAll(temp);
    }

    private void subito() {
        this.events.add(first);
        this.events.add(second);
        this.events.add(test);
        this.events.add(third);
        this.events.add(four);
        this.events.add(five);
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


    public final int getYear() {
        return date.getYear();
    }


    public final List<Event> getEvents() {
        return  this.events;
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
