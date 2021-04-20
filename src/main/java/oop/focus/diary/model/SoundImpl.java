package oop.focus.diary.model;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Implementation of {@link Sound}. The class manages alarm's sound.
 */
public class SoundImpl implements Sound {
    private static final String SEP = File.separator;
    private final Path soundDir = Path.of(new File(".").getCanonicalPath() + SEP + "src" + SEP + "main" 
            + SEP + "resources" + SEP + "sounds");
    private Path alarmPath;
    private final Clip clip;

    /**
     * Instantiates a new Sound and sets the relative {@link Clip}.
     * @throws UnsupportedAudioFileException    if file doesn't contain valid data
     * @throws IOException  if an I/O operation has been interrupted.
     * @throws LineUnavailableException if a {@link javax.sound.sampled.Line} cannot be opened.
     */
    public SoundImpl() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.setAlarmPath();
        this.clip = AudioSystem.getClip();
        this.clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(this.alarmPath.toFile()))));
    }

    /**
     * Sets the path of directory of alarm sound.
     */
    private void setAlarmPath() {
        URI uri = null;
        try {
            uri = ClassLoader.getSystemResource("sounds//").toURI();
            String mainPath = Paths.get(uri).toString();
            this.alarmPath = Paths.get(mainPath , "alarm.wav");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void playSound()  {
        if (!this.clip.isRunning()) {
            this.clip.start();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void stopSound() {
        if (this.isPlaying()) {
            this.clip.stop();
            this.clip.close();
        }
    }
    /**
     * The method can be used to know if an alarm's counter is playing or not.
     * @return  true if alarm is playing, false otherwise.
     */
    private boolean isPlaying() {
        return this.clip.isRunning();
    }
}
