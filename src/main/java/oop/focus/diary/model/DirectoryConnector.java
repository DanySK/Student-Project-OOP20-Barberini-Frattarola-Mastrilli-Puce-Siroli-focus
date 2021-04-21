package oop.focus.diary.model;

import oop.focus.db.Connector;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryConnector implements Connector<File> {
    private static final String SOURCE_PATH = ".focus";
    private static final String FOLDER_NAME = "DiaryPages";
    private static final String SEP = File.separator;
    private final Path dirPath = Paths.get(System.getProperty("user.home") + SEP + SOURCE_PATH + SEP + FOLDER_NAME);

    /**
     * {@inheritDoc}
     */
    @Override
    public void create()  {
        if (!this.dirPath.toFile().exists()) {
            try {
                Files.createDirectories(this.dirPath);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getConnection() {
        return this.dirPath.toFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open() {

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {

    }


}
