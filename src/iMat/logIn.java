package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.Customer;

import java.net.URL;
import java.util.ResourceBundle;

public class logIn implements Initializable {
//    private final Controller parentController;
    private Customer customer;

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

    }

//    public logIn(Controller parentController) {
//        this.parentController = parentController;
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            customer.setFirstName(newValue);
//            System.out.println(newValue);
//        });


    }
}
