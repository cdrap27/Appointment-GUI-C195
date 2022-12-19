package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password



    private static final String ipAddress = "wgudb.ucertify.com:3306";


    private static final String jdbcURL = protocol + vendor + ipAddress + databaseName;

    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";

    private static Connection conn = null;

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
    public static void closeConnection(){
        try{
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static Connection getConnection(){

        return conn;
    }
}
