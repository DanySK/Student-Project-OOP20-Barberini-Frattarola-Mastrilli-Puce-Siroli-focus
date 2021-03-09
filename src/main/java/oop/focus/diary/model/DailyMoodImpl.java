package oop.focus.diary.model;

import org.joda.time.LocalDate;

public class DailyMoodImpl implements DailyMood {
    private int value;
    private LocalDate date;
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
}
