package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * creates contact class
 */
public class Contacts {

    /**
     * reference for contacts class
     */
    private int contactID;
    /**
     * reference for contacts class
     */
    private String contactName;
    /**
     * reference for contacts class
     */
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
     * setter
     */
    public void setContactID(int contactID){
        this.contactID = contactID;    }

    /**
     * setter
     */
    public void setContactName(String contactName){
        this.contactName = contactName;    }

    /**
     * setter
     */
    public void setContactEmail(String contactEmail){
        this.contactEmail = contactEmail;
    }

    /**
     * getter
     */
    public int getContactID(){
        return contactID;    }

    /**
     * getter
     */
    public String getContactName(){
        return contactName;    }

    /**
     * getter
     */
    public String getContactEmail(){
        return contactEmail;
    }

    /**
     * Lambda Expression #5: cycles through the contact list to retrieve the contact names and add them to the
     * contactNames list
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

    /**
     * finds the contact id for a given contact
     * @param contactName contact name
     * @return contact id
     */
    public static int findContactID(String contactName){
        for(int i = 0; i < DAO.DBContact.getContactList().size(); i ++){
            if(contactName.equals(DAO.DBContact.getContactList().get(i).getContactName())){
                return DAO.DBContact.getContactList().get(i).getContactID();
            }
        }
        return -1;
    }

    /**
     * finds the position where the contact is located
     * @param contact contact
     * @return contact position
     */
    public static int getID(int contact){
        for(int i = 0; i < DAO.DBContact.getContactList().size(); i++){
            if(contact == DAO.DBContact.getContactList().get(i).getContactID()){
                return i;
            }
        }
        return -1;
    }
}

