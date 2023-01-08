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
    //reports table 1

    /**
     * reference for the gui
     */
    public ToggleGroup customerSelecter;
    /**
     * reference for the gui
     */
    public RadioButton type;
    /**
     * reference for the gui
     */
    public RadioButton month;
    /**
     * reference for the gui
     */
    public RadioButton all;

    /**
     * reference for the gui
     */
    public TableView customerReport;
    /**
     * reference for the gui
     */
    public TableColumn appointmentsID;
    /**
     * reference for the gui
     */
    public TableColumn title;
    /**
     * reference for the gui
     */
    public TableColumn description;
    /**
     * reference for the gui
     */
    public TableColumn appointmentLocation;
    /**
     * reference for the gui
     */
    public TableColumn contact;
    /**
     * reference for the gui
     */
    public TableColumn appointmentType;
    /**
     * reference for the gui
     */
    public TableColumn start;
    /**
     * reference for the gui
     */
    public TableColumn end;
    /**
     * reference for the gui
     */
    public TableColumn customer;
    /**
     * reference for the gui
     */
    public TableColumn user;

    /**
     * reference for the gui
     */
    public ChoiceBox customerTableChoice;

    /**
     * reference for the gui
     */
    public Text numberOfAppointments;

    //table 2

    /**
     * reference for the gui
     */
    public TableView contactSchedule;
    /**
     * reference for the gui
     */
    public TableColumn appointmentsID2;
    /**
     * reference for the gui
     */
    public TableColumn title2;
    /**
     * reference for the gui
     */
    public TableColumn description2;
    /**
     * reference for the gui
     */
    public TableColumn appointmentLocation2;
    /**
     * reference for the gui
     */
    public TableColumn appointmentType2;
    /**
     * reference for the gui
     */
    public TableColumn start2;
    /**
     * reference for the gui
     */
    public TableColumn end2;
    /**
     * reference for the gui
     */
    public TableColumn customer2;
    /**
     * reference for the gui
     */
    public TableColumn user2;
    /**
     * reference for the gui
     */
    public ChoiceBox contactSelect;
    /**
     * reference for the gui
     */
    public Text contactsAppointments;

    //customer select table

    /**
     * reference for the gui
     */
    public Text numberOfCustomers;
    /**
     * reference for the gui
     */
    public TableView customerByCountry;
    /**
     * reference for the gui
     */
    public TableColumn customerID;
    /**
     * reference for the gui
     */
    public TableColumn name;
    /**
     * reference for the gui
     */
    public TableColumn address;
    /**
     * reference for the gui
     */
    public TableColumn post;
    /**
     * reference for the gui
     */
    public TableColumn phone;
    /**
     * reference for the gui
     */
    public TableColumn division;
    /**
     * reference for the gui
     */
    public TableColumn country;
    /**
     * reference for the gui
     */
    public TableColumn divisionName;
    /**
     * reference for the gui
     */
    public ChoiceBox customerSelect;

    /**
     * reference for the gui
     */
    public Button exit;
    /**
     * reference for the gui
     */
    public Button dashboard;

    /**
     * initializes the page
     */
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

    /**
     * on selecting type, populates choice box
     * @param actionEvent type
     */
    public void onType(ActionEvent actionEvent) {
        customerReport.setItems(null);
        customerTableChoice.setItems(Appointment.getTypes(DAO.DBAppointment.getAppointmentList()));
        numberOfAppointments.setText(null);

    }

    /**
     * on selecting month, populates choice box
     * @param actionEvent month
     */
    public void onMonth(ActionEvent actionEvent) {
        customerReport.setItems(null);
        customerTableChoice.setItems(months());
        numberOfAppointments.setText(null);
    }

    /**
     * selects all
     * @param actionEvent on all
     */
    public void onAll(ActionEvent actionEvent) {
        customerTableChoice.setItems(null);
        customerReport.setItems(DBAppointment.getAppointmentList());
        numberOfAppointments.setText(String.valueOf(DBAppointment.getAppointmentList().size()));
    }

    /**
     * populates table one based one choicebox selection
     * @param actionEvent   choicebox selection
     */
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

    /**
     * observable list of months
     * @return months
     */
    public ObservableList<String> months(){
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        return months;
    }

    /**
     * on selecting contact, populates the second table
     * @param actionEvent contact select
     */
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

    /**
     * on selecting a country, returns customers from that country
     * @param actionEvent country select
     */
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

    /**
     * returns to the dashboard
     * @param actionEvent dashboard
     * @throws IOException ioexception
     */
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

    /**
     * exits the program
     * @param actionEvent exit
     */
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
