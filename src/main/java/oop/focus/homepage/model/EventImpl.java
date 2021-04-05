package oop.focus.homepage.model;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import oop.focus.common.Repetition;



/**
 * This class implements Event interface.
 */
public class EventImpl implements Event {

    private final String name;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private Repetition repetition;
    private final List<Person> persons;
    private boolean isRepeated;

    /**
     * This is the class constructor.
     * @param name is the name of the event to create.
     * @param startDate is the start day of the event to create.
     * @param endDate is the end day of the event to create.
     * @param repetition is the field that tells if and when an event will repeat itself.
     * @param persons is the list of persons to add at the event.
     * @param isRepeat is true if the event is repeated false otherwise.
     */
    public EventImpl(final String name, final LocalDateTime startDate, final LocalDateTime endDate, final Repetition repetition, final List<Person> persons, final boolean isRepeat) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.repetition = repetition;
        this.persons = persons;
        this.isRepeated = isRepeat;
    }

    /**
     * This is the class constructor.
     * @param name is the name of the event to create.
     * @param startDate is the start day of the event to create.
     * @param endDate is the end day of the event to create.
     * @param repetition is the field that tells if and when an event will repeat itself.
     * @param persons is the list of persons to add at the event.
     */
    public EventImpl(final String name, final LocalDateTime startDate, final LocalDateTime endDate, final Repetition repetition, final List<Person> persons) {
        this(name, startDate, endDate, repetition, persons, !repetition.equals(Repetition.ONCE));
    }

    /**
     * This is the class constructor.
     * @param name is the name of the event to create.
     * @param startDate is the start day of the event to create.
     * @param endDate is the end day of the event to create.
     * @param repetition is the field that tells if and when an event will repeat itself.
     */
    public EventImpl(final String name, final LocalDateTime startDate, final LocalDateTime endDate, final Repetition repetition) {
        this(name, startDate, endDate, repetition, new ArrayList<>(), !repetition.equals(Repetition.ONCE));
    }

    public final void addPerson(final Person person) {
        this.persons.add(person);
    }

    public final LocalDateTime getEnd() {
        return this.endDate;
    }

    public final LocalDate getEndDate() {
        return this.endDate.toLocalDate();
    }

    public final LocalTime getEndHour() {
        return this.endDate.toLocalTime();
    }

    /**
     * This method is used for get the event name.
     * @return a string that represent the event name.
     */
    public final String getName() {
        return this.name;
    }

    public final Event getNextRenewal() {
        final LocalDate nextStart = this.repetition.getNextRenewalFunction().apply(new LocalDate(this.getStartDate().getYear(), this.getStartDate().getMonthOfYear(), this.getStartDate().getDayOfMonth()));
        final LocalDate nextEnd = this.repetition.getNextRenewalFunction().apply(new LocalDate(this.getEndDate().getYear(), this.getEndDate().getMonthOfYear(), this.getEndDate().getDayOfMonth()));
        return new EventImpl(this.name, nextStart.toLocalDateTime(this.getStartHour()), nextEnd.toLocalDateTime(this.getEndHour()), this.repetition);
    }

    public final List<Person> getPersons() {
        return this.persons;
    }

    public final LocalDateTime getStart() {
        return this.startDate;
    }

    public final LocalDate getStartDate() {
        return this.startDate.toLocalDate();
    }

    public final LocalTime getStartHour() {
        return this.startDate.toLocalTime();
    }

    public final Repetition getRipetition() {
        return this.repetition;
    }

    public final boolean isRepeated() {
        return isRepeated;
    }

    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

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
        if (startDate.toLocalTime().getHourOfDay() != other.startDate.toLocalTime().getHourOfDay()) {
            return false;
        }
        if (startDate.toLocalTime().getMinuteOfHour() != other.startDate.toLocalTime().getMinuteOfHour()) {
            return false;
        }
        if (startDate.toLocalDate() == null) {
            if (other.startDate.toLocalDate() != null) {
                return false;
            }
        } else if (!startDate.toLocalDate().equals(other.startDate.toLocalDate())) {
            return false;
        }
        return true;
    }

    public final void stopRepeat() {
        this.isRepeated = false;
    }

}
