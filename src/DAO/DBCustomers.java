package DAO;

import Database.DBConnection;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class DBCustomers {
    private static ObservableList<Customers> customerList = FXCollections.observableArrayList();

    public static void setCustomerList(){
        try{
            String sql = "SELECT * FROM customers;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //rs.next();
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
                Customers c = new Customers(id, name, address, post, phone, createDate, creator, update,
                        updater, division);
                customerList.add(c);

            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addCustomer(Customers c){
        customerList.add(c);
    }

    public static ObservableList<Customers> getCustomerList(){
        return customerList;
    }

}
