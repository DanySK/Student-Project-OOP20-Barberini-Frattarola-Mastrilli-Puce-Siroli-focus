package oop.focus.diary.model;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DailyMoodManagerImplTest {
    private final DailyMoodManagerImpl dailyMoodManager = new DailyMoodManagerImpl(new DataSourceImpl());
    @Test
    public void testDailyMood() throws DaoAccessException {
        final DailyMood mood1 =new DailyMoodImpl(2, LocalDate.now());
        final DailyMood mood2 = new DailyMoodImpl(3, LocalDate.now());
        final DailyMood mood3 = new DailyMoodImpl(4, LocalDate.now().plusDays(4));
        this.dailyMoodManager.addDailyMood(mood1);
        assertEquals(java.util.Optional.of(2), this.dailyMoodManager.getMoodByDate(LocalDate.now()));
        this.dailyMoodManager.modifyDailyMood(mood2);
        assertEquals(java.util.Optional.of(3), this.dailyMoodManager.getMoodByDate(LocalDate.now()));
        this.dailyMoodManager.addDailyMood(mood3);
        assertEquals(java.util.Optional.of(4), this.dailyMoodManager.getMoodByDate(LocalDate.now().plusDays(4)));
        this.dailyMoodManager.deleteDailyMood(mood3);
        this.dailyMoodManager.deleteDailyMood(mood1);
        this.dailyMoodManager.deleteDailyMood(mood2);
    }
}