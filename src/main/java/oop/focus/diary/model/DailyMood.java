package oop.focus.diary.model;

import java.time.LocalDate;

public interface DailyMood {
    /**
     * @return  the id relatives to the daily mood
     */
    int getMoodId();
    /**
     * Sets the id relatives to the daily mood.
     * @param moodId
     */
    void setMoodId(int moodId);
    /**
     * @return the date of the registration of the mood 
     */
    LocalDate getDate();
    /**
     * Sets the date of the registration of mood.
     * @param date
     */
    void setDate(LocalDate date);

}
