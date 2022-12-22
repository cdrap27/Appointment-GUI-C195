package DAO;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class DBCustomers {
    private static ObservableList<Customers> customerList = FXCollections.observableArrayList();

    public static void addCustomer(Customers c){
        customerList.add(c);
    }

    public static ObservableList<Customers> getCustomerList(){
        return customerList;
    }

}
