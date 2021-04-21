package oop.focus.diary.model;

import javafx.collections.ObservableSet;
import oop.focus.db.Dao;
import oop.focus.db.DataSource;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;

import java.util.Optional;
/**
 * Immutable implementation of {@link DailyMoodManager}.
 */
public class DailyMoodManagerImpl implements DailyMoodManager {
    private final Dao<DailyMood> dm;

    /**
     * Instantiates a new daily mood manager.
     * @param dataSource   the {@link DataSource} from which to retrieve data
     */
    public DailyMoodManagerImpl(final DataSource dataSource) {
        this.dm = dataSource.getDailyMoods();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDailyMood(final DailyMood mood) throws DaoAccessException {
        if (!this.dm.getAll().contains(mood)) {
            this.dm.save(mood);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyDailyMood(final DailyMood mood) throws DaoAccessException {
        final Optional<DailyMood> moodToUpdate = this.dm.getAll().stream().filter(x -> x.getDate().equals(mood.
                getDate())).findAny();
        if (moodToUpdate.isPresent()) {
            this.dm.update(mood);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteDailyMood(final DailyMood mood) throws DaoAccessException {
        if (this.dm.getAll().contains(mood)) {
            this.dm.delete(mood);
        }
    }

    /**
     * Returns all register moods.
     * @return  an {@link ObservableSet} with all register DailyMood
     */
    private ObservableSet<DailyMood> getAllMoods() {
        return this.dm.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getMoodByDate(final LocalDate date) {
        final Optional<DailyMood> mood = this.getAllMoods().stream().filter(x -> x.getDate().equals(date)).findAny();
        return mood.map(DailyMood::getMoodValue);
    }
}
