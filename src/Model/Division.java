package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Division {
    private int divisionID;
    private String division;
    private int countryID;

    /**
     * creates the division class
     * @param divisionID division id
     * @param division division
     * @param countryID country id
     */
    public Division(int divisionID, String division, int countryID){
       this.divisionID = divisionID;
       this.division = division;
       this.countryID = countryID;
    }

    /**
     * setters
     */
   public void setDivisionID(int divisionID){
       this.divisionID = divisionID;
   }

   public void setDivision(String division){
       this.division = division;
   }

   public void setCountryID(int countryID){
       this.countryID = countryID;
   }

    /**
     * getters
     */
    public int getDivisionID(){
        return divisionID;
    }

    public String getDivision(){
        return division;
    }

    public int getCountryID(){
        return countryID;
    }
}

