package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class Controller {

    @FXML
    public ChoiceBox<String> language;
    public TextField username;
    public TextField password;
    ObservableList<String> languages  = FXCollections.observableArrayList("English", "French");
    @FXML
    private void initialize(){
        language.setItems(languages);
    }


    public void onLogin(ActionEvent actionEvent) {
        String user = username.getText();
        String pass = password.getText();


    }
}
