package Model;

import DAO.DBCountries;
import DAO.DBDivision;
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

    public static ObservableList<String> divisionNames(String country){
       int countryID = Countries.getCountryID(country);
        ObservableList<String> divisionNames = FXCollections.observableArrayList();
        DBDivision.getDivisionList().forEach(d -> {
           if(d.getCountryID() == countryID)
               divisionNames.add(d.getDivision());
        });
        return divisionNames;
    }

    public static int getDivisionID(String division){
        for(int i = 0; i < DBDivision.getDivisionList().size(); i++){
            if(division.equals(DBDivision.getDivisionList().get(i).getDivision()))
                return DBDivision.getDivisionList().get(i).getDivisionID();
        }
        return -1;
    }
}

