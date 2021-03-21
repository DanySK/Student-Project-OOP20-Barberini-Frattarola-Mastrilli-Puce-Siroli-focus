package oop.focus.diary.model;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundImpl implements Sound {
    private static final String SEP = File.separator;
    private final Path soundDir = Path.of(new File(".").getCanonicalPath() + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "sounds");
    private File alarmPath;
    private final Clip clip;
    private boolean isPlaying;

    public SoundImpl() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.isPlaying = false;
        this.setAlarmPath();
        //final DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        //clip = (Clip) AudioSystem.getLine(info);
        this.clip = AudioSystem.getClip();
        this.clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(alarmPath))));
    }
    private void setAlarmPath() {
        alarmPath = Arrays.stream(soundDir.toFile().listFiles()).iterator().next(); 
    }
    @Override
    public final void playSound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (!this.isPlaying) {
            this.isPlaying = true;
            this.clip.start();
        }
    }
    @Override
    public final void stopSound() {
        if (this.isPlaying) {
            this.isPlaying = false;
            this.clip.stop();
        }
    }
    @Override
    public final boolean isPlaying() {
        return this.isPlaying;
    }
}
