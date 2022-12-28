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
    public static Boolean duplicateAppointment = false;
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
     * deletes an appointment by id number
     * @param i id of appointment
     */
    public static void deleteAppointment(int i){
        try{
            //accesses the database
            String sql = "DELETE FROM appointments WHERE Appointment_ID = " + i + ";";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
            System.out.println("deleted " + sql);
            }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * adds an appointment to the appointment list
     * @param a adds this appointment
     */
    private static void addAppointmentSQL(Appointment a){
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
                if(a.getID() == i)
                    duplicateAppointment = true;
            }
            //if there is no match, adds the appointment to the database
            if (duplicateAppointment == false){
                //accesses the database
                sql = "INSERT INTO appointments VALUES (" + a.getID() + ",'" + a.getTitle() + "','" + a.getDescription() +
                        "','" + a.getLocation() + "','" + a.getType() + "','" + a.getStart() +
                        "','" + a.getEnd() + "','" + a.getCreated() + "','" + a.getCreator() + "','" + a.getUpdate() + "','" +
                        a.getUpdater() + "'," + a.getCustomer() + "," + a.getUser() + "," + a.getContact() + ");";
                 ps = DBConnection.getConnection().prepareStatement(sql);
                ps.execute();
            }
            else if(duplicateAppointment == true){
                System.out.println("duplicate");
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }


    }

    private static void addTestData(){
        //test data for this week
        Appointment a = new Appointment(3, "week", "gotta test", "Mall", "regualr",
                LocalDateTime.now().plusDays(1), LocalDateTime.now(), LocalDateTime.now(), "cahd",
                LocalDateTime.now(), "cahd", 1, 2, 3);
        addAppointmentSQL(a);
        //test for this month
        a = new Appointment(4, "month", "gotta test", "Mall", "regualr",
                LocalDateTime.now().minusDays(15), LocalDateTime.now(), LocalDateTime.now(), "cahd",
                LocalDateTime.now(), "cahd", 1, 2, 3);
        addAppointmentSQL(a);
        //another month test
        a = new Appointment(5, "month 2", "gotta test", "Mall", "regualr",
                LocalDateTime.now().plusDays(15), LocalDateTime.now(), LocalDateTime.now(), "cahd",
                LocalDateTime.now(), "cahd", 1, 2, 3);
        addAppointmentSQL(a);
        //default data 1
        a = new Appointment(1, "title", "description", "location", "Planning Session",
                LocalDateTime.of(2020, 05, 28, 12, 00, 00),
                LocalDateTime.of(2020, 05, 28, 13, 00, 00),
                LocalDateTime.of(2020, 05, 28, 12, 00, 00),
                "script",
                LocalDateTime.of(2020, 05, 28, 12, 00, 00),
                "script", 1, 1, 3);
        addAppointmentSQL(a);
        //default data 2
        a = new Appointment(2, "title", "description", "location", "De-Briefing",
                LocalDateTime.of(2020, 05, 29, 12, 00, 00),
                LocalDateTime.of(2020, 05, 29, 13, 00, 00),
                LocalDateTime.of(2021, 9, 10, 13, 14, 10),
                "script",
                LocalDateTime.of(2021, 9, 10, 17, 14, 10),
                "script", 1, 1, 3);
        addAppointmentSQL(a);

    }

}
