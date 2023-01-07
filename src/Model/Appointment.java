package Model;

import Controllers.Dashboard;
import DAO.DBAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.*;
import java.util.concurrent.atomic.AtomicReference;

public class Appointment {
    private int ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customer;
    private int user;
    private int contact;

    /**
     * appointment class
     * @param ID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param customer
     * @param user
     * @param contact
     */
    public Appointment(int ID, String title, String description, String location, String type, LocalDateTime start,
                       LocalDateTime end,  int customer, int user, int contact){
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;

        this.customer = customer;
        this.user = user;
        this.contact = contact;

    }
    /**
     * setters
     */
    public void setID(int ID){
        this.ID = ID;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setStart(LocalDateTime start){
        this.start = start;
    }

    public void setEnd(LocalDateTime end){
        this.end = end;
    }


    public void setCustomer(int customer){
        this.customer = customer;
    }

    public void setUser(int user){
        this.user = user;
    }

    public void setContact(int contact){
        this.contact = contact;
    }

    /**
     * getters
     */
    public int getID(){
        return ID;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getLocation(){
        return location;
    }

    public String getType(){
        return type;
    }

    public LocalDateTime getStart(){
        return start;
    }

    public LocalDateTime getEnd(){
        return end;
    }


    public int getCustomer(){
        return customer;
    }

    public int getUser(){
        return user;
    }

    public int getContact(){
        return contact;
    }

    public static ObservableList<LocalTime> time(){
        ObservableList<LocalTime> time = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(0,15);
        time.add(LocalTime.of(0,0));
        while(start.isAfter(LocalTime.of(0,0))){
            time.add(start);
            start = start.plusMinutes(15);
        }
        return time;
    }

    public static int getTime(LocalDateTime time){
        LocalTime t = time.toLocalTime();
        int getTime;
        for(int i = 0; i < time().size(); i++){
            if(t.equals(time().get(i))){
                getTime = i;
                return getTime;
            }
            else if(t.isAfter(time().get(i)) && t.isBefore(time().get(i+1))){
                getTime = i;
                return getTime;
            }
        }
        return 0;
    }

    /**
     * checks
     */
    public static Boolean checkName(TextField addTitle){
        String temp = addTitle.getText();
        Boolean checkName = true;
        if(temp.length() < 1) {
            checkName = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Title Error");
            errorAlert.setContentText("Title Error");
            errorAlert.showAndWait();
        }
        return checkName;
    }

    public static Boolean checkDescription(TextField addDescription){
        String temp = addDescription.getText();
        Boolean checkName = true;
        if(temp.length() < 1) {
            checkName = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Description Error");
            errorAlert.setContentText("Description Error");
            errorAlert.showAndWait();
        }
        return checkName;
    }

    public static Boolean checkLocation(TextField addLocation){
        String temp = addLocation.getText();
        Boolean checkName = true;
        if(temp.length() < 1) {
            checkName = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Location Error");
            errorAlert.setContentText("Location Error");
            errorAlert.showAndWait();
        }
        return checkName;
    }

    public static Boolean checkType(TextField addType){
        String temp = addType.getText();
        Boolean checkName = true;
        if(temp.length() < 1) {
            checkName = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Type Error");
            errorAlert.setContentText("Type Error");
            errorAlert.showAndWait();
        }
        return checkName;
    }

    public static Boolean checkStartDate(DatePicker addStartDate){
        Boolean checkName = true;

            LocalDate date = (LocalDate) addStartDate.getValue();


            if(date == null) {
                checkName = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Start Date Error");
                errorAlert.setContentText("No start date selected");
                errorAlert.showAndWait();
                return checkName;
            }
            LocalDate currentDate = LocalDate.now();
            if(date.isBefore(currentDate)){
                checkName = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Start Date Error");
                errorAlert.setContentText("Start date cannot be before current date.");
                errorAlert.showAndWait();
                return checkName;
            }
            return checkName;
        }

    public static Boolean checkEndDate(DatePicker addEndDate, DatePicker addStartDate){
        Boolean checkName = true;

        LocalDate date = (LocalDate) addEndDate.getValue();


        if(date == null) {
            checkName = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("End Date Error");
            errorAlert.setContentText("No end date selected");
            errorAlert.showAndWait();
            return checkName;
        }
       if(date.isBefore((LocalDate)addStartDate.getValue())){
           checkName = false;
           Alert errorAlert = new Alert(Alert.AlertType.ERROR);
           errorAlert.setHeaderText("End Date Error");
           errorAlert.setContentText("End date cannot be before start date");
           errorAlert.showAndWait();
           return checkName;
       }
        return checkName;
    }

    public static Boolean checkStartTime(ChoiceBox addStartTime, DatePicker addStartDate){
        Boolean check = true;
             LocalTime time = (LocalTime) addStartTime.getSelectionModel().getSelectedItem();
        if(time == null){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Start Time Error");
            errorAlert.setContentText("No start time selected.");
            errorAlert.showAndWait();
            return check;
        }
        LocalTime t1 = LocalTime.of(8, 0);
        LocalTime t2 = LocalTime.of(22,0);
        LocalDateTime startingStart = LocalDateTime.of(addStartDate.getValue(), t1);
        ZonedDateTime zoneStart = startingStart.atZone(ZoneId.of("EST", ZoneId.SHORT_IDS));
        zoneStart = zoneStart.withZoneSameInstant(ZoneId.of(System.getProperty("user.timezone")));

        startingStart = zoneStart.toLocalDateTime();
        LocalDateTime startingEnd;
        ZonedDateTime zoneStartend = LocalDateTime.of(addStartDate.getValue(),t2).atZone(ZoneId.of("EST", ZoneId.SHORT_IDS));
        zoneStartend = zoneStartend.withZoneSameInstant(ZoneId.of(System.getProperty("user.timezone")));
        startingEnd = zoneStartend.toLocalDateTime();
        LocalDateTime userStart = LocalDateTime.of(addStartDate.getValue(), (LocalTime) addStartTime.getValue());
        LocalDateTime userEnd = LocalDateTime.of(addStartDate.getValue(), (LocalTime) addStartTime.getValue());
        if(userEnd.isBefore(startingStart) || userEnd.isAfter(startingEnd)){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Start Time Error");
            errorAlert.setContentText("Start time is outside of business hours.\n" +"Business hours in your timezone are "
                    + startingStart.toLocalTime() + "-" + startingEnd.toLocalTime());
            errorAlert.showAndWait();
            return check;
        }
        return check;
    }

    public static Boolean checkEndTime(ChoiceBox addEndTime, ChoiceBox addStartTime, DatePicker addEndDate, DatePicker addStartDate){
            Boolean check = true;
            LocalTime time = (LocalTime) addEndTime.getSelectionModel().getSelectedItem();
            if(time == null){
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("End Time Error");
                errorAlert.setContentText("No end time selected.");
                errorAlert.showAndWait();
                return check;
            }

        LocalTime t1 = LocalTime.of(8, 0);
        LocalTime t2 = LocalTime.of(22,0);
        LocalDateTime endingStart = LocalDateTime.of(addEndDate.getValue(), t1);
        ZonedDateTime zoneEnd = endingStart.atZone(ZoneId.of("EST", ZoneId.SHORT_IDS));
        zoneEnd = zoneEnd.withZoneSameInstant(ZoneId.of(System.getProperty("user.timezone")));

       endingStart = zoneEnd.toLocalDateTime();
       LocalDateTime endingEnd;
        ZonedDateTime zoneEndEnd = LocalDateTime.of(addEndDate.getValue(),t2).atZone(ZoneId.of("EST", ZoneId.SHORT_IDS));
        zoneEndEnd = zoneEndEnd.withZoneSameInstant(ZoneId.of(System.getProperty("user.timezone")));
        endingEnd = zoneEndEnd.toLocalDateTime();
        LocalDateTime userStart = LocalDateTime.of(addEndDate.getValue(), (LocalTime) addStartTime.getValue());
        LocalDateTime userEnd = LocalDateTime.of(addEndDate.getValue(), (LocalTime) addEndTime.getValue());
        if(userEnd.isBefore(endingStart) || userEnd.isAfter(endingEnd)){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("End Time Error");
            errorAlert.setContentText("End time is outside of business hours.\n" +"Business hours in your timezone are "
            + endingStart.toLocalTime() + "-" + endingEnd.toLocalTime());
            errorAlert.showAndWait();
            return check;
        }
            return check;
        }



    public static Boolean checkCustomer(ChoiceBox customer){
        Boolean check = true;
        String cust = (String)customer.getSelectionModel().getSelectedItem();
        if(cust == null){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Customer Error");
            errorAlert.setContentText("No customer selected.");
            errorAlert.showAndWait();
            return check;
        }

        return check;
    }

    public static Boolean checkAppointmentTime(DatePicker addStartDate, DatePicker addEndDate, ChoiceBox addStartTime,
                                               ChoiceBox addEndTime, ChoiceBox addCustomer){
        Boolean check = true;
        LocalDate startDate = addStartDate.getValue();
        LocalDate endDate = addEndDate.getValue();
        LocalTime startTime = (LocalTime)addStartTime.getSelectionModel().getSelectedItem();
        LocalTime endTime = (LocalTime)addEndTime.getSelectionModel().getSelectedItem();
        if(startDate.equals(endDate)){
            if(endTime.isBefore(startTime)){
                check = false;
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("End Time Error");
                errorAlert.setContentText("End time is before start time.");
                errorAlert.showAndWait();
                return check;
            }
        }

        return check;

    }

    public static Boolean checkAppointmentOverlap(DatePicker addStartDate, DatePicker addEndDate, ChoiceBox addStartTime,
                                               ChoiceBox addEndTime, ChoiceBox addCustomer) {
        Boolean check = true;
        LocalDate startDate = addStartDate.getValue();
        LocalDate endDate = addEndDate.getValue();
        LocalTime startTime = (LocalTime) addStartTime.getSelectionModel().getSelectedItem();
        LocalTime endTime = (LocalTime) addEndTime.getSelectionModel().getSelectedItem();

        LocalDateTime startDate2 = LocalDateTime.of(startDate, startTime);
        LocalDateTime endDate2 = LocalDateTime.of(endDate, endTime);

        int customerID = Model.Customers.findCustomerID((String)addCustomer.getSelectionModel().getSelectedItem());
        for(int i = 0; i < DAO.DBAppointment.getAppointmentList().size(); i++) {
            if(Dashboard.modifyApp.getID() != DAO.DBAppointment.getAppointmentList().get(i).getID()) {
                if (DAO.DBAppointment.getAppointmentList().get(i).getCustomer() == customerID) {
                    if (startDate2.isAfter(DAO.DBAppointment.getAppointmentList().get(i).getStart())) {
                        if (startDate2.isBefore(DAO.DBAppointment.getAppointmentList().get(i).getEnd())) {
                            check = false;
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Appointment Error");
                            errorAlert.setContentText("Appointment overlaps with Appointment: " + DAO.DBAppointment.getAppointmentList().get(i).getID());
                            errorAlert.showAndWait();
                            return check;

                        }
                    }
                    if (endDate2.isAfter(DAO.DBAppointment.getAppointmentList().get(i).getStart())) {
                        if (endDate2.isBefore(DAO.DBAppointment.getAppointmentList().get(i).getEnd())) {
                            check = false;
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Appointment Error");
                            errorAlert.setContentText("Appointment overlaps with Appointment: " + DAO.DBAppointment.getAppointmentList().get(i).getID());
                            errorAlert.showAndWait();
                            return check;

                        }
                    }
                    if (startDate2.isBefore(DAO.DBAppointment.getAppointmentList().get(i).getStart())) {
                        if (endDate2.isAfter(DAO.DBAppointment.getAppointmentList().get(i).getEnd())) {
                            check = false;
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Appointment Error");
                            errorAlert.setContentText("Appointment overlaps with Appointment: " + DAO.DBAppointment.getAppointmentList().get(i).getID());
                            errorAlert.showAndWait();
                            return check;

                        }
                    }
                    if (startDate2.equals(DAO.DBAppointment.getAppointmentList().get(i).getEnd())) {
                        check = false;
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Appointment Error");
                        errorAlert.setContentText("Appointment overlaps with Appointment: " + DAO.DBAppointment.getAppointmentList().get(i).getID());
                        errorAlert.showAndWait();
                        return check;

                    }
                    if (endDate2.equals(DAO.DBAppointment.getAppointmentList().get(i).getStart())) {
                        check = false;
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Appointment Error");
                        errorAlert.setContentText("Appointment overlaps with Appointment: " + DAO.DBAppointment.getAppointmentList().get(i).getID());
                        errorAlert.showAndWait();
                        return check;

                    }
                    if (endDate2.equals(DAO.DBAppointment.getAppointmentList().get(i).getEnd())) {
                        check = false;
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Appointment Error");
                        errorAlert.setContentText("Appointment overlaps with Appointment: " + DAO.DBAppointment.getAppointmentList().get(i).getID());
                        errorAlert.showAndWait();
                        return check;
                    }

                    if (startDate2.equals(DAO.DBAppointment.getAppointmentList().get(i).getStart())) {
                        check = false;
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Appointment Error");
                        errorAlert.setContentText("Appointment overlaps with Appointment: " + DAO.DBAppointment.getAppointmentList().get(i).getID());
                        errorAlert.showAndWait();
                        return check;

                    }

                }
            }
        }
        return check;

    }

    public static Boolean checkContact(ChoiceBox addContactID){
        Boolean check = true;
        String cust = (String)addContactID.getSelectionModel().getSelectedItem();
        if(cust == null){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Contact Error");
            errorAlert.setContentText("No contact selected.");
            errorAlert.showAndWait();
            return check;
        }
        return check;

    }

    public static Boolean checkUser(ChoiceBox addUserID){
        Boolean check = true;
        String cust = (String)addUserID.getSelectionModel().getSelectedItem();
        if(cust == null){
            check = false;
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("User Error");
            errorAlert.setContentText("No user selected.");
            errorAlert.showAndWait();
            return check;
        }
        System.out.print("user is " + Model.Users.findUserID(cust));
        return check;

    }

    /**
     * Lambda Expression 1: uses a lambda expression to cycle through the appointments and change all times to local time
     * @param appointments  appointment list
     * @return  appointment list with updated times
     */
    public static ObservableList<Appointment> toLocalTime(ObservableList<Appointment> appointments){
        appointments.forEach(a ->{
            ZonedDateTime zoneStart = a.getStart().atZone(ZoneId.of("UTC"));
          zoneStart = zoneStart.withZoneSameInstant(ZoneId.of(System.getProperty("user.timezone")));
          a.setStart(zoneStart.toLocalDateTime());
          ZonedDateTime zoneEnd = a.getEnd().atZone(ZoneId.of("UTC"));
          zoneEnd = zoneEnd.withZoneSameInstant(ZoneId.of(System.getProperty("user.timezone")));
          a.setEnd(zoneEnd.toLocalDateTime());
        });
        return appointments;
    }

    /**
     * Lambda Expression 2: uses a lambda expression to cycle through the appointments and change all times to UTC
     * @param appointments  appointment list
     * @return  appointment list with updated times
     */
    public static ObservableList<Appointment> toUTC(ObservableList<Appointment> appointments){
        appointments.forEach(a ->{
            ZonedDateTime zoneStart = a.getStart().atZone(ZoneId.of(System.getProperty("user.timezone")));
            zoneStart = zoneStart.withZoneSameInstant(ZoneId.of("UTC"));
            a.setStart(zoneStart.toLocalDateTime());
            ZonedDateTime zoneEnd = a.getEnd().atZone(ZoneId.of(System.getProperty("user.timezone")));
            zoneEnd = zoneEnd.withZoneSameInstant(ZoneId.of("UTC"));
            a.setEnd(zoneEnd.toLocalDateTime());
        });
        return appointments;
    }

    public static ObservableList<String> getTypes(ObservableList<Appointment> apps){
            ObservableList<String> getTypes = FXCollections.observableArrayList();
            Boolean duplicate = false;
           for(int i = 0; i < apps.size(); i++){
               duplicate = false;
               for(int j = 0; j < getTypes.size(); j++){
                   if(getTypes.get(j).equals(apps.get(i).getType()))
                       duplicate = true;
               }
               if(duplicate == false)
                   getTypes.add(apps.get(i).getType());
           }
            return getTypes;
    }

    public static ObservableList<Appointment> getTypeAppointments(String type){
        ObservableList<Appointment> getTypeAppointments = FXCollections.observableArrayList();
        DAO.DBAppointment.getAppointmentList().forEach(a ->{
            if(a.getType().equals(type))
                getTypeAppointments.add(a);
        });
        return getTypeAppointments;
    }

    public static ObservableList<Appointment> getMonths(int month){
        ObservableList<Appointment> getMonths = FXCollections.observableArrayList();
        DBAppointment.getAppointmentList().forEach(a ->{
            if(a.getStart().getMonth().getValue() == month){
                getMonths.add(a);
            }
        });
        return getMonths;
    }


}
