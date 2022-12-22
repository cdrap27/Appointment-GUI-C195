package Controllers;

import Model.Customers;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;

public class Dashboard {

    public TableView customersTable;
    public TableView appointmentsTable;
    @FXML
    private void initialize(){
        Customers c = new Customers(1,"Jack", "dkd", "23322", "757",
                LocalDateTime.of(2022, 12, 12, 12, 58),
                "henry", LocalDateTime.of(2022, 12, 12, 12, 58),
                "jack", 2340);
        DAO.DBCustomers.addCustomer(c);
        System.out.println("" + DAO.DBCustomers.getCustomerList().get(0).getCreateDate() );
    }
}
