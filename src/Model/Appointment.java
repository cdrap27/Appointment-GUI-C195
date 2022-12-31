package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public static ObservableList<String> time(){
        ObservableList<String> time = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(0,15);
        time.add(LocalTime.of(0,0).toString());
        while(start.isAfter(LocalTime.of(0,0))){
            time.add(start.toString());
            start = start.plusMinutes(15);
        }
        return time;
    }
}
