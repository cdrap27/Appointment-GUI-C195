package Main;

import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application{
    public static String locale;

    @Override
    public void start(Stage primaryStage) throws Exception{
        locale = Locale.getDefault().getCountry();
        System.out.println("" + locale);
        Parent root = FXMLLoader.load(getClass().getResource("../Views/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }



    public static void main(String[] args) {
        DBConnection.startConnection();
        launch(args);

        DBConnection.closeConnection();
    }
}
