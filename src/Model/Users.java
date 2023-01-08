package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * creates users class
 */
public class Users {
    /**
     * reference for the users class
     */
    private int userID;
    /**
     * reference for the users class
     */
    private String userName;
    /**
     * reference for the users class
     */
    private String userPassword;

    /**
     * creates the user
     * @param userID user id
     * @param userName user name
     * @param userPassword user password
     */
    public Users(int userID, String userName, String userPassword){
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * setter
     */
    public void setUserID(int userID){
        this.userID = userID;    }

    /**
     * setter
     */
    public void setUserName(String userName){
        this.userName = userName;    }

    /**
     * setter
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * getter
     */
    public int getUserID(){
        return userID;    }

    /**
     * getter
     */
    public String getUserName(){
        return userName;    }

    /**
     * getter
     */
    public String getUserPassword(){
        return userPassword;
    }

    /**
     * Lambda Expression 10: cycles through the users list and adds the usernames to the usernames list
     * @param users user list
     * @return user names
     */
    public static ObservableList<String> userNames(ObservableList<Users> users){
        ObservableList<String> userNames = FXCollections.observableArrayList();
        //lambda expression cycles through the contact list to retrieve contact names.
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

