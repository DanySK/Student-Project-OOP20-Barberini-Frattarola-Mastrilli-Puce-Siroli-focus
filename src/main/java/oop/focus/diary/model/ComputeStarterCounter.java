package oop.focus.diary.model;
/**
 * The interface can be used to compute all time spent to do an activity,
 * witch can be specified through the constructor.
 *
 */
public interface ComputeStarterCounter {
    /**
     * Compute the seconds spent to do an activity.
     * @return  the seconds spent to do an activity
     */
    long countSeconds();

}
