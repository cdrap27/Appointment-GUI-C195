package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * gets database connection
 */
public class DBConnection {

    /**
     * for database connection
     */
    private static final String protocol = "jdbc";
    /**
     * for database connection
     */
    private static final String vendor = ":mysql:";
    /**
     * for database connection
     */
    private static final String location = "//localhost/";
    /**
     * for database connection
     */
    private static final String databaseName = "client_schedule";
    /**
     * for database connection
     */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    /**
     * for database connection
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    /**
     * for database connection
     */
    private static final String userName = "sqlUser"; // Username
    /**
     * for database connection
     */
    private static String password = "Passw0rd!"; // Password



    /**
     * for database connection
     */
    private static final String ipAddress = "wgudb.ucertify.com:3306";


    /**
     * for database connection
     */
    private static final String jdbcURL = protocol + vendor + ipAddress + databaseName;

    /**
     * for database connection
     */
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";

    /**
     * for database connection
     */
    private static Connection conn = null;

    /**
     * for database connection
     */
    public static Connection startConnection(){
        try{

            Class.forName(driver); // Locate Driver
            //password = Details.getPassword(); // Assign password
            conn = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("Connection successful!");



            }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * for database connection
     */
    public static void closeConnection(){
        try{
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * for database connection
     */
    public static Connection getConnection(){

        return conn;
    }
}
