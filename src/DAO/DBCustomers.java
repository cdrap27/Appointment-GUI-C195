package DAO;

import Database.DBConnection;
import Model.Appointment;
import Model.Customers;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * access to the database to get customers.
 */
public class DBCustomers {
    //checks if a customer already exists
    private static Boolean duplicateCustomer;
    //keeps track of the size of the customer list
    public static int customerSize;
    /**
     * creates a customer list
     */
    private static ObservableList<Customers> customerList = FXCollections.observableArrayList();

    /**
     * populates the customer list
     */
    public static void setCustomerList(){
        try{
            addTestData();
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
                int division = rs.getInt("Division_ID");
                String divisionName = Customers.getDivision(division);
                String country = "hank";
                //creates customer
                Customers c = new Customers(id, name, address, post, phone,  division, divisionName, country);
                //adds customer
                customerList.add(c);

            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("" + customerList.size() + " customers added.");
        //sets the size of the list of customers
        customerSize = customerList.size();
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

    /**
     * adds a customer to the database
     * @param c takes in a customer element to add to the database
     */
    public static void addCustomerSQL(Customers c){
        duplicateCustomer = false;
        try{
            //accesses the database
            String sql = "SELECT * FROM customers;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            /*
            while there is another customer, takes in customer information and checks to see if it matches an
            customer currently in the database
            */
            while(rs.next()){
                int i = rs.getInt("Customer_ID");
                if(c.getCustomerID() == i)
                    duplicateCustomer = true;

            }
            //if there is no match, adds the appointment to the database
            if (duplicateCustomer == false){
                //accesses the database
                sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?, ?)";
                ps = DBConnection.getConnection().prepareStatement(sql);
                ps.setInt(1, c.getCustomerID());
                ps.setString(2, c.getName());
                ps.setString(3, c.getAddress());
                ps.setString(4, c.getPostalCode());
                ps.setString(5, c.getPhone());
                ps.setInt(6, c.getDivision());
                ps.execute();
               // System.out.println("Added a customer");

            }
            /*
            else if(duplicateCustomer == true){
                System.out.println("duplicate customer");
            }*/

        }
        catch(SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * deletes the selected customer from the database
     * @param i takes in an integer to reference the customer
     */
    public static void deleteCustomerSQL(int i){
        try{
            //accesses the database
            String sql = "SET FOREIGN_KEY_CHECKS=0;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
            sql = "DELETE FROM customers WHERE Customer_ID =  "+ i + ";";
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
            //calls a function to delete associatedappointments from DAOAppointments
            DAO.DBAppointment.deleteAssociatedAppointment(i);
            System.out.println("deleted");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the test data
     */
    private static void addTestData(){
        Customers c = new Customers(1, "Daddy Warbucks", "1919 Boardwalk", "01291", "866-908-1875", 29, Customers.getDivision(29), "hank");
        addCustomerSQL(c);
        c = new Customers(2, "Lady McAnderson", "526 Madeup Street", "AF19B", "186-389-38392", 103, Customers.getDivision(103), "hank");
        addCustomerSQL(c);
        c = new Customers(3, "Dudley Do-Right", "2016 Money Lane", "28198", "286-286-2866", 60, Customers.getDivision(60), "hank");
        addCustomerSQL(c);


    }

}
