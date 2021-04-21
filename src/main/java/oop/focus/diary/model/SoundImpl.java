package oop.focus.diary.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Implementation of {@link Sound}. The class manages alarm's sound.
 */
public class SoundImpl implements Sound {
    private final Clip clip;

    /**
     * Instantiates a new Sound and sets the relative {@link Clip}.
     * @throws UnsupportedAudioFileException    if file doesn't contain valid data
     * @throws IOException  if an I/O operation has been interrupted.
     * @throws LineUnavailableException if a {@link javax.sound.sampled.Line} cannot be opened.
     */
    public SoundImpl() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.clip = AudioSystem.getClip();
        final Path tempFile = copyToTempFile(this.getClass().getResource("/sounds/alarm.wav"),
                ".wav");
        this.clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(tempFile.toFile()))));
    }

    public static Path copyToTempFile(final URL url, final String suffix) throws IOException {
        final Path tempFile = Files.createTempFile(null, suffix);
        try (InputStream in = url.openStream();
             OutputStream out = Files.newOutputStream(tempFile)) {
            in.transferTo(out);
        }
        return tempFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playSound()  {
        if (!this.clip.isRunning()) {
            this.clip.start();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stopSound() {
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
