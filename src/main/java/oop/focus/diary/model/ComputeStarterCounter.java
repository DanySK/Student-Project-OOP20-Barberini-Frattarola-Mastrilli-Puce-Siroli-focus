package oop.focus.diary.model;

import java.util.Optional;

import org.joda.time.LocalTime;



/**
 * The interface can be used to compute all time spent to do an activity,
 * witch can be specified through the constructor.
 *
 */
public interface ComputeStarterCounter {
    /**
     * Compute the seconds spent to do an activity.
     * @param labelName   activity's name
     * @return  the seconds spent to do an activity
     */
    Optional<LocalTime> countSeconds(String labelName);

}
