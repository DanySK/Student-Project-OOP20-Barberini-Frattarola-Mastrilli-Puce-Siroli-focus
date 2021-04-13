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
     * The method saves the daily mood chosen, setting as day today itself.
     * @param value    the value of mood to save
     * @throws DaoAccessException   if the mood can't be saved
     */
    void addDailyMood(int value) throws DaoAccessException;

    /**
     * Return today's mood if is it chosen yet, an empty optional otherwise.
     * @return  the value of today's mood if present, nothing otherwise
     */
    Optional<Integer> getValueChosen();

    /**
     * The method deletes today's dailyMood(if it was set yet).
     * @throws DaoAccessException  if the mood can't be deleted
     */
    void removeChoice() throws DaoAccessException;
    Optional<Integer> getValueByDate(LocalDate date);
}
