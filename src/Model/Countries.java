package Model;

import DAO.DBCountries;
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

    public static ObservableList<String> countryNames(ObservableList<Countries> countries){
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        countries.forEach(c -> {
            countryNames.add(c.countryName);
        });
        return countryNames;
    }

    public static int getCountryID(String countryName){
        for(int i =0; i < DBCountries.getCountryList().size(); i++){
            if(countryName.equals(DBCountries.getCountryList().get(i).getCountryName())){
                return DBCountries.getCountryList().get(i).getCountryID();
            }
        }
        return -1;
    }
}

