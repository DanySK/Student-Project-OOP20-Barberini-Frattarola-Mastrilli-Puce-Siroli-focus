package oop.focus.db;

import oop.focus.db.exceptions.ConnectionException;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

/**
 * A specific connector to a h2 db.
 */
public class H2Connector implements Connector<Connection> {
    private static final String DB_NAME = "db";
    private static final String SOURCE_PATH = ".focus/";
    private static final String DB_URL = "jdbc:h2:~/" + SOURCE_PATH + DB_NAME + ";DB_CLOSE_ON_EXIT=TRUE";
    private static final String CREATE_SCRIPT_PATH = "scripts/create_script.sql";
    private static final String INSERT_SCRIPT_PATH = "scripts/populate_script.sql";
    private static final String CREATE_SCHEMA = "INIT=create schema if not exists " + DB_NAME;
    private static final String CREATE_SCRIPT = "runscript from ";
    private static final String DRIVER = "org.h2.Driver";
    private static final String USER = "oop";
    private static final String PASS = "oop2021";
    private static final String SEPARATOR = "\\";

    private Connection connection;
    private boolean connected;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void create() throws ConnectionException {
        final File db = new File(System.getProperty("user.home") + "//" + SOURCE_PATH);
        if (!db.exists() && !db.mkdirs()) {
            return;
        }
        if (db.listFiles() == null || Arrays.stream(Objects.requireNonNull(db.listFiles()))
                .noneMatch(f -> f.getName().contains(DB_NAME))) {
            try {
                Class.forName(DRIVER);
                this.connect(DB_URL + ";"
                        + CREATE_SCHEMA + SEPARATOR + ";"
                        + CREATE_SCRIPT + "'" + this.getPath(CREATE_SCRIPT_PATH) + "'" + SEPARATOR + ";"
                        + CREATE_SCRIPT + "'" + this.getPath(INSERT_SCRIPT_PATH) + "'");
            } catch (final SQLException | URISyntaxException | ClassNotFoundException e) {
                throw new ConnectionException();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Connection getConnection() {
        return this.connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void open() throws ConnectionException {
        if (this.connected) {
            throw new IllegalStateException();
        }
        try {
            this.connect(DB_URL);
            this.connected = true;
        } catch (final SQLException throwables) {
            throw new ConnectionException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void close() throws ConnectionException {
        if (!this.connected) {
            throw new IllegalStateException();
        }
        try {
            this.connection.close();
            this.connected = false;
        } catch (final SQLException throwables) {
            throw new ConnectionException();
        }
    }

    /**
     * Try to connect to the database.
     *
     * @param url the url on which to connect.
     * @throws SQLException if is not possible to connect.
     */
    private void connect(final String url) throws SQLException {
        this.connection = DriverManager.getConnection(url, USER, PASS);
    }

    /**
     * Get the correct absolute path for a relative path file.
     *
     * @param name the file path
     * @return the correct path as {@link String}
     * @throws URISyntaxException if the file cannot be found.
     */
    private String getPath(final String name) throws URISyntaxException {
        return new File(Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResource(name)).toURI()).getPath().replace(SEPARATOR, SEPARATOR + "\\");
    }
}
