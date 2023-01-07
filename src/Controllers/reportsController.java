package Controllers;

import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    }

    public void onType(ActionEvent actionEvent) {
        customerTableChoice.setItems(Appointment.getTypes(DAO.DBAppointment.getAppointmentList()));

    }

    public void onMonth(ActionEvent actionEvent) {
    }

    public void onAll(ActionEvent actionEvent) {
    }

    public void onCustomerTableChoice(ActionEvent actionEvent) {
        if(type.isSelected()){
            customerReport.setItems(Appointment.getTypeAppointments((String)customerTableChoice.getValue()));
        }
    }
}
