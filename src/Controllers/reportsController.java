package Controllers;

import DAO.DBAppointment;
import DAO.DBContact;
import DAO.DBCountries;
import Model.Appointment;
import Model.Contacts;
import Model.Countries;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class reportsController {
    public ToggleGroup customerSelecter;
    public RadioButton type;
    public RadioButton month;
    public RadioButton all;
    public TableView customerReport;
    public TableColumn appointmentsID;
    public TableColumn title;
    public TableColumn description;
    public TableColumn appointmentLocation;
    public TableColumn contact;
    public TableColumn appointmentType;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customer;
    public TableColumn user;

    public ChoiceBox customerTableChoice;

    public Text numberOfAppointments;

    //table 2
    public TableView contactSchedule;
    public TableColumn appointmentsID2;
    public TableColumn title2;
    public TableColumn description2;
    public TableColumn appointmentLocation2;
    public TableColumn appointmentType2;
    public TableColumn start2;
    public TableColumn end2;
    public TableColumn customer2;
    public TableColumn user2;
    public ChoiceBox contactSelect;
    public Text contactsAppointments;

    //customer select table
    public Text numberOfCustomers;
    public TableView customerByCountry;
    public TableColumn customerID;
    public TableColumn name;
    public TableColumn address;
    public TableColumn post;
    public TableColumn phone;
    public TableColumn division;
    public TableColumn country;
    public TableColumn divisionName;
    public ChoiceBox customerSelect;
    public Button exit;
    public Button dashboard;


    public void initialize(){
        //populate table1
        customerReport.setItems(DAO.DBAppointment.getAppointmentList());
        appointmentsID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        numberOfAppointments.setText(String.valueOf(DBAppointment.getAppointmentList().size()));

        //table 2
        contactSelect.setItems(Contacts.contactNames(DBContact.getContactList()));

        //customer select table
        customerSelect.setItems(Countries.countryNames(DBCountries.getCountryList()));
    }

    public void onType(ActionEvent actionEvent) {
        customerReport.setItems(null);
        customerTableChoice.setItems(Appointment.getTypes(DAO.DBAppointment.getAppointmentList()));
        numberOfAppointments.setText(null);

    }

    public void onMonth(ActionEvent actionEvent) {
        customerReport.setItems(null);
        customerTableChoice.setItems(months());
        numberOfAppointments.setText(null);
    }

    public void onAll(ActionEvent actionEvent) {
        customerTableChoice.setItems(null);
        customerReport.setItems(DBAppointment.getAppointmentList());
        numberOfAppointments.setText(String.valueOf(DBAppointment.getAppointmentList().size()));
    }

    public void onCustomerTableChoice(ActionEvent actionEvent) {
        if(type.isSelected()){
            customerReport.setItems(Appointment.getTypeAppointments((String)customerTableChoice.getValue()));
            numberOfAppointments.setText(String.valueOf(Appointment.getTypeAppointments((String)customerTableChoice.getValue()).size()));
        }
        if(month.isSelected()){
           customerReport.setItems(Appointment.getMonths(customerTableChoice.getSelectionModel().getSelectedIndex()+1));
           numberOfAppointments.setText(String.valueOf(Appointment.getMonths(customerTableChoice.getSelectionModel().getSelectedIndex()+1).size()));
        }
    }

    public ObservableList<String> months(){
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        return months;
    }

    public void onContactSelect(ActionEvent actionEvent) {
       contactSchedule.setItems(Appointment.getContactSchedule(Contacts.findContactID((String)contactSelect.getSelectionModel().getSelectedItem())));
        appointmentsID2.setCellValueFactory(new PropertyValueFactory<>("ID"));
        title2.setCellValueFactory(new PropertyValueFactory<>("title"));
        description2.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocation2.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentType2.setCellValueFactory(new PropertyValueFactory<>("type"));
        start2.setCellValueFactory(new PropertyValueFactory<>("start"));
        end2.setCellValueFactory(new PropertyValueFactory<>("end"));
        customer2.setCellValueFactory(new PropertyValueFactory<>("customer"));
        user2.setCellValueFactory(new PropertyValueFactory<>("user"));

        contactsAppointments.setText(String.valueOf(Appointment.getContactSchedule(Contacts.findContactID((String)contactSelect.getSelectionModel().getSelectedItem())).size()));
    }

    public void onCustomerSelect(ActionEvent actionEvent) {
        customerByCountry.setItems(Customers.customersByCountry((String)customerSelect.getSelectionModel().getSelectedItem()));

        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        post.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        division.setCellValueFactory(new PropertyValueFactory<>("division"));
        divisionName.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));

        numberOfCustomers.setText(String.valueOf(Customers.customersByCountry((String)customerSelect.getSelectionModel().getSelectedItem()).size()));
    }

    public void onDashboard(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dashboard?");
        alert.setContentText("Are you sure you want return to the dashboard?");
        ButtonType CANCEL = new ButtonType("Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/dashboard.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1055, 699);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.getScene().getWindow().centerOnScreen();
            stage.show();
        }
    }

    public void onExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit?");
        alert.setContentText("Are you sure you want to exit?");
        ButtonType CANCEL = new ButtonType("Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
    }
}
}
