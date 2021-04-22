package oop.focus.event.model;

import oop.focus.common.Repetition;

public interface EventExtension {

    /**
     * This method is used to set the event name.
     * @param newValue is the new name of the event.
     */
    void setName(String newValue);

    /**
     * This method is used to set the event repetition field.
     * @param repetition is the new repetition value.
     */
    void setRepetition(Repetition repetition);

    /**
     * This method is used to set the start day of the event.
     * @param localDate is the new start date.
     */
    void setStartDay(String localDate);

    /**
     * This method is used to set the start time of the event.
     * @param newValue is the new start time.
     */
    void setStartTime(String newValue);

}
