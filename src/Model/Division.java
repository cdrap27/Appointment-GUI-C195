package Model;

import DAO.DBCountries;
import DAO.DBDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * division class
 */
public class Division {
    /**
     * reference for the division class
     */
    private int divisionID;
    /**
     * reference for the division class
     */
    private String division;
    /**
     * reference for the division class
     */
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
     * setter
     */
   public void setDivisionID(int divisionID){
       this.divisionID = divisionID;
   }

    /**
     * setter
     */
    public void setDivision(String division){
       this.division = division;
   }

    /**
     * setter
     */
    public void setCountryID(int countryID){
       this.countryID = countryID;
   }

    /**
     * getter
     */
    public int getDivisionID(){
        return divisionID;
    }

    /**
     * getter
     */
    public String getDivision(){
        return division;
    }

    /**
     * getter
     */
    public int getCountryID(){
        return countryID;
    }

    /**
     * Lambda Expression 9: cycles through the list of divisions and adds the division names to the division names list
     * @param country country
     * @return division names
     */
    public static ObservableList<String> divisionNames(String country){
       int countryID = Countries.getCountryID(country);
        ObservableList<String> divisionNames = FXCollections.observableArrayList();
        DBDivision.getDivisionList().forEach(d -> {
           if(d.getCountryID() == countryID)
               divisionNames.add(d.getDivision());
        });
        return divisionNames;
    }

    /**
     * gets division id from division name
     * @param division division name
     * @return division id
     */
    public static int getDivisionID(String division){
        for(int i = 0; i < DBDivision.getDivisionList().size(); i++){
            if(division.equals(DBDivision.getDivisionList().get(i).getDivision()))
                return DBDivision.getDivisionList().get(i).getDivisionID();
        }
        return -1;
    }
}

