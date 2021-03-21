package oop.focus.diary.model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * The interface has methods to manage alarm's sound at the end of a timer(when timer's counter is zero).
 *
 */

public interface Sound {
    /**
     * The method is used to play alarm's sound. 
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    void playSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException;
    /**
     * The method is used to stop an alarm if it's playing.
     */
    void stopSound();
    /**
     * Returns if alarm is playing or not.
     * @return  true if alarm is playing, false otherwise.
     */
    boolean isPlaying();

}
