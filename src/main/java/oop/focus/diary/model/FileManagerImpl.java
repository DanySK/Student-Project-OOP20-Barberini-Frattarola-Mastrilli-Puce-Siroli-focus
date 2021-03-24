package oop.focus.diary.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileManagerImpl implements FileManager {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final File file;

    public FileManagerImpl(final File file) {
        this.file = file;
    }
    @Override
    public final void openBufferedReader(final File file) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(file));
    }
    @Override
    public final BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }
    @Override
    public final void openBufferedWriter(final File file) throws IOException {
        this.bufferedWriter = new BufferedWriter(new FileWriter(file, true));

    }
    @Override
    public final BufferedWriter getBufferedWriter()  {
        return this.bufferedWriter;
    }
    @Override
    public final Path getFile() {
        return this.file.toPath();
    }
}
