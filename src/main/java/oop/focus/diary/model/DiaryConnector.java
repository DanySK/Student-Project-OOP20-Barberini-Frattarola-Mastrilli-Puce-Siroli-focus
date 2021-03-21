package oop.focus.diary.model;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import oop.focus.db.Connector;
import oop.focus.db.exceptions.ConnectionException;

import static java.nio.file.Files.createFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DiaryConnector implements Connector<BufferedReader> {
    private static final String SOURCE_PATH = ".focus";
    private static final String FOLDER_NAME = "DiarysPages";
    private static final String SEP = File.separator;
    private boolean connected;
    private final Path dirPath = Paths.get(System.getProperty("user.home") + SEP + SOURCE_PATH + SEP + FOLDER_NAME);
    private Path filePath;
    private BufferedReader br;

    public DiaryConnector(final Path filePath) {
        this.filePath = filePath;
        try {
            this.create();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void create() throws ConnectionException {
        if (!dirPath.toFile().exists()) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!filePath.toFile().exists()) {
            try {
                filePath = createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public final BufferedReader getConnection() throws IllegalStateException {
        return this.br;
    }
    @Override
    public final void open() throws ConnectionException, IllegalStateException {
        if (this.connected) {
            throw new IllegalStateException();
        }
        try {
            br = new BufferedReader(new FileReader(filePath.toFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.connected = true;
    }

    @Override
    public final void close() throws ConnectionException, IllegalStateException {
        if (!this.connected) {
            throw new IllegalStateException();
        }
        try {
            this.getConnection().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connected = false;
    }

}
