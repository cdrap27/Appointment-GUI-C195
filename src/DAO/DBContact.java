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

public class DBContact {
    //a list of contacts
    private static ObservableList<Contacts> contactList = FXCollections.observableArrayList();


    /**
     * adds in test data then sets all appointments into an observable list
     */
    public static void setContactList() {
        //trys to get all appointments into observable list
        try {
            //accesses the database
            String sql = "SELECT * FROM contacts;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //while there is another appointment, takes in customer information and adds it to the customerList
            while (rs.next()) {
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                //create contact
               Contacts c = new Contacts(id, name, email);
                //adds division
                contactList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("" + contactList.size() + " contacts added.");

    }

    public static ObservableList<Contacts> getContactList(){
        return contactList;
    }

}