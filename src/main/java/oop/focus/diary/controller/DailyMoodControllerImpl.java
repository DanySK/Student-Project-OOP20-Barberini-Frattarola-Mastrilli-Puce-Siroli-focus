package oop.focus.diary.controller;

import oop.focus.common.Controller;
import oop.focus.common.View;
import oop.focus.db.exceptions.DaoAccessException;
import oop.focus.diary.model.DailyMood;
import oop.focus.diary.model.DailyMoodImpl;
import oop.focus.diary.model.DailyMoodManager;
import oop.focus.diary.view.DailyMoodSection;
import org.joda.time.LocalDate;

import java.util.Optional;

public class DailyMoodControllerImpl implements DailyMoodController, Controller {
    private final DailyMoodManager manager;
    private DailyMood dailyMood;

    public DailyMoodControllerImpl(final DailyMoodManager manager) {
        this.manager = manager;
        if (this.manager.getMoodByDate(LocalDate.now()).isPresent()) {
            this.dailyMood = new DailyMoodImpl(this.manager.getMoodByDate(LocalDate.now()).get(), LocalDate.now());
        }
    }
    @Override
    public final void addDailyMood(final int value) throws DaoAccessException {
        this.dailyMood = new DailyMoodImpl(value, LocalDate.now());
        this.manager.addDailyMood(this.dailyMood);
    }
    @Override
    public final Optional<Integer> getValueChosen() {
        return this.manager.getMoodByDate(LocalDate.now());
    }
    public final Optional<Integer> getValueByDate(final LocalDate date) {
        return this.manager.getMoodByDate(date);
    }

    @Override
    public final void removeChoice() throws DaoAccessException {
        if (this.getValueChosen().isPresent()) {
            this.manager.deleteDailyMood(this.dailyMood);
        }
    }

    @Override
    public final View getView() {
        return new DailyMoodSection(this);
    }
}
