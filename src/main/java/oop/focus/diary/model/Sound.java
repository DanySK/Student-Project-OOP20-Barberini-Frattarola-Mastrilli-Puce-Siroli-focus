package oop.focus.diary.model;

/**
 * The interface has methods to manage alarm's sound at the end of a timer(when timer's counter is zero).
 */

public interface Sound {
    /**
     * The method is used to play alarm's sound.
     */
    void playSound();
    /**
     * The method is used to stop an alarm if it's playing.
     */
    void stopSound();

}
