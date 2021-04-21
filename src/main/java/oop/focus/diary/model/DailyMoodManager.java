package oop.focus.diary.model;
import java.util.Optional;
import org.joda.time.LocalDate;
import oop.focus.db.exceptions.DaoAccessException;

/**
 * The interface can be used to add, remove or change by database, the value of {@link DailyMood}.
 */
    public interface DailyMoodManager {
    /**
     * Saves new DailyMood.
     * @param mood  the dailyMood to be added
     * @throws DaoAccessException   if is not possible to save the mood
     */
    void addDailyMood(DailyMood mood) throws DaoAccessException;
    /**
     * Changes the value of a register mood.
     * @param mood  the mood to be updated
     * @throws DaoAccessException   if is not possible to update the mood
     */
    void modifyDailyMood(DailyMood mood) throws DaoAccessException;
    /**
     * Deletes a register mood.
     * @param mood  the mood to be deleted
     * @throws DaoAccessException   if is not possible to remove the mood
     */
    void deleteDailyMood(DailyMood mood) throws DaoAccessException;

    /**
     * Returns the value of mood on the input's date.
     * @param date  the date in which compute the mood
     * @return  an optional, which is empty if no mood is register in that date, or with mood value otherwise
     */
    Optional<Integer> getMoodByDate(LocalDate date);
    }
