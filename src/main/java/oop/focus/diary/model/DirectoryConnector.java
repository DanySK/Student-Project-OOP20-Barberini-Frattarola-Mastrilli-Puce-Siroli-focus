package oop.focus.diary.model;

import oop.focus.db.Connector;
import oop.focus.db.exceptions.ConnectionException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryConnector implements Connector<File> {
    private static final String SOURCE_PATH = ".focus";
    private static final String FOLDER_NAME = "DiarysPages";
    private static final String SEP = File.separator;
    private final Path dirPath = Paths.get(System.getProperty("user.home") + SEP + SOURCE_PATH + SEP + FOLDER_NAME);
    @Override
    public final void create()  {
        if (!this.dirPath.toFile().exists()) {
            try {
                Files.createDirectories(this.dirPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public final File getConnection() throws IllegalStateException {
        return this.dirPath.toFile();
    }

    @Override
    public void open() throws ConnectionException, IllegalStateException {

    }


    @Override
    public void close() throws ConnectionException, IllegalStateException {

    }


}
