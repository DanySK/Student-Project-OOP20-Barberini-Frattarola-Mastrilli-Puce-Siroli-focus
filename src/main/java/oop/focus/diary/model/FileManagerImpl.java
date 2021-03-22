package oop.focus.diary.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileManagerImpl implements FileManager {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final Path dirPath;
    public FileManagerImpl(final Path dirPath) {
        this.dirPath = dirPath;
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
        this.bufferedWriter = new BufferedWriter(new FileWriter(file));
    }
    @Override
    public final BufferedWriter getBufferedWriter()  {
        return this.bufferedWriter;
    }
    @Override
    public final List<File> getList() {
        return Arrays.asList(this.dirPath.toFile().listFiles());
    }
}
