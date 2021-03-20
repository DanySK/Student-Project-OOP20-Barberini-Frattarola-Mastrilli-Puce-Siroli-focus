package oop.focus.diary.model;

import oop.focus.db.Dao;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DailyMoodManagerImpl implements DailyMoodManager {
    private final Dao<DailyMood> dm;
    public DailyMoodManagerImpl(final DataSourceImpl dsi) {
        this.dm = dsi.getDailyMoods();
    }
    @Override
    public final void addDailyMood(final DailyMood mood) throws DaoAccessException {
        if (!dm.getAll().contains(mood)) {
            this.dm.save(mood);
        }
    }
    @Override
    public final void modifyDailyMood(final DailyMood mood) throws DaoAccessException {
        final Optional<DailyMood> moodToUpdate = this.dm.getAll().stream().filter(x -> x.getDate().equals(mood.getDate())).findAny();
        if (moodToUpdate.isPresent()) {
            this.dm.update(mood);
        }
    }
    @Override
    public final void deleteDailyMood(final DailyMood mood) throws DaoAccessException {
        if (this.dm.getAll().contains(mood)) {
            this.dm.delete(mood);
        }
    }
    @Override
    public final List<DailyMood> getAllMoods() {
        return this.dm.getAll();
    }
    @Override
    public final Optional<Integer> getMoodByDate(final LocalDate date) {
        final Optional<DailyMood> mood = this.getAllMoods().stream().filter(x -> x.getDate().equals(date)).findAny();
        if (mood.isPresent()) {
            return Optional.of(mood.get().getMoodValue());
        }
        return Optional.empty();
    }
}
