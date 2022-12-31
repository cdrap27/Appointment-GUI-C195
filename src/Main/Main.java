package Main;

import Database.DBConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application{
    public static String locale;

    @Override
    public void start(Stage primaryStage) throws Exception{
        DAO.DBCustomers.setCustomerList();
        DAO.DBAppointment.setAppointmentList();
        locale = Locale.getDefault().getCountry();

        Parent root = FXMLLoader.load(getClass().getResource("../Views/login.fxml"));
        primaryStage.setTitle("Login");
        if (locale == "fr")
        primaryStage.setTitle("Connectez-vous");
        primaryStage.setScene(new Scene(root, 518, 247));
        primaryStage.show();
    }



    public static void main(String[] args) {
        DBConnection.startConnection();
        launch(args);

        DBConnection.closeConnection();
    }
}
