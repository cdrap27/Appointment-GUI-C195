package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Countries {
    private int countryID;
    private String countryName;

    /**
     *
     * @param countryID
     * @param countryName
     */
    public Countries(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * setters
     */
    public void setCountryID(int countryID){
        this.countryID = countryID;    }

    public void setCountryName(String countryName){
        this.countryName = countryName;    }

    /**
     * getters
     */
    public int getCountryID(){
        return countryID;    }

    public String getCountryName(){
        return countryName;    }
}

