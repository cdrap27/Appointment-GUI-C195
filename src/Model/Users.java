package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Users {
    private int userID;
    private String userName;
    private String userPassword;


    public Users(int userID, String userName, String userPassword){
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * setters
     */
    public void setUserID(int userID){
        this.userID = userID;    }

    public void setUserName(String userName){
        this.userName = userName;    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * getters
     */
    public int getUserID(){
        return userID;    }

    public String getUserName(){
        return userName;    }

    public String getUserPassword(){
        return userPassword;
    }

    /**
     * gets user names using a lambda expression from the user list
     * @param users user list
     * @return user names
     */
    public static ObservableList<String> userNames(ObservableList<Users> users){
        ObservableList<String> userNames = FXCollections.observableArrayList();
        //lambda expression 2, cycles through the contact list to retrieve contact names.
        users.forEach(c ->{
            String nameUser = c.getUserName();
            userNames.add(nameUser);

        });
        return userNames;
    }

    /**
     * finds a user id from a user name
     * @param userName user name
     * @return id
     */
    public static int findUserID(String userName){
        for(int i = 0; i < DAO.DBUsers.getUserList().size(); i ++){
            if(userName.equals(DAO.DBUsers.getUserList().get(i).getUserName())){
                return DAO.DBUsers.getUserList().get(i).getUserID();
            }
        }
        return -1;
    }

    /**
     * finds the position in the list where the user is
     * @param user user
     * @return user position
     */
    public static int getUser(int user){
        for(int i = 0; i < DAO.DBUsers.getUserList().size(); i++){
            if(user == DAO.DBUsers.getUserList().get(i).getUserID()){
                return i;
            }
        }
    return -1;
    }
}

