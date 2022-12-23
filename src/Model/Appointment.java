package Model;

import java.time.LocalDateTime;

public class Appointment {
    private int ID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime created;
    private String creator;
    private LocalDateTime update;
    private String updater;
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
     * @param created
     * @param creator
     * @param update
     * @param updater
     * @param customer
     * @param user
     * @param contact
     */
    public Appointment(int ID, String title, String description, String location, String type, LocalDateTime start,
                       LocalDateTime end, LocalDateTime created, String creator, LocalDateTime update,
                       String updater, int customer, int user, int contact){
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.created = created;
        this.creator = creator;
        this.update = update;
        this.updater = updater;
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

    public void setCreated(LocalDateTime created){
        this.created = created;
    }

    public void setCreator(String creator){
        this.creator = creator;
    }

    public void setUpdate(LocalDateTime update){
        this.update = update;
    }

    public void setUpdater(String updater){
        this.updater = updater;
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

    public LocalDateTime getCreated(){
        return created;
    }

    public String getCreator(){
        return creator;
    }

    public LocalDateTime getUpdate(){
        return update;
    }

    public String getUpdater(){
        return updater;
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
}
