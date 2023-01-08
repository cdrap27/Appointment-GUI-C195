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
 * controls the modify appointment screen
 */
public class modifyAppointmentController {

    /**
     * reference for the gui
     */
    public DatePicker addStartDate;
    /**
     * reference for the gui
     */
    public DatePicker addEndDate;
    /**
     * reference for the gui
     */
    public ChoiceBox addStartTime;
    /**
     * reference for the gui
     */
    public ChoiceBox addEndTime;
    /**
     * reference for the gui
     */
    public ChoiceBox addContactID;
    /**
     * reference for the gui
     */
    public ChoiceBox addCustomerID;
    /**
     * reference for the gui
     */
    public ChoiceBox addUserID;
    /**
     * reference for the gui
     */
    public TextField addAppointmentID;
    /**
     * reference for the gui
     */
    public TextField addTitle;
    /**
     * reference for the gui
     */
    public TextField addDescription;
    /**
     * reference for the gui
     */
    public TextField addLocation;
    /**
     * reference for the gui
     */
    public TextField addType;
    /**
     * temporary appointment for modifying appointment
     */
    public Appointment addAnAppointment;
    /**
     * reference for the gui
     */
    public Text estStart;
    /**
     * reference for the gui
     */
    public Text estEnd;


    /**
     * initializes the modify appointment page
     */
    public void initialize(){

        addStartTime.setItems(Model.Appointment.time());
        addEndTime.setItems(Model.Appointment.time());
        addCustomerID.setItems(Model.Customers.customerNames(DAO.DBCustomers.getCustomerList()));
        addContactID.setItems(Model.Contacts.contactNames(DAO.DBContact.getContactList()));
        addUserID.setItems(Model.Users.userNames(DAO.DBUsers.getUserList()));
        estStart.setText(ZoneId.systemDefault().getId());
        estEnd.setText(ZoneId.systemDefault().getId());
        //creates a temporary appointment and populates all forms with data from the temporary appointment
        Appointment temp = Dashboard.modifyApp;
        addAppointmentID.setText(String.valueOf(temp.getID()));
        addTitle.setText(temp.getTitle());
        addDescription.setText(temp.getDescription());
        addLocation.setText(temp.getLocation());
        addType.setText(temp.getType());
        addStartDate.setValue(temp.getStart().toLocalDate());
        addEndDate.setValue(temp.getStart().toLocalDate());
        addStartTime.setValue(Model.Appointment.time().get(Model.Appointment.getTime(temp.getStart())));
        addEndTime.setValue(Model.Appointment.time().get(Model.Appointment.getTime(temp.getEnd())));
        addContactID.setValue(Model.Contacts.contactNames(DAO.DBContact.getContactList()).get(Model.Contacts.getID(temp.getContact())));
        addCustomerID.setValue(Model.Customers.customerNames(DAO.DBCustomers.getCustomerList()).get(Model.Customers.getID(temp.getCustomer())));
        addUserID.setValue(Model.Users.userNames(DAO.DBUsers.getUserList()).get(Model.Users.getUser(temp.getUser())));


    }

    /**
     * checks the appointment data and saves it if it passes the check/deletes the old appointment
     * @param actionEvent save
     * @throws IOException ioexception
     */
    public void onSave(ActionEvent actionEvent) throws IOException {
        Boolean check = checkData();
        if(check == true){
            int i = Dashboard.appointmentIndex;
            DBAppointment.getAppointmentList().set(i, addAppointmentForm());
           i = Integer.parseInt(addAppointmentID.getText());
            DAO.DBAppointment.deleteAppointment(i);
            DAO.DBAppointment.addAppointmentSQL(addAppointmentForm());
            Appointment.toUTC(DAO.DBAppointment.getAppointmentList());
            Appointment.toLocalTime(DAO.DBAppointment.getAppointmentList());

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
     * @param actionEvent cancel
     * @throws IOException cancel
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel?");
        alert.setContentText("Are you sure you want to cancel modifying appointment?");
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
     * checks data, returns true if passes checks
     * @return check data
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
     * creates an appointment from data entered
     * @return appointment
     */
    public Appointment addAppointmentForm(){
        Appointment appointment = new Appointment(Dashboard.modifyApp.getID(), addTitle.getText(), addDescription.getText(), addLocation.getText(),
                addType.getText(), LocalDateTime.of(addStartDate.getValue(), (LocalTime)addStartTime.getValue()),
                LocalDateTime.of(addEndDate.getValue(), (LocalTime)addEndTime.getValue()), Model.Customers.findCustomerID((String)addCustomerID.getValue()),
                Model.Users.findUserID((String)addUserID.getValue()), Model.Contacts.findContactID((String)addContactID.getValue()));
        return appointment;
    }

    /**
     * when start date is selected, changes end date to match
     * @param actionEvent action event
     */
    public void startDateSelect(ActionEvent actionEvent) {
        addEndDate.setValue(addStartDate.getValue());
    }
}
