package oop.focus.diary.model;

import junit.framework.TestCase;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;
import org.junit.Test;

public class DailyMoodManagerImplTest extends TestCase {
    private final DailyMoodManagerImpl dailyMoodManager = new DailyMoodManagerImpl(new DataSourceImpl());
    @Test
    public void testDailyMood() throws DaoAccessException {
        dailyMoodManager.addDailyMood(new DailyMoodImpl(2, LocalDate.now()));
        dailyMoodManager.modifyDailyMood(new DailyMoodImpl(3, LocalDate.now()));
        System.out.println(dailyMoodManager.getMoodByDate(LocalDate.now()));
        final DailyMood mood = new DailyMoodImpl(4, LocalDate.now().plusDays(4));
        dailyMoodManager.addDailyMood(mood);
        System.out.println(dailyMoodManager.getMoodByDate(LocalDate.now().plusDays(4)));
        dailyMoodManager.deleteDailyMood(mood);
        System.out.println(dailyMoodManager.getMoodByDate(LocalDate.now().plusDays(4)));
        dailyMoodManager.modifyDailyMood(new DailyMoodImpl(2, LocalDate.now().plusDays(2)));
        System.out.println(dailyMoodManager.getMoodByDate(LocalDate.now().plusDays(2)));
    }
}