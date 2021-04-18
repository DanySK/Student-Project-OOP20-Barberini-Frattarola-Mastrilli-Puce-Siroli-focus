package oop.focus.diary.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Implementation of {@link FileManager}. It manages a file, opening and closing
 * its buffers.
 */
public class FileManagerImpl implements FileManager {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final File file;

    /**
     * Instantiates a new file manager, relative to a file.
     * @param file  a file to manage
     */
    public FileManagerImpl(final File file) {
        this.file = file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void openBufferedReader(final File file) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(file));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void openBufferedWriter(final File file) throws IOException {
        this.bufferedWriter = new BufferedWriter(new FileWriter(file, true));

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final BufferedWriter getBufferedWriter()  {
        return this.bufferedWriter;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Path getFile() {
        return this.file.toPath();
    }
}
