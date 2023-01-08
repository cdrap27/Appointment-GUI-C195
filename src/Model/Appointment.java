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

/**
 * establishes appointment class
 */
public class Appointment {

    /**
     * reference for appointment class
     */
    private int ID;
    /**
     * reference for appointment class
     */
    private String title;
    /**
     * reference for appointment class
     */
    private String description;
    /**
     * reference for appointment class
     */
    private String location;
    /**
     * reference for appointment class
     */
    private String type;
    /**
     * reference for appointment class
     */
    private LocalDateTime start;
    /**
     * reference for appointment class
     */
    private LocalDateTime end;
    /**
     * reference for appointment class
     */
    private int customer;
    /**
     * reference for appointment class
     */
    private int user;
    /**
     * reference for appointment class
     */
    private int contact;

    /**
     * appointment class
     * @param ID id
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param customer customer
     * @param user user
     * @param contact contact
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
     * setter
     */
    public void setID(int ID){
        this.ID = ID;
    }

    /**
     * setter
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * setter
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * setter
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * setter
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * setter
     */
    public void setStart(LocalDateTime start){
        this.start = start;
    }

    /**
     * setter
     */
    public void setEnd(LocalDateTime end){
        this.end = end;
    }


    /**
     * setter
     */
    public void setCustomer(int customer){
        this.customer = customer;
    }

    /**
     * setter
     */
    public void setUser(int user){
        this.user = user;
    }

    /**
     * setter
     */
    public void setContact(int contact){
        this.contact = contact;
    }

    /**
     * getter
     */
    public int getID(){
        return ID;
    }

    /**
     * getter
     */
    public String getTitle(){
        return title;
    }

    /**
     * getter
     */
    public String getDescription(){
        return description;
    }

    /**
     * getter
     */
    public String getLocation(){
        return location;
    }

    /**
     * getter
     */
    public String getType(){
        return type;
    }

    /**
     * getter
     */
    public LocalDateTime getStart(){
        return start;
    }

    /**
     * getter
     */
    public LocalDateTime getEnd(){
        return end;
    }

    /**
     * getter
     */
    public int getCustomer(){
        return customer;
    }

    /**
     * getter
     */
    public int getUser(){
        return user;
    }

    /**
     * getter
     */
    public int getContact(){
        return contact;
    }

    /**
     * Observable list of times in 15 minute increments
     */
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

    /**
     * gets time
     * @param time time
     * @return time
     */
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
     * checks name
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

    /**
     * checks description
     * @param addDescription text field
     * @return description
     */
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

    /**
     * checks location
     * @param addLocation location
     * @return location
     */
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

    /**
     * checks type
     * @param addType type
     * @return type
     */
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

    /**
     * checks start date
     * @param addStartDate start date
     * @return start date
     */
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

    /**
     * chekcs end date
      * @param addEndDate end date
     * @param addStartDate start date
     * @return end date
     */
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

    /**
     * checks start time
     * @param addStartTime start time
     * @param addStartDate start date
     * @return
     */
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

    /**
     * checks end time
     * @param addEndTime end time
     * @param addStartTime start time
     * @param addEndDate end date
     * @param addStartDate start date
     * @return end time
     */
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


    /**
     * checks customer
     * @param customer customer
     * @return customer
     */
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

    /**
     * checks appointment time
     * @param addStartDate start date
     * @param addEndDate end date
     * @param addStartTime start time
     * @param addEndTime end time
     * @param addCustomer add customer
     * @return appointment time
     */
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

    /**
     * checks appointment overlap
     * @param addStartDate start date
     * @param addEndDate end date
     * @param addStartTime start time
     * @param addEndTime end time
     * @param addCustomer add customer
     * @return appointment overlap
     */
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

    /**
     * checks contact
     * @param addContactID contact
     * @return contact
     */
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

    /**
     * checks user
     * @param addUserID user id
     * @return user
     */
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

    /**
     * gets types
     * @param apps appointments
     * @return types
     */
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

    /**
     * gets type appointments
     * @param type string type
     * @return type appointments
     */
    public static ObservableList<Appointment> getTypeAppointments(String type){
        ObservableList<Appointment> getTypeAppointments = FXCollections.observableArrayList();
        DAO.DBAppointment.getAppointmentList().forEach(a ->{
            if(a.getType().equals(type))
                getTypeAppointments.add(a);
        });
        return getTypeAppointments;
    }

    /**
     * Lambda Expression #3: uses a lambda expression to cycle through all appointments and add appointments with a
     * matching month to the getMonths list.
     * @param month month
     * @return list of appointments
     */
    public static ObservableList<Appointment> getMonths(int month){
        ObservableList<Appointment> getMonths = FXCollections.observableArrayList();
        DBAppointment.getAppointmentList().forEach(a ->{
            if(a.getStart().getMonth().getValue() == month){
                getMonths.add(a);
            }
        });
        return getMonths;
    }

    /**
     * Lambda expression #4: uses a lambda expression to cycle through all appointments and add appointments with a
     * matching contact ID to the getContactSchedule list
     * @param contactID contact id
     * @return list of appointments
     */
    public static ObservableList<Appointment> getContactSchedule(int contactID){
        ObservableList<Appointment> getContactSchedule = FXCollections.observableArrayList();
        DAO.DBAppointment.getAppointmentList().forEach(a ->{
            if(a.getContact() == contactID){
                getContactSchedule.add(a);
            }
        });
        return getContactSchedule;
    }

}
