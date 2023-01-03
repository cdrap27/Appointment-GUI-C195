package DAO;

import Database.DBConnection;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBUsers {
    //a list of users
    private static ObservableList<Users> userList = FXCollections.observableArrayList();


    /**
     * adds in test data then sets all users into an observable list
     */
    public static void setUserList() {
        //trys to get all appointments into observable list
        try {
            //accesses the database
            String sql = "SELECT * FROM users;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //while there is another user, takes in user information and adds it to the userlist
            while (rs.next()) {
                int id = rs.getInt("User_ID");
                String name = rs.getString("User_Name");
                String password = rs.getString("Password");
                //create user
                Users c = new Users(id, name, password);
                //adds user
                userList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("" + userList.size() + " users added.");

    }

    public static ObservableList<Users> getUserList() {
        return userList;
    }

}