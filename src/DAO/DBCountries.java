package DAO;

import Database.DBConnection;
import Model.Appointment;
import Model.Countries;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBCountries {
    //a list of countries
    private static ObservableList<Countries> countryList = FXCollections.observableArrayList();


    /**
     * adds in test data then sets all appointments into an observable list
     */
    public static void setCountryList() {
        //trys to get all appointments into observable list
        try {
            //accesses the database
            String sql = "SELECT * FROM countries;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //while there is another appointment, takes in customer information and adds it to the customerList
            while (rs.next()) {
                int id = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                //create country
                Countries c = new Countries(id, countryName);
                //adds country
                countryList.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("" + countryList.size() + " countries added.");

    }
}