package core;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class that establishes a connection with the MySQL database using JDBC.
 *
 * @author szabolcsdnagy
 * @version 27.03.2021
 */
public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnectionLink() {
        String databaseName = "accountsystem";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
