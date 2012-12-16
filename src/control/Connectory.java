
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connectory {

    String driverClassName = "org.hsqldb.jdbcDriver";
    String connectionUrl = "jdbc:hsqldb:file:${user.home}i377/Team01d/piirivalveDb;shutdown=true";
//	String dbUser = "root";
//	String dbPwd = "root";
    private static Connectory Connectory = null;

    private Connectory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl); //cutted dbUser, dbPwd
        return conn;
    }

    public static Connectory getInstance() {
        if (Connectory == null) {
            Connectory = new Connectory();
        }
        return Connectory;
    }
}
