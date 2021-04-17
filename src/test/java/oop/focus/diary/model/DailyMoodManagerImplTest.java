package oop.focus.diary.model;
import oop.focus.db.DataSourceImpl;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;
import org.junit.Test;

public class DailyMoodManagerImplTest {
    private final DailyMoodManagerImpl dailyMoodManager = new DailyMoodManagerImpl(new DataSourceImpl());
    @Test
    public void testDailyMood() throws DaoAccessException {
        final DailyMood mood1 =new DailyMoodImpl(2, LocalDate.now());
        final DailyMood mood2 = new DailyMoodImpl(3, LocalDate.now());
        final DailyMood mood3 = new DailyMoodImpl(4, LocalDate.now().plusDays(4));
        final DailyMood mood4 = new DailyMoodImpl(2, LocalDate.now().plusDays(2));
        this.dailyMoodManager.addDailyMood(mood1);
        this.dailyMoodManager.modifyDailyMood(mood2);
        System.out.println(this.dailyMoodManager.getMoodByDate(LocalDate.now()));
        this.dailyMoodManager.addDailyMood(mood3);
        System.out.println(this.dailyMoodManager.getMoodByDate(LocalDate.now().plusDays(4)));
        this.dailyMoodManager.deleteDailyMood(mood3);
        System.out.println(this.dailyMoodManager.getMoodByDate(LocalDate.now().plusDays(4)));
        this.dailyMoodManager.modifyDailyMood(mood4);
        System.out.println(this.dailyMoodManager.getMoodByDate(LocalDate.now().plusDays(2)));
        this.dailyMoodManager.deleteDailyMood(mood1);
        this.dailyMoodManager.deleteDailyMood(mood2);
        this.dailyMoodManager.deleteDailyMood(mood4);
    }
}