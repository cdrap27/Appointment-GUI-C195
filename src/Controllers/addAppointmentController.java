package Controllers;

import DAO.DBAppointment;
import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.TimeZone;

/**
 * controller for add appointment screen
 */
public class addAppointmentController {
    /**
     * for use on the gui
     */
    public DatePicker addStartDate;
    /**
     * for use on the gui
     */
    public DatePicker addEndDate;
    /**
     * for use on the gui
     */
    public ChoiceBox addStartTime;
    /**
     * for use on the gui
     */
    public ChoiceBox addEndTime;
    /**
     * for use on the gui
     */
    public ChoiceBox addContactID;
    /**
     * for use on the gui
     */
    public ChoiceBox addCustomerID;
    /**
     * for use on the gui
     */
    public ChoiceBox addUserID;
    /**
     * for use on the gui
     */
    public TextField addAppointmentID;
    /**
     * for use on the gui
     */
    public TextField addTitle;
    /**
     * for use on the gui
     */
    public TextField addDescription;
    /**
     * for use on the gui
     */
    public TextField addLocation;
    /**
     * for use on the gui
     */
    public TextField addType;
    /**
     * appointment for reference
     */
    public Appointment addAnAppointment;
    /**
     * for use on the gui
     */
    public Text estStart;
    /**
     * for use on the gui
     */
    public Text estEnd;


    /**
     * initializes the add appointment screen
     *
     */
    public void initialize(){
        Dashboard.modifyApp = new Appointment(DBAppointment.appointmentSize+1, "blank", "blank", "blank", "blank",
                LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1);
        addAppointmentID.setText(Integer.toString(DBAppointment.appointmentSize+1));
        addStartTime.setItems(Model.Appointment.time());
        addEndTime.setItems(Model.Appointment.time());
        addCustomerID.setItems(Model.Customers.customerNames(DAO.DBCustomers.getCustomerList()));
        addContactID.setItems(Model.Contacts.contactNames(DAO.DBContact.getContactList()));
        addUserID.setItems(Model.Users.userNames(DAO.DBUsers.getUserList()));
        estStart.setText(ZoneId.systemDefault().getId());
        estEnd.setText(ZoneId.systemDefault().getId());


    }

    /**
     * when saving, checks data and adds it to the appointment list and database if it passes the checks
     * @param actionEvent save button
     * @throws IOException ioexception
     */
    public void onSave(ActionEvent actionEvent) throws IOException {
        Boolean check = checkData();
        if(check == true){
            DAO.DBAppointment.getAppointmentList().add(addAppointmentForm());
            DAO.DBAppointment.addAppointmentSQL(DAO.DBAppointment.getAppointmentList().get(DAO.DBAppointment.getAppointmentList().size()-1));
            Appointment.toUTC(DAO.DBAppointment.getAppointmentList());
            Appointment.toLocalTime(DAO.DBAppointment.getAppointmentList());
            DBAppointment.appointmentSize = DBAppointment.appointmentSize+1;
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
     * returns to the dashboard
     * @param actionEvent   cancel button
     * @throws IOException  io exception
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel?");
        alert.setContentText("Are you sure you want to cancel adding appointment?");
        ButtonType CANCEL = new ButtonType("Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
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
     * checks data and returns true if it passes
     * @return checks data
     */
    public Boolean checkData(){
        Boolean check = true;
        Boolean continued = true;
        while(check == true) {
            check = Model.Appointment.checkName(addTitle);
            if (check == false){
                continued = false;
                break;
                }
            check = Model.Appointment.checkDescription(addDescription);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkLocation(addLocation);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkType(addType);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkStartDate(addStartDate);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkEndDate(addEndDate, addStartDate);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkStartTime(addStartTime, addStartDate);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkEndTime(addEndTime, addStartTime, addEndDate, addStartDate);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkAppointmentTime(addStartDate, addEndDate, addStartTime, addEndTime, addCustomerID);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkCustomer(addCustomerID);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkAppointmentOverlap(addStartDate, addEndDate, addStartTime, addEndTime, addCustomerID);
            if (check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkContact(addContactID);
            if(check == false){
                continued = false;
                break;
            }
            check = Model.Appointment.checkUser(addUserID);
            if(check == false){
                continued = false;
                break;
            }
            check = false;
        }
        return continued;
    }

    /**
     * creates and appointment with the entered data
     * @return appointment
     */
    public Appointment addAppointmentForm(){
        Appointment appointment = new Appointment(DBAppointment.appointmentSize+1, addTitle.getText(), addDescription.getText(), addLocation.getText(),
                addType.getText(), LocalDateTime.of(addStartDate.getValue(), (LocalTime)addStartTime.getValue()),
                LocalDateTime.of(addEndDate.getValue(), (LocalTime)addEndTime.getValue()), Model.Customers.findCustomerID((String)addCustomerID.getValue()),
                Model.Users.findUserID((String)addUserID.getValue()), Model.Contacts.findContactID((String)addContactID.getValue()));
        return appointment;
    }

    /**
     * when a start date is selected, sets the end date to be the same
     * @param actionEvent start date selected
     */
    public void startDateSelect(ActionEvent actionEvent) {
        addEndDate.setValue(addStartDate.getValue());
    }
}
