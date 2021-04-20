package oop.focus.diary.model;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import oop.focus.db.Connector;
import java.io.File;
import java.io.IOException;

/**
 * A specific connector to a {@link FileManager}. The methods allow the creation, opening and closing
 * of a file specified in input.
 */
public class DiaryConnector implements Connector<FileManager> {
    private boolean connected;
    private final FileManager fm;
    private final File file;

    public DiaryConnector(final File file) {
        this.file = file;
        this.fm = new FileManagerImpl(this.file);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void create() {
        if (!this.file.exists()) {
            try {
                Files.createFile(this.file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final FileManager getConnection() {
        return this.fm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void open() {

        if (this.connected) {
            throw new IllegalStateException();
        }
        try {
            this.fm.openBufferedWriter(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.fm.openBufferedReader(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.connected = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void close() {
        if (!this.connected) {
            throw new IllegalStateException();
        }
        try {
            this.fm.getBufferedWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.fm.getBufferedReader().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connected = false;
    }
}
