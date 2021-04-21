package oop.focus.diary.model;

import org.joda.time.LocalDate;

/**
 * Implementation of {@link DailyMood}.
 */
public class DailyMoodImpl implements DailyMood {
    private int value;
    private final LocalDate date;

    /**
     * Instantiates a new daily mood.
     * @param value the value to associate to daily mood
     * @param date  the date in which daily mood is saved
     */
    public DailyMoodImpl(final int value, final LocalDate date) {
        super();
        this.value = value;
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMoodValue() {
        return this.value;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setMoodValue(final int moodValue) {
        this.value = moodValue;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final DailyMoodImpl other = (DailyMoodImpl) obj;
        if (this.date == null) {
            return other.date == null;
        } else  {
            return this.date.equals(other.date); 
            }
    }
}
