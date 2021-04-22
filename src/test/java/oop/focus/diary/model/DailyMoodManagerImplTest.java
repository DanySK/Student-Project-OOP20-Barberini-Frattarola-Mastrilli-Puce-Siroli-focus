package oop.focus.diary.model;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class DailyMoodManagerImplTest {
    private final DailyMoodManagerImpl dailyMoodManager = new DailyMoodManagerImpl(new DataSourceImpl());
    @Test
    public void testDailyMood() throws DaoAccessException {
        final LocalDate date = new LocalDate(20, 10, 10);
        final LocalDate date1 = new LocalDate(20,10,11);
        final DailyMood mood1 = new DailyMoodImpl(2, date);
        final DailyMood mood2 = new DailyMoodImpl(3, date);
        final DailyMood mood3 = new DailyMoodImpl(4, date1);
        this.dailyMoodManager.addDailyMood(mood1);
        assertEquals(java.util.Optional.of(2), this.dailyMoodManager.getMoodByDate(date));
        this.dailyMoodManager.modifyDailyMood(mood2);
        assertEquals(java.util.Optional.of(3), this.dailyMoodManager.getMoodByDate(date));
        this.dailyMoodManager.addDailyMood(mood3);
        assertEquals(java.util.Optional.of(4), this.dailyMoodManager.getMoodByDate(date1));
        this.dailyMoodManager.deleteDailyMood(mood3);
        this.dailyMoodManager.deleteDailyMood(mood1);
        this.dailyMoodManager.deleteDailyMood(mood2);
    }
}