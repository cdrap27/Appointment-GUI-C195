package Controllers;

import DAO.DBAppointment;
import DAO.userAccess;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
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
    public Text languageLabel;
    public Text locationText;
    public Text locationTextResult;
    ObservableList<String> languages  = FXCollections.observableArrayList("English", "French");
    @FXML
    private void initialize(){
        locationTextResult.setText(Locale.getDefault().getCountry());
           if(Locale.getDefault().getLanguage() == "en" ){
            System.out.println("working");
            language.setText("English");

        }
        else if(Locale.getDefault().getLanguage() == "fr") {
               language.setText("Français");
               login.setText("Connectez-vous");
               exit.setText("Sortie");
               usernameLabel.setText("Nom d’utilisateur");
               passwordLabel.setText("Mot de passe");
               languageLabel.setText("Langue");
               locationText.setText("Lieu");


           }
    }


    public void onLogin(ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();

        if(userAccess.userFound(user, pass) == true){
            //loads the dashboard
            Parent root = FXMLLoader.load(getClass().getResource("../Views/dashboard.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1055, 699);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.getScene().getWindow().centerOnScreen();
            stage.show();
            //alerts the user of upcoming appointments
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upcoming Appointments");
            alert.setHeaderText("Upcoming");
            //if no upcoming appointments, says no upcoming
            if(DBAppointment.upcomingAppointments().size() == 0)
                alert.setContentText("No upcoming Appointments");
            //if there is at least one upcoming appointment, display a message containing info on the upcoming appointment
            else{
                    alert.setContentText("Upcoming Appointment: " + DBAppointment.upcomingAppointments().get(0).getID() +
                            "\nTime: " + DBAppointment.upcomingAppointments().get(0).getStart());
                }
            Optional<ButtonType> result = alert.showAndWait();
            //if there is more than 1 upcoming appointment, displays an alert for each upcoming appointment
            if(DBAppointment.upcomingAppointments().size() > 1){
                for(int i = 1; i < DBAppointment.upcomingAppointments().size(); i++){
                     alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Upcoming Appointments");

                      alert.setHeaderText("Upcoming");
                      alert.setContentText("Upcoming Appointment: " + DBAppointment.upcomingAppointments().get(i).getID() +
                                "\nTime: " + DBAppointment.upcomingAppointments().get(i).getStart());
                      result = alert.showAndWait();
                }
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(Locale.getDefault().getLanguage() == "en")
            alert.setTitle("User not found");
            else if(Locale.getDefault().getLanguage() == "fr")
            alert.setTitle("Utilisateur introuvable");
            if(Locale.getDefault().getLanguage() == "en")
            alert.setContentText("User not found");
            else if (Locale.getDefault().getLanguage() == "fr")
            alert.setContentText("Utilisateur introuvable");
            alert.showAndWait();
        }


    }

    public void onExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(Locale.getDefault().getLanguage() == "en")
        alert.setTitle("Exit");
        else if (Locale.getDefault().getLanguage() == "fr")
        alert.setTitle("Sortie?");
        if(Locale.getDefault().getLanguage() == "en")
        alert.setHeaderText("Exit?");
        else if (Locale.getDefault().getLanguage() == "fr")
        alert.setHeaderText("Sortie?");
        if(Locale.getDefault().getLanguage() == "en")
        alert.setContentText("Exit?");
        else if (Locale.getDefault().getLanguage() == "fr")
        alert.setContentText("Sortie?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
            
        }
    }
}