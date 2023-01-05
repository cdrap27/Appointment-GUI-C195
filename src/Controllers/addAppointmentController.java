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

public class addAppointmentController {

    public DatePicker addStartDate;
    public DatePicker addEndDate;
    public ChoiceBox addStartTime;
    public ChoiceBox addEndTime;
    public ChoiceBox addContactID;
    public ChoiceBox addCustomerID;
    public ChoiceBox addUserID;
    public TextField addAppointmentID;
    public TextField addTitle;
    public TextField addDescription;
    public TextField addLocation;
    public TextField addType;
    public Appointment addAnAppointment;
    public Text estStart;
    public Text estEnd;



    public void initialize(){
        addAppointmentID.setText(Integer.toString(DBAppointment.appointmentSize+1));
        addStartTime.setItems(Model.Appointment.time());
        addEndTime.setItems(Model.Appointment.time());
        addCustomerID.setItems(Model.Customers.customerNames(DAO.DBCustomers.getCustomerList()));
        addContactID.setItems(Model.Contacts.contactNames(DAO.DBContact.getContactList()));
        addUserID.setItems(Model.Users.userNames(DAO.DBUsers.getUserList()));
        estStart.setText(ZoneId.systemDefault().getId());
        estEnd.setText(ZoneId.systemDefault().getId());


    }

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

    public Appointment addAppointmentForm(){
        Appointment appointment = new Appointment(DBAppointment.appointmentSize+1, addTitle.getText(), addDescription.getText(), addLocation.getText(),
                addType.getText(), LocalDateTime.of(addStartDate.getValue(), (LocalTime)addStartTime.getValue()),
                LocalDateTime.of(addEndDate.getValue(), (LocalTime)addEndTime.getValue()), Model.Customers.findCustomerID((String)addCustomerID.getValue()),
                Model.Users.findUserID((String)addUserID.getValue()), Model.Contacts.findContactID((String)addContactID.getValue()));
        return appointment;
    }

    public void startDateSelect(ActionEvent actionEvent) {
        addEndDate.setValue(addStartDate.getValue());
    }
}
