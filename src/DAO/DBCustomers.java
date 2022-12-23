package DAO;

import Database.DBConnection;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * access to the database to get customers.
 */
public class DBCustomers {
    /**
     * creates a customer list
     */
    private static ObservableList<Customers> customerList = FXCollections.observableArrayList();

    /**
     * populates the customer list
     */
    public static void setCustomerList(){
        try{
            //accesses the database
            String sql = "SELECT * FROM customers;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //while there is another customer, takes in customer information and adds it to the customerList
            while(rs.next()){
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String post = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String creator = rs.getString("Created_By");
                LocalDateTime update = rs.getTimestamp("Last_Update").toLocalDateTime();
                String updater = rs.getString("Last_Updated_By");
                int division = rs.getInt("Division_ID");
                //creates customer
                Customers c = new Customers(id, name, address, post, phone, createDate, creator, update,
                        updater, division);
                //adds customer
                customerList.add(c);

            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * adds a customer
     * @param c customer parameter
     */
    public static void addCustomer(Customers c){
        customerList.add(c);
    }

    /**
     * returns the customer list
     * @return returns observable list of customers.
     */
    public static ObservableList<Customers> getCustomerList(){
        return customerList;
    }

}
