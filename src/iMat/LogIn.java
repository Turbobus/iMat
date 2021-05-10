package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogIn extends AnchorPane {
    private Controller pController;


// Text fields for logIn view
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField mailTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField mobileTextField;

    @FXML private Button nextButton;


    @FXML
    public void logInPressed(ActionEvent event){            // Metoden måste testa så att input är valid och skicka vidare till nästa sida

        String name = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        System.out.println(name);
        System.out.println(lastName);

        // Byter till main view

    }

    public LogIn(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
       }



        this.pController = pController;
    }

}
