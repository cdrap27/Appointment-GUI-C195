package Model;

import DAO.DBCountries;
import DAO.DBCustomers;
import DAO.DBDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * creates a customer class
 */
public class Customers {

    /**
     * reference for the customers class
     */
    private int customerID;
    /**
     * reference for the customers class
     */
    private String name;
    /**
     * reference for the customers class
     */
    private String address;
    /**
     * reference for the customers class
     */
    private String postalCode;
    /**
     * reference for the customers class
     */
    private String phone;
    /**
     * reference for the customers class
     */
    private int division;
    /**
     * reference for the customers class
     */
    private String divisionName;
    /**
     * reference for the customers class
     */
    private String country;
    /**
     * customer country name
     */
    public static String customerCountryName;

    /**
     * creates the customer class
     * @param customerID customer id
     * @param name name
     * @param address address
     * @param postalCode postal code
     * @param phone phone
     * @param division division
     * @param divisionName division name
     * @param country country
     */
    public Customers(int customerID, String name, String address, String postalCode, String phone, int division, String divisionName, String country){
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.divisionName = divisionName;
        this.country = country;
    }

    /**
     * getter
     */
    public int getCustomerID(){
       return customerID;
    }

    /**
     * getter
     */
    public String getName(){
        return name;
    }

    /**
     * getters
     */
    public String getAddress(){
       return address;
    }

    /**
     * getters
     */
    public String getPostalCode(){
        return postalCode;
    }

    /**
     * getters
     */
    public String getPhone(){
       return phone;
    }

    /**
     * getters
     */
    public int getDivision(){
        return division;
    }

    /**
     * getters
     */
    public String getDivisionName(){ return divisionName; }

    /**
     * getters
     */
    public String getCountry(){ return country; }

    /**
     * Lambda Expression 7: cycles through the list of customers and adds the name to a list of customer names.
     * @param customers customers
     * @return customer names
     */
    public static ObservableList<String> customerNames(ObservableList<Customers> customers){
        ObservableList<String> customerNames = FXCollections.observableArrayList();
        //lambda expression to cycle through the customer list and return customer names
        customers.forEach(c ->{
            String nameCustomer = c.getName();
            customerNames.add(nameCustomer);

        });
                return customerNames;
    }

    /**
     * setter
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /**
     * setter
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * setter
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * setter
     */
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /**
     * setter
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * setter
     */
    public void setDivision(int division){
        this.division = division;
    }

    /**
     * setter
     */
    public void setDivisionName(String divisionName) { this.divisionName = divisionName; }

    /**
     * setter
     */
    public void setCountry(String countr) { this.country = country; }

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

    /**
     * finds the position where the customer is located
     * @param customer customer
     * @return customer position
     */
    public static int getID(int customer){
        for(int i = 0; i < DAO.DBCustomers.getCustomerList().size(); i++){
            if(customer == DBCustomers.getCustomerList().get(i).getCustomerID()){
                return i;
            }
        }
        return -1;
    }

    /**
     * find the division name based on the division id
     * @param division division id
     * @return division name
     */
    public static String getDivision(int division){
        for(int i = 0; i < DBDivision.getDivisionList().size(); i++){
            if(division == DBDivision.getDivisionList().get(i).getDivisionID()){
                customerCountryName = getCountry(DBDivision.getDivisionList().get(i).getCountryID());
                return DBDivision.getDivisionList().get(i).getDivision();
            }
        }
        return null;
    }

    public static String getCountry(int countryID){
        for(int i = 0; i < DBCountries.getCountryList().size(); i++){
            if(countryID == DBCountries.getCountryList().get(i).getCountryID()){
                return DBCountries.getCountryList().get(i).getCountryName();
            }
        }
        return null;
    }

    /**
     * checks name
      * @param nameField name
     * @return name
     */
    public static Boolean checkName(TextField nameField){
        String name = nameField.getText();
        Boolean check = true;
        if(name.length()<1){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Name Error");
            errorAlert.setContentText("Name Error");
            errorAlert.showAndWait();
        }
        return check;
    }

    /**
     * checks address
     * @param nameField address
     * @return address
     */
    public static Boolean checkAddress(TextField nameField){
        String name = nameField.getText();
        Boolean check = true;
        if(name.length()<1){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Address Error");
            errorAlert.setContentText("Address Error");
            errorAlert.showAndWait();
        }
        return check;
    }

    /**
     * checks postal code
     * @param nameField postal code
     * @return postal code
     */
    public static Boolean checkPost(TextField nameField){
        String name = nameField.getText();
        Boolean check = true;
        if(name.length()<1){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Postal Code Error");
            errorAlert.setContentText("Postal Code Error");
            errorAlert.showAndWait();
        }
        return check;
    }

    /**
     * checks phone
     * @param nameField phone
     * @return phone
     */
    public static Boolean checkPhone(TextField nameField){
        String name = nameField.getText();
        Boolean check = true;
        if(name.length()<1){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Phone Error");
            errorAlert.setContentText("Phone Error");
            errorAlert.showAndWait();
        }
        return check;
    }

    /**
     * chekcs country
     * @param country country
     * @return country
     */
    public static Boolean checkCountry(ChoiceBox country){

        Boolean check = true;
        if(country.getValue() == null){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Country Error");
            errorAlert.setContentText("Country Error");
            errorAlert.showAndWait();
        }
        return check;
    }

    /**
     * checks division
     * @param country division
     * @return division
     */
    public static Boolean checkDivision(ChoiceBox country){

        Boolean check = true;
        if(country.getValue() == null){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Division Error");
            errorAlert.setContentText("Division Error");
            errorAlert.showAndWait();
        }
        return check;
    }

    /**
     * Lambda Expression 8: Cycels through the list of customers to find matching countries and adds them to the
     * customersByCountry list
     * @param country country
     * @return customers by country
     */
    public static ObservableList<Customers> customersByCountry(String country){
        ObservableList<Customers> customersByCountry = FXCollections.observableArrayList();
        DAO.DBCustomers.getCustomerList().forEach(c ->{
            //System.out.println("" + c.getCountry() + " input is " + country);
            if(c.getCountry().equals(country)){
                customersByCountry.add(c);
            }
        });
        return customersByCountry;
    }
}
