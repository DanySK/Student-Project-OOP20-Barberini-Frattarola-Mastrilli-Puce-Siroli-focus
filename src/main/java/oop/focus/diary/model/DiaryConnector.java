package oop.focus.diary.model;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import oop.focus.db.Connector;
import oop.focus.db.exceptions.ConnectionException;

public class DiaryConnector implements Connector<File> {
    private static final String SOURCE_PATH = ".focus";
    private static final String FOLDER_NAME = "DiarysPages";
    private static final String SEP = File.separator;
    private boolean connected;
    private Path filePath;


    @Override
    public final void create() throws ConnectionException {
        filePath = Paths.get(System.getProperty("user.home") + SEP + SOURCE_PATH + SEP + FOLDER_NAME);
        if (!filePath.toFile().exists()) {
            try {
                this.filePath = Files.createDirectories(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final File getConnection() throws IllegalStateException {
        return this.filePath.toFile();
    }

    @Override
    public final void open() throws ConnectionException, IllegalStateException {
        if (this.connected) {
            throw new IllegalStateException();
        }
        this.connected = true;
    }

    @Override
    public final void close() throws ConnectionException, IllegalStateException {
        if (!this.connected) {
            throw new IllegalStateException();
        }
        this.connected = false;
    }

}
