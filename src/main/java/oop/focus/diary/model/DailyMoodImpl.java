package oop.focus.diary.model;

import org.joda.time.LocalDate;

public class DailyMoodImpl implements DailyMood {
    private int value;
    private LocalDate date;
    public DailyMoodImpl(final int moodValue, final LocalDate date) {
        super();
        this.value = moodValue;
        this.date = date;
    }
    @Override
    public final int getMoodValue() {
        return value;
    }
    @Override
    public final void setMoodValue(final int moodValue) {
        this.value = moodValue;
    }
    @Override
    public final LocalDate getDate() {
        return date;
    }
    @Override
    public final void setDate(final LocalDate date) {
        this.date = date;
    }
}
