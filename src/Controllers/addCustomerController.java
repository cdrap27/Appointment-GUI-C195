package Controllers;

import DAO.DBCountries;
import DAO.DBCustomers;
import DAO.DBDivision;
import Model.Countries;
import Model.Division;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class addCustomerController {
    public TextField addCustomerID;
    public TextField addCustomerName;
    public TextField addCustomerAddress;
    public TextField addCustomerPost;
    public TextField addCustomerPhone;
    public Button save;
    public Button cancel;
    public ChoiceBox addCustomerCountry;
    public ChoiceBox addCustomerDivision;
    public TextField addCustomerDivisionID;

    public void initialize(){
        addCustomerID.setText(String.valueOf(DBCustomers.customerSize +1));
        addCustomerCountry.setItems(Countries.countryNames(DBCountries.getCountryList()));
    }

    public void onSave(ActionEvent actionEvent) {
    }

    public void onCancel(ActionEvent actionEvent) {
    }

    public void countrySelected(ActionEvent event) {
        addCustomerDivisionID.clear();
        addCustomerDivision.setItems(Division.divisionNames((String)addCustomerCountry.getSelectionModel().getSelectedItem()));
        System.out.println(addCustomerDivision.getValue());
    }

    public void divisionSelected(ActionEvent actionEvent) {
        addCustomerDivisionID.clear();
        if(addCustomerDivision.getValue() != null)
        addCustomerDivisionID.setText(String.valueOf(Division.getDivisionID((String)addCustomerDivision.getSelectionModel().getSelectedItem())));
    }
}
