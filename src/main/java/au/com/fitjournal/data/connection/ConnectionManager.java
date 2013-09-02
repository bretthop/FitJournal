package au.com.fitjournal.data.connection;

import org.postgresql.Driver;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager
{
    public static Connection getConnection()
    {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

            // TODO: For some reason, normally, the postgres driver is not loaded by tomcat and i get an SQL exception when i try
            // TODO: to create the connection (the return statement in this method). Using the driver object below forces the class to be loaded, so everything works fine.
            // TODO: This is obviously the worst way to do anything... so find out whats going on and fix it fix it fix it fix it
            Driver.getLogLevel();

            return DriverManager.getConnection(dbUrl, username, password);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }
}
