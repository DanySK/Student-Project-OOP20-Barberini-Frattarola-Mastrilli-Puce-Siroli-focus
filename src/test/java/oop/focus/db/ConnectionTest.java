package oop.focus.db;

import oop.focus.db.exceptions.ConnectionException;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.fail;

public class ConnectionTest {
    @Test
    public void testCreation() {
        Connector<Connection> conn = new H2Connector();
        try {
            conn.create();
        } catch (ConnectionException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testConnection() {
        Connector<Connection> conn = new H2Connector();
        try {
            conn.create();
            conn.open();
            conn.close();
        } catch (ConnectionException e) {
            e.printStackTrace();
            fail();
        }
    }
}
