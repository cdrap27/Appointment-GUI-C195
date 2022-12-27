package Controllers;

import Model.Appointment;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class Dashboard {
    //customer table
    public TableView customersTable;

    public TableColumn customerID;
    public TableColumn name;
    public TableColumn address;
    public TableColumn post;
    public TableColumn phone;
    public TableColumn creationDate;
    public TableColumn creator;
    public TableColumn update;
    public TableColumn updater;
    public TableColumn division;

    //appointment table
    public TableView appointmentsTable;

    public TableColumn appointmentsID;
    public TableColumn title;
    public TableColumn description;
    public TableColumn appointmentLocation;
    public TableColumn appointmentType;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customer;
    public TableColumn user;
    public TableColumn contact;

   // public ObservableList<Appointment> weekAppointment;

    @FXML
    private void initialize(){

        //populate the customers table
        customersTable.setItems(DAO.DBCustomers.getCustomerList());
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        post.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        creationDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
        update.setCellValueFactory(new PropertyValueFactory<>("changeDate"));
        updater.setCellValueFactory(new PropertyValueFactory<>("changer"));
        division.setCellValueFactory(new PropertyValueFactory<>("division"));

        //populate appointments table
        appointmentsTable.setItems(DAO.DBAppointment.getAppointmentList());
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

    }

    /**
     * sets the appointments to this week
     * @param actionEvent on clicking this week radio button
     */
    public void onThisWeek(ActionEvent actionEvent) {

      appointmentsTable.setItems(DAO.DBAppointment.thisWeek());

    }

    /**
     * sets the appointments to this month
     * @param actionEvent on clicking this month radio button
     */
    public void onThisMonth(ActionEvent actionEvent) {
        appointmentsTable.setItems(DAO.DBAppointment.thisMonth());
    }

    /**
     * sets appointments to all
     * @param actionEvent on clicking all radio button
     */
    public void onAll(ActionEvent actionEvent) {
        appointmentsTable.setItems(DAO.DBAppointment.getAppointmentList());
    }
}
