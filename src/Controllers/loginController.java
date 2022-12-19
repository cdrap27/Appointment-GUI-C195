package Controllers;

import DAO.userAccess;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class loginController {

    @FXML

    public TextField username;
    public TextField password;
    public Text language;
    ObservableList<String> languages  = FXCollections.observableArrayList("English", "French");
    @FXML
    private void initialize(){
        if(Main.locale == "US"){
            language.setText("English");
        }
        else
            language.setText(Main.locale);
    }


    public void onLogin(ActionEvent actionEvent) {
        String user = username.getText();
        String pass = password.getText();
        if(userAccess.userFound(user, pass) == true){
            System.out.println("User Found!");
        }
        else
            System.out.println("user not found");


    }
}