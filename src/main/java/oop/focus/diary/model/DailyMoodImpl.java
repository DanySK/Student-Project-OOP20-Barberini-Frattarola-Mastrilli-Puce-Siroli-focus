package oop.focus.diary.model;

import org.joda.time.LocalDate;

public class DailyMoodImpl implements DailyMood {
    private int value;
    private LocalDate date;
    public DailyMoodImpl(final int value, final LocalDate date) {
        super();
        this.value = value;
        this.date = date;
    }
    @Override
    public final int getMoodValue() {
        return this.value;
    }
    @Override
    public final void setMoodValue(final int moodValue) {
        this.value = moodValue;
    }
    @Override
    public final LocalDate getDate() {
        return this.date;
    }
    @Override
    public final void setDate(final LocalDate date) {
        this.date = date;
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
        final DailyMoodImpl other = (DailyMoodImpl) obj;
        if (this.date == null) {
            return other.date == null;
        } else if (!date.equals(other.date)) {
            return false;
        }
        return true;
    }
}
