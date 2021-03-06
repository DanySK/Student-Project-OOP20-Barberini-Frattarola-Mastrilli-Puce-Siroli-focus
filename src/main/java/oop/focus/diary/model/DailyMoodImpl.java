package oop.focus.diary.model;

import java.time.LocalDate;

public class DailyMoodImpl implements DailyMood {
    private int moodId;
    private LocalDate date;
    public DailyMoodImpl(final int moodId, final LocalDate date) {
        super();
        this.moodId = moodId;
        this.date = date;
    }
    @Override
    public final int getMoodId() {
        return moodId;
    }
    @Override
    public final void setMoodId(final int moodId) {
        this.moodId = moodId;
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
