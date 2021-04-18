package oop.focus.diary.controller;
import oop.focus.common.Controller;
import oop.focus.db.exceptions.DaoAccessException;
import org.joda.time.LocalDate;

import java.util.Optional;

/**
 * The interface can be used to add, remove or change the value of DailyMood Section.
 */
public interface DailyMoodController extends Controller {
    /**
     * Saves the daily mood chosen, setting as day today itself.
     * @param value    the value of mood to save
     * @throws DaoAccessException   if the mood can't be saved
     */
    void addDailyMood(int value) throws DaoAccessException;

    /**
     * Returns today's mood if is it chosen yet, an empty optional otherwise.
     * @return  the value of today's mood if present, nothing otherwise
     */
    Optional<Integer> getValueChosen();

    /**
     * Deletes today's dailyMood(if it was set yet).
     * @throws DaoAccessException  if the mood can't be deleted
     */
    void removeChoice() throws DaoAccessException;

    /**
     * Returns dailyMood's value in the date specified in input.
     * @param date  the {@link LocalDate} from which is returned the relative daily mood.
     * @return  the value of daily mood registered in a specific day
     */
    Optional<Integer> getValueByDate(LocalDate date);
}
