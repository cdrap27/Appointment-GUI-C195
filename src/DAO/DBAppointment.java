package DAO;

import Database.DBConnection;
import Model.Appointment;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBAppointment {

    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    public static void setAppointmentList(){
        try{
            //accesses the database
            String sql = "SELECT * FROM appointments;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //while there is another appointment, takes in customer information and adds it to the customerList
            while(rs.next()){
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                LocalDateTime created = rs.getTimestamp("Create_Date").toLocalDateTime();
                String creator = rs.getString("Created_By");
                LocalDateTime update = rs.getTimestamp("Last_Update").toLocalDateTime();
                String updater = rs.getString("Last_Updated_By");
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");

                //create an appointment
                Appointment a = new Appointment(id, title, description, location, type, start, end,
                        created, creator, update, updater, customer, user, contact);
                //add appointment
                appointmentList.add(a);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * add an appointment
     */
    public void addAppointment(Appointment a){
        appointmentList.add(a);
    }
    /**
     * get appointment
     */
    public static ObservableList<Appointment> getAppointmentList() {
        return appointmentList;
    }
}
