package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * creates a customer class
 */
public class Customers {

    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private int division;

    public Customers(int customerID, String name, String address, String postalCode, String phone, int division){
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
    }

    /**
     * getters
     */
    public int getCustomerID(){
       return customerID;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
       return address;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public String getPhone(){
       return phone;
    }

    public int getDivision(){
        return division;
    }

    public static ObservableList<String> customerNames(ObservableList<Customers> customers){
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        customers.forEach(c ->{
            String nameCustomer = c.getName();
            customerNames.add(nameCustomer);

        });
                return customerNames;
    }

    /**
     * setters
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setDivision(int division){
        this.division = division;
    }

    /**
     * gets customer id from customer name
     * @param customerName customer name
     * @return customer id
     */
    public static int findCustomerID(String customerName){
        for(int i = 0; i < DAO.DBCustomers.getCustomerList().size(); i ++){
            if(customerName.equals(DAO.DBCustomers.getCustomerList().get(i).getName())){
                return DAO.DBCustomers.getCustomerList().get(i).getCustomerID();
            }
        }
        return -1;
    }

}
