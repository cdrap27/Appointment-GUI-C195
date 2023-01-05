package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Contacts {
    private int contactID;
    private String contactName;
    private String contactEmail;

    /**
     * creates contact class
     * @param contactID contact id
     * @param contactName contact name
     * @param contactEmail contact email
     */
    public Contacts(int contactID, String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * setters
     */
    public void setContactID(int contactID){
        this.contactID = contactID;    }

    public void setContactName(String contactName){
        this.contactName = contactName;    }

    public void setContactEmail(String contactEmail){
        this.contactEmail = contactEmail;
    }

    /**
     * getters
     */
    public int getContactID(){
        return contactID;    }

    public String getContactName(){
        return contactName;    }

    public String getContactEmail(){
        return contactEmail;
    }

    /**
     * gets contact names using a lambda expression
     * @param contacts contact list
     * @return contact names
     */
    public static ObservableList<String> contactNames(ObservableList<Contacts> contacts){
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        //lambda expression 2, cycles through the contact list to retrieve contact names.
        contacts.forEach(c ->{
            String nameCustomer = c.getContactName();
            contactNames.add(nameCustomer);

        });
        return contactNames;
    }

    public static int findContactID(String contactName){
        for(int i = 0; i < DAO.DBContact.getContactList().size(); i ++){
            if(contactName.equals(DAO.DBContact.getContactList().get(i).getContactName())){
                return DAO.DBContact.getContactList().get(i).getContactID();
            }
        }
        return -1;
    }

    public static int getID(int contact){
        for(int i = 0; i < DAO.DBContact.getContactList().size(); i++){
            if(contact == DAO.DBContact.getContactList().get(i).getContactID()){
                return i;
            }
        }
        return -1;
    }
}

