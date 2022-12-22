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
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;


public class loginController {

    @FXML

    public TextField username;
    public TextField password;
    public Text language;
    public Button exit;
    public Button login;
    public Text usernameLabel;
    public Text passwordLabel;
    ObservableList<String> languages  = FXCollections.observableArrayList("English", "French");
    @FXML
    private void initialize(ResourceBundle rb, URL url){
    ResourceBundle resourceBundle = ResourceBundle.getBundle("main/Lang", Locale.getDefault());
        if(Main.locale == "US" || Main.locale == "FR"){
            ZoneId zoneId = ZoneId.systemDefault();
            passwordLabel.setText(resourceBundle.getString("passwordLabel"));
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