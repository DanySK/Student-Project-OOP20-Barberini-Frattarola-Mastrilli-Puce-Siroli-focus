package oop.focus.diary.model;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import oop.focus.db.Connector;
import oop.focus.db.exceptions.ConnectionException;

import static java.nio.file.Files.createFile;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class DiaryConnector implements Connector<FileManager> {
    private static final String SOURCE_PATH = ".focus";
    private static final String FOLDER_NAME = "DiarysPages";
    private static final String SEP = File.separator;
    private boolean connected;
    private final Path dirPath = Paths.get(System.getProperty("user.home") + SEP + SOURCE_PATH + SEP + FOLDER_NAME);
    private Path filePath;
    private final FileManager fm;
    private Optional<String> fileName;

    public DiaryConnector(final Optional<String> fileName) {
        this.fm = new FileManagerImpl(this.dirPath);
        if (!fileName.isEmpty()) {
            try {
                this.fileName = fileName;
                this.create();

            } catch (ConnectionException e) {
                e.printStackTrace();
            }
        }
    }
    private void setFilePath(final String fileName) {
        this.filePath = Path.of(this.dirPath.toString() + SEP + fileName);
    }
    @Override
    public final void create() throws ConnectionException {
        if (!this.dirPath.toFile().exists()) {
            try {
                Files.createDirectories(this.dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.setFilePath(this.fileName.get());
        if (!this.filePath.toFile().exists()) {
            try {
               createFile(this.filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public final FileManager getConnection() throws IllegalStateException {
        return this.fm;
    }
    @Override
    public final void open()  {
        if (this.connected) {
            throw new IllegalStateException();
        }
        try {
            if (!this.fileName.isEmpty()) {
                this.setFilePath(this.fileName.get());
                this.fm.openBufferedWriter(this.filePath.toFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connected = true;
    }

    @Override
    public final void close() {
        if (!this.connected) {
            throw new IllegalStateException();
        }
        try {
            this.fm.getBufferedReader().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connected = false;
    }

}
