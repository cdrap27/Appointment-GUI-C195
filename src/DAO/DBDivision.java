package DAO;

import Database.DBConnection;
import Model.Appointment;
import Model.Countries;
import Model.Customers;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DBDivision {
    //a list of divsions
    private static ObservableList<Division> divisionList = FXCollections.observableArrayList();


    /**
     * adds in test data then sets all appointments into an observable list
     */
    public static void setDivisionList() {
        //trys to get all appointments into observable list
        try {
            //accesses the database
            String sql = "SELECT * FROM first_level_divisions;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //while there is another appointment, takes in customer information and adds it to the customerList
            while (rs.next()) {
                int id = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                //create division
                Division d = new Division(id, division, countryID);
                //adds division
                divisionList.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("" + divisionList.size() + " divisions added.");

    }

    public static ObservableList<Division> getDivisionList(){
        return divisionList;
    }
}