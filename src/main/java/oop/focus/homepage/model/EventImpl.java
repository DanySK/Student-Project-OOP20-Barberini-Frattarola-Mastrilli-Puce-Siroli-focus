package oop.focus.homepage.model;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

/**
 * This class implements Event interface.
 */
public class EventImpl implements Event {

    private final String name;
    private final HotKeyType category;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Repetition repetition;
    private final List<Person> persons;

    /**
     * This is the class constructor.
     * @param name is the name of the event to create.
     *@param startDate is the start day of the event to create.
     * @param endDate is the end day of the event to create.
     * @param repetition is the field that tells if and when an event will repeat itself.
     */
    public EventImpl(final String name, final LocalDateTime startDate, final LocalDateTime endDate, final Repetition repetition) {
        this.name = name;
        this.category = HotKeyType.EVENT;
        this.startDate = startDate;
        this.endDate = endDate;
        this.repetition = repetition;
        this.persons = new ArrayList<>();
    }

    /**
     * This method is used to add a new person who will attend the event.
     * @param p is the person to add.
     */
    public final void addPerson(final Person p) {
        if (!this.persons.contains(p)) {
            this.persons.add(p);
        }
    }

    /**
     * This method is use to get the color of an event.
     * @return a String that rappresent the color of the event.
     */
    public final String getColor() {
        return this.category.getColor();
    }

    /**
     * This method is used to know both the end date and the end time of an event.
     * @return LocalDateTime.
     */
    public final LocalDateTime getEnd() {
        return this.endDate;
    }

    /**
     * This method is use for get the event end day.
     * @return a LocalDate that rappresent the event endDay.
     */
    public final LocalDate getEndDate() {
        return this.endDate.toLocalDate();
    }

    /**
     * This method is use for get the event end hour.
     * @return a LocalDateTime that rappresent the event endHour.
     */
    public final LocalTime getEndHour() {
        return this.endDate.toLocalTime();
    }

    /**
     * This method is use for get the event name.
     * @return a string that rappresent the event name.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * This method is used to know the list of people who will attend the event.
     * @return the persons list, return an empty list if no other people will attend.
     */
    public final List<Person> getPersons() {
        return this.persons;
    }

    /**
     * This method is used to know both the start date and the start time of an event.
     * @return LocalDateTime.
     */
    public final LocalDateTime getStart() {
        return this.startDate;
    }

    /**
     * This method is use for get the event start day.
     * @return a LocalDate that rappresent the event startDay.
     */
    public final LocalDate getStartDate() {
        return this.startDate.toLocalDate();
    }

    /**
     * This method is use for get the event start hour.
     * @return a LocalDateTime that rappresent the event startHour.
     */
    public final LocalTime getStartHour() {
        return this.startDate.toLocalTime();
    }

    /**
     * This method is used to know if an event repeats itself or not, and if it recurs, to know how often.
     * @return a member of the Repetition enumeration.
     */
    public final Repetition getRipetition() {
        return this.repetition;
    }

    /**
     * This is the hasCode related to the equals method.
     * @return an int.
     */
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    /**
     * This method is use to verify if an event is equals as another.
     * Two events are the same if their name and start date are the same.
     * @param obj is the event whose equality needs to be checked.
     * @return a boolean which will be true if the two events are equal and false if the two events are different.
     */
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
        final EventImpl other = (EventImpl) obj;
        if (name == null) {
            if (other.name != null) {
            return false;
        }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (startDate == null) {
            if (other.startDate != null) {
                return false;
            }
        } else if (!startDate.equals(other.startDate)) {
            return false;
        }
        return true;
    }

}
