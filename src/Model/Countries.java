package Model;

import DAO.DBCountries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * creates the countries class
 */
public class Countries {
    /**
     * reference for the countries class
     */
    private int countryID;
    /**
     * reference for the countries class
     */
    private String countryName;

    /**
     *creates countries class
     * @param countryID country id
     * @param countryName country name
     */
    public Countries(int countryID, String countryName){
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * setter
     */
    public void setCountryID(int countryID){
        this.countryID = countryID;    }

    /**
     * setter
     */
    public void setCountryName(String countryName){
        this.countryName = countryName;    }

    /**
     * getter
     */
    public int getCountryID(){
        return countryID;    }

    /**
     * getter
     */
    public String getCountryName(){
        return countryName;    }

    /**
     * Lambda Expression 6: cycles through the list of countries and adds the names to the countryNames list
     * @param countries countries
     * @return country names
     */
    public static ObservableList<String> countryNames(ObservableList<Countries> countries){
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        countries.forEach(c -> {
            countryNames.add(c.countryName);
        });
        return countryNames;
    }

    /**
     * gets country id
     * @param countryName country name
     * @return country id
     */
    public static int getCountryID(String countryName){
        for(int i =0; i < DBCountries.getCountryList().size(); i++){
            if(countryName.equals(DBCountries.getCountryList().get(i).getCountryName())){
                return DBCountries.getCountryList().get(i).getCountryID();
            }
        }
        return -1;
    }
}

