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
        //addTestData();
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

    /**
     * creates a list of appointments for this week
     * @return current weeks appointments
     */
    public static ObservableList<Appointment> thisWeek(){
        ObservableList<Appointment> thisWeek = FXCollections.observableArrayList();
        LocalDateTime beforeDate = LocalDateTime.now().plusDays(7);
        LocalDateTime afterDate = LocalDateTime.now().minusDays(7);
        for(int i = 0; i < DAO.DBAppointment.getAppointmentList().size(); i++) {

            if (DAO.DBAppointment.getAppointmentList().get(i).getStart().isBefore(beforeDate) &&
                    DAO.DBAppointment.getAppointmentList().get(i).getStart().isAfter(afterDate)) {
                thisWeek.add(DAO.DBAppointment.getAppointmentList().get(i));
            }
        }
        return thisWeek;
    }

    /**
     * returns appointments for this month
     * @return current months appointments
     */
    public static ObservableList<Appointment> thisMonth(){
        ObservableList<Appointment> thisMonth = FXCollections.observableArrayList();
        LocalDateTime beforeDate = LocalDateTime.now().plusDays(30);
        LocalDateTime afterDate = LocalDateTime.now().minusDays(30);
        for(int i = 0; i < DAO.DBAppointment.getAppointmentList().size(); i++) {

            if (DAO.DBAppointment.getAppointmentList().get(i).getStart().isBefore(beforeDate) &&
                    DAO.DBAppointment.getAppointmentList().get(i).getStart().isAfter(afterDate)) {
                thisMonth.add(DAO.DBAppointment.getAppointmentList().get(i));
            }
        }
        return thisMonth;

    }

    private static void addTestData(){
        //test data for this week
        Appointment a = new Appointment(3, "week", "gotta test", "Mall", "regualr",
                LocalDateTime.now().plusDays(1), LocalDateTime.now(), LocalDateTime.now(), "cahd",
                LocalDateTime.now(), "cahd", 1, 2, 3);
        appointmentList.add(a);
        //test for this month
        a = new Appointment(3, "month", "gotta test", "Mall", "regualr",
                LocalDateTime.now().minusDays(15), LocalDateTime.now(), LocalDateTime.now(), "cahd",
                LocalDateTime.now(), "cahd", 1, 2, 3);
        appointmentList.add(a);
        //another month test
        a = new Appointment(3, "month 2", "gotta test", "Mall", "regualr",
                LocalDateTime.now().plusDays(15), LocalDateTime.now(), LocalDateTime.now(), "cahd",
                LocalDateTime.now(), "cahd", 1, 2, 3);
        appointmentList.add(a);

    }
}
