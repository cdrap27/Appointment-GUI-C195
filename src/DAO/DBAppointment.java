package DAO;

import Database.DBConnection;
import Model.Appointment;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * communicates with the database to get appointment information
 */
public class DBAppointment {
    //checks if the appointment already exists.
    public static Boolean duplicateAppointment = false;
    //checks the size of the appointment list
    public static int appointmentSize;
    //a list of appointments
    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();


    /**
     * adds in test data then sets all appointments into an observable list
     */
    public static void setAppointmentList(){
        //adds test data
        addTestData();
        //trys to get all appointments into observable list
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
                LocalDateTime start = (LocalDateTime) rs.getObject("Start");
                LocalDateTime end = (LocalDateTime) rs.getObject("End");
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                //create an appointment
                Appointment a = new Appointment(id, title, description, location, type, start, end,
                       customer, user, contact);
                //add appointment
                appointmentList.add(a);
            }
            System.out.println("" + appointmentList.size() + " appointments added.");


        }
        catch(SQLException e){
            e.printStackTrace();
        }
        appointmentSize = appointmentList.size();
    }

    /**
     * add an appointment to the observable list
     */
    public void addAppointment(Appointment a){
        appointmentList.add(a);
    }

    /**
     * returns the observable list of appointments
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
        for(int i = 0; i < DAO.DBAppointment.getAppointmentList().size(); i++)
        {
            if (DAO.DBAppointment.getAppointmentList().get(i).getStart().isBefore(beforeDate) &&
                    DAO.DBAppointment.getAppointmentList().get(i).getStart().isAfter(afterDate))
            {
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
        for(int i = 0; i < DAO.DBAppointment.getAppointmentList().size(); i++)
        {
            if (DAO.DBAppointment.getAppointmentList().get(i).getStart().isBefore(beforeDate) &&
                    DAO.DBAppointment.getAppointmentList().get(i).getStart().isAfter(afterDate))
            {
                thisMonth.add(DAO.DBAppointment.getAppointmentList().get(i));
            }
        }
        return thisMonth;
    }

    /**
     * finds appointments occuring within the next 15 minutes and stores them in an observable list
     * @return upcoming appointments
     */
    public static ObservableList<Appointment> upcomingAppointments(){
        ObservableList<Appointment> upcoming = FXCollections.observableArrayList();
        LocalDateTime before = LocalDateTime.now().minusMinutes(15);
        LocalDateTime after = LocalDateTime.now().plusMinutes(15);
        for(int i = 0; i < DBAppointment.appointmentSize; i++){
            if(DAO.DBAppointment.getAppointmentList().get(i).getStart().isAfter(before) &&
            DAO.DBAppointment.getAppointmentList().get(i).getStart().isBefore(after)){
                upcoming.add(DAO.DBAppointment.getAppointmentList().get(i));
            }
        }
        return upcoming;
    }

    /**
     * deletes an appointment by id number
     * @param i id of appointment
     */
    public static void deleteAppointment(int i){
        try{
            //accesses the database
            String sql = "SET FOREIGN_KEY_CHECKS=0;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
            sql = "DELETE FROM appointments WHERE Appointment_ID =  "+ i + ";";
            ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
            System.out.println("deleted");
            }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds an appointment to the appointment list
     * @param a adds this appointment
     */
    public static void addAppointmentSQL(Appointment a){
        duplicateAppointment = false;
        try{
            //accesses the database
            String sql = "SELECT * FROM appointments;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            /*
            while there is another appointment, takes in customer information and checks to see if it matches an
            appointment currently in the database
            */
            while(rs.next()){
                int i = rs.getInt("Appointment_ID");
                String name = rs.getString("Title");

                if(a.getID() == i)
                    duplicateAppointment = true;

            }
            //if there is no match, adds the appointment to the database
            if (duplicateAppointment == false){
                //accesses the database
                sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                 ps = DBConnection.getConnection().prepareStatement(sql);
                ps.setInt(1, a.getID());
                ps.setString(2, a.getTitle());
                ps.setString(3, a.getDescription());
                ps.setString(4, a.getLocation());
                ps.setString(5, a.getType());
                ps.setTimestamp(6, Timestamp.valueOf(a.getStart()));
                ps.setTimestamp(7, Timestamp.valueOf(a.getEnd()));
                ps.setInt(8, a.getCustomer());
                ps.setInt(9, a.getUser());
                ps.setInt(10, a.getContact());
                ps.execute();
                //System.out.println("Added an appointment");
            }
            /*
            else if(duplicateAppointment == true){
                //System.out.println("duplicate appointment");
            }*/

        }
        catch(SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * deletes appointments that are associated with a customer being deleted.
     * @param i i is the customer id
     * @throws SQLException sql exception
     */
    public static void deleteAssociatedAppointment(int i) throws SQLException {
        //accesses the database
        String sql = "SELECT * FROM appointments;";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
            /*
            while there is another appointment, takes in customer information and checks to see if it matches an
            appointment currently in the database
            */
        while(rs.next()){
            int j = rs.getInt("Customer_ID");
            //goes through the database to find matching customer ids
            if(i == j){
                //if a match is found, j is set to the corresponding appointment id for deletion
                j = rs.getInt("Appointment_ID");
                //cycles through the appointment list to find a matching entry and deletes it
                for(int k =0; k<appointmentList.size(); k++){
                    if(appointmentList.get(k).getCustomer() == i){
                       appointmentList.remove(k);
                       k = 0;
                   }
                }
                deleteAppointment(j);
            }
            }
    }

    /**
     * adds in test data
     */
    private static void addTestData(){
        //test data for this week
        Appointment a = new Appointment(3, "week", "gotta test", "Mall", "regualr",
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusMinutes(15),
                2, 2, 3);
        addAppointmentSQL(a);
        //test for this month
        a = new Appointment(4, "month", "testing more", "Hawaii", "expensive",
                LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(10).plusHours(1),
                3, 2, 3);
        addAppointmentSQL(a);
        //another month test
        a = new Appointment(5, "month 2", "gotta test", "Mall", "regualr",
                LocalDateTime.now().plusDays(15), LocalDateTime.now().plusDays(15).plusMinutes(10),
                2, 2, 3);
        addAppointmentSQL(a);
        //default data 1
        a = new Appointment(1, "title", "description", "location", "Planning Session",
                LocalDateTime.now().minusMinutes(5),
                LocalDateTime.now(),
                 1, 1, 3);
        addAppointmentSQL(a);
        //default data 2
        a = new Appointment(2, "title", "description", "location", "De-Briefing",
                LocalDateTime.now().plusMinutes(14),
                LocalDateTime.now().plusMinutes(15),
                 1, 1, 3);
        addAppointmentSQL(a);

    }

}
