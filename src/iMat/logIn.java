package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class logIn extends AnchorPane {

    private Controller pController;
    DB db = DB.getInstance();

    // Text fields for logIn view
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField postAddressTextField;

    @FXML private TextField mailTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField mobileTextField;

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

    @FXML
    private void logInPressed(ActionEvent event) {

        try {
            isFilledIn(firstNameTextField.getText());
            db.setFirstName(firstNameTextField.getText());
        } catch (IOException ioe) {
            System.out.println("First name must be filled in.");
        }

        try {
            isFilledIn(lastNameTextField.getText());
            db.setLastName(lastNameTextField.getText());
        } catch(IOException ioe) {
            System.out.println("Last name must be filled in.");
        }

        try {
            isFilledIn(addressTextField.getText());
            db.setAddress(addressTextField.getText());
        } catch(IOException ioe) {
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
            isFilledIn(postAddressTextField.getText());
            db.setPostAddress(postAddressTextField.getText());
        } catch(IOException ioe) {
            System.out.println("City must be filled in.");
        }

        //Even if these fields are left empty they don't produce a NullPointerException.
        db.setEMail(mailTextField.getText());
        db.setPhoneNumber(telephoneTextField.getText());
        db.setMobileNumber(mobileTextField.getText());

        // Byter till main view

    }

    private static void isFilledIn(String field) throws IOException {
        if(field.length() < 1) {
            throw new IOException();
        }
    }
}
