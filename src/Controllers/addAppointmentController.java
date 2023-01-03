package Controllers;

import DAO.DBAppointment;
import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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



    public void initialize(){
        addAppointmentID.setText(Integer.toString(DBAppointment.appointmentSize+1));
        addStartTime.setItems(Model.Appointment.time());
        addEndTime.setItems(Model.Appointment.time());
    }

    public void onSave(ActionEvent actionEvent) {
        Boolean check = checkData();


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
        while(check == true) {
            check = Model.Appointment.checkName(addTitle);
            if (check == false)
                break;
            check = Model.Appointment.checkDescription(addDescription);
            if (check == false)
                break;
            check = Model.Appointment.checkLocation(addLocation);
            if (check == false)
                break;
            check = Model.Appointment.checkType(addType);
            if (check == false)
                break;
            check = Model.Appointment.checkStartDate(addStartDate);
            if (check == false)
                break;
            check = Model.Appointment.checkEndDate(addEndDate, addStartDate);
            if (check == false)
                break;
            check = Model.Appointment.checkStartTime(addStartTime);
            if (check == false)
                break;
            check = Model.Appointment.checkEndTime(addEndTime, addStartTime);
            if (check == false)
                break;
            check = Model.Appointment.checkAppointmentTime(addStartDate, addEndDate, addStartTime, addEndTime);
            if (check == false)
                break;
            check = false;
        }
        return check;
    }
}
