package Controllers;

import DAO.DBAppointment;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        customerReport.setItems(null);
        customerTableChoice.setItems(Appointment.getTypes(DAO.DBAppointment.getAppointmentList()));

    }

    public void onMonth(ActionEvent actionEvent) {
        customerReport.setItems(null);
        customerTableChoice.setItems(months());
    }

    public void onAll(ActionEvent actionEvent) {
        customerReport.setItems(DBAppointment.getAppointmentList());
        customerTableChoice.setItems(null);
    }

    public void onCustomerTableChoice(ActionEvent actionEvent) {
        if(type.isSelected()){
            customerReport.setItems(Appointment.getTypeAppointments((String)customerTableChoice.getValue()));
        }
        if(month.isSelected()){
           customerReport.setItems(Appointment.getMonths(customerTableChoice.getSelectionModel().getSelectedIndex()+1));
        }
    }

    public ObservableList<String> months(){
        ObservableList<String> months = FXCollections.observableArrayList();
        months.addAll("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        return months;
    }
}
