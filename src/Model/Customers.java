package Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * creates a customer class
 */
public class Customers {

    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String creator;
    private LocalDateTime changeDate;
    private String changer;
    private int division;

    public Customers(int customerID, String name, String address, String postalCode, String phone, LocalDateTime createDate,
                     String creator, LocalDateTime changeDate, String changer, int division){
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.creator = creator;
        this.changeDate = changeDate;
        this.changer = changer;
        this.division = division;
    }

    /**
     * getters
     */
    public int getCustomerID(){
       return customerID;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
       return address;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public String getPhone(){
       return phone;
    }

    public LocalDateTime getCreateDate(){
       return createDate;
    }

    public String getCreator(){
       return creator;
    }

    public LocalDateTime getChangeDate(){
        return changeDate;
    }

    public String getChanger(){
        return changer;
    }

    public int getDivision(){
        return division;
    }

    /**
     * setters
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }

    public void setCreator(String creator){
        this.creator = creator;
    }

    public void setChangeDate(LocalDateTime changeDate){
        this.changeDate = changeDate;
    }

    public void setChanger(String changer){
        this.changer = changer;
    }

    public void setDivision(int division){
        this.division = division;
    }

}
