package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class logIn extends AnchorPane {
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

    //@FXML private Button nextButton;

    DB db = DB.getInstance();


    @FXML
    public void logInPressed(ActionEvent event){            // Metoden måste testa så att input är valid och skicka vidare till nästa sida

        String name = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();

        System.out.println(name);
        System.out.println(lastName);


        try {
            db.setFirstName(firstNameTextField.getText());
        } catch(NullPointerException npe) {
            System.out.println("First name must be filled in.");
        }

        try {
            db.setLastName(lastNameTextField.getText());
        } catch(NullPointerException npe) {
            System.out.println("Last name must be filled in.");
        }

        try {
            db.setLastName(lastNameTextField.getText());
        } catch(NullPointerException npe) {
            System.out.println("Last name must be filled in.");
        }

        try {
            db.setAddress(addressTextField.getText());
        } catch(NullPointerException npe) {
            System.out.println("Address must be filled in.");
        }

        try {
            StringBuilder sb = new StringBuilder();
            for(char c : postalCodeTextField.getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if(sb.length() == 5) {
                db.setPostCode(sb.toString());
                System.out.println("Postal code successfully set");
            }
            else
                System.out.println("Invalid post code");

        } catch(NullPointerException npe) {
            System.out.println("Postal code must be filled in.");
        }

        try {
            db.setPostAddress(cityTextField.getText());
        } catch(NullPointerException npe) {
            System.out.println("City must be filled in.");
        }

        try {
            db.setAddress(addressTextField.getText());
        } catch(NullPointerException npe) {
            System.out.println("Address must be filled in.");
        }

        try {
            db.setEMail(mailTextField.getText());
        } catch(NullPointerException npe) {
            //Do nothing
        }

        try {
            db.setPhoneNumber(telephoneTextField.getText());
        } catch(NullPointerException npe) {
            //Do nothing
        }

        try {
            db.setMobileNumber(mobileTextField.getText());
        } catch(NullPointerException npe) {
            //Do nothing
        }

        // Byter till main view
        pController.setupShop();

    }

    public logIn(Controller pController){
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
