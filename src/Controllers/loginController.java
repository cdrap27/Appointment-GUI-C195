package Controllers;

import DAO.userAccess;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;


public class loginController {

    @FXML

    public TextField username;
    public TextField password;
    public Text language;
    public Button exit;
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
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User not found");
            alert.setContentText("User not found");
            alert.showAndWait();
        }


    }

    public void onExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit?");
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
            
        }
    }
}