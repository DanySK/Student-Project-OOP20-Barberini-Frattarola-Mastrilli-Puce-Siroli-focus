package oop.focus.diary.model;

import java.time.LocalDate;
/**
 * The interface DailyMood can be used to register the daily mood.
 * It defines methods to set/get the date and the id representative of the mood.
 *
 */
public interface DailyMood {
    /**
     * @return  the id relatives to the daily mood
     */
    int getMoodId();
    /**
     * Sets the id relatives to the daily mood.
     * @param moodId    the id relatives to the daily mood
     */
    void setMoodId(int moodId);
    /**
     * @return the date of the registration of the mood 
     */
    LocalDate getDate();
    /**
     * Sets the date of the registration of mood.
     * @param date      the date of mood's registration
     */
    void setDate(LocalDate date);

}
