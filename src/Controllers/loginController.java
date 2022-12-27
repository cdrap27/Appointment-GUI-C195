package Controllers;

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
    ObservableList<String> languages  = FXCollections.observableArrayList("English", "French");
    @FXML
    private void initialize(){
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


           }
    }


    public void onLogin(ActionEvent actionEvent) throws IOException {
        String user = username.getText();
        String pass = password.getText();

        if(userAccess.userFound(user, pass) == true){
            Stage dash = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../Views/dashboard.fxml"));
            dash.setTitle("Dashboard");

            dash.setScene(new Scene(root, 1055, 699));
            dash.show();
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