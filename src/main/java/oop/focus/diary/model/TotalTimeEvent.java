package oop.focus.diary.model;


import java.util.Optional;


import org.joda.time.Period;


/**
 * The interface can be used to compute all time spent to do an activity,
 * witch can be specified through the constructor.
 *
 */
public interface TotalTimeEvent {
    /**
     * Compute the seconds spent to do an activity.
     * @param labelName   activity's name
     * @return  the period spent to do an activity (if the activity is present in dataBase)
    */
    Optional<Period> computePeriod(String labelName);

}
