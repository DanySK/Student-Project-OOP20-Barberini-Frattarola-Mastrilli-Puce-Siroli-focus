package oop.focus.db;

import oop.focus.db.exceptions.ConnectionException;

/**
 * The interface Connector defines a object that manages the connection with a source.
 * It defines the methods to open, close or get the connection instance.
 *
 * @param <X> the type of the connection
 */
public interface Connector<X> {
    /**
     * Create the connection with the source. If the source does not exist, it will be created.
     *
     * @throws ConnectionException if is not possible to create the connection.
     */
    void create() throws ConnectionException;

    /**
     * Gets the connection instance.
     *
     * @return the instance of the connection of type X
     * @throws IllegalStateException if the connection has not been created.
     */
    X getConnection();

    /**
     * Open the connection with the source.
     *
     * @throws ConnectionException   if the connection cannot be opened.
     * @throws IllegalStateException if the connection has not been created or is already open.
     */
    void open() throws ConnectionException;

    /**
     * Close the connection with the source.
     *
     * @throws ConnectionException   the connection exception
     * @throws IllegalStateException if the connection has not been created or is already closed.
     */
    void close() throws ConnectionException;
}
