package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class LogIn extends AnchorPane {

    private Controller pController;
    DB db = DB.getInstance();

    // Text fields for logIn view
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField postAddressTextField;

    @FXML private AnchorPane nameHide;
    @FXML private AnchorPane lastHide;
    @FXML private AnchorPane adressHide;
    @FXML private AnchorPane postNumberHide;
    @FXML private AnchorPane postAdressHide;

    @FXML private TextField mailTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField mobileTextField;

    @FXML private Button nextButton;

    @FXML public void updateNextButton(){
        boolean flag = false;

        if (isTextFieldEmpty(firstNameTextField)) { flag = true; }
        if (isTextFieldEmpty(lastNameTextField)) { flag = true; }
        if (isTextFieldEmpty(addressTextField)) { flag = true; }
        if (isTextFieldEmpty(postAddressTextField)) { flag = true; }
        if (isPostalCodeWrong()) { flag = true; }

        if (flag){
            // Knapp Ska vara grå
            nextButton.setId("white_button_disabled");
        } else{
            // Knapp ska ha färg
            nextButton.setId("white_button");
        }

    }

    private static boolean missingField = false;

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
        setupTextField();
    }

    private boolean isTextFieldEmpty(TextField field){
        return field.getText().matches("");
    }

    private boolean isPostalCodeWrong(){
        return postalCodeTextField.getText().length() != 5;
    }

    private void setupTextField(){

        postalCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                postalCodeTextField.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 5){
                postalCodeTextField.setText(oldValue);
            }
        });

        postalCodeTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (isPostalCodeWrong()){
                    postNumberHide.setId("hide_white_textArea_wrong");
                } else {
                    postNumberHide.setId("hide_white_textArea");
                }
            }
        });

        firstNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (isTextFieldEmpty(firstNameTextField)){
                    nameHide.setId("hide_white_textArea_wrong");
                } else {
                    nameHide.setId("hide_white_textArea");
                }
            }

        });

        lastNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (isTextFieldEmpty(lastNameTextField)){
                    lastHide.setId("hide_white_textArea_wrong");
                } else {
                    lastHide.setId("hide_white_textArea");
                }
            }

        });

        addressTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (isTextFieldEmpty(addressTextField)){
                    adressHide.setId("hide_white_textArea_wrong");
                } else {
                    adressHide.setId("hide_white_textArea");
                }
            }

        });

        postAddressTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (isTextFieldEmpty(postAddressTextField)){
                    postAdressHide.setId("hide_white_textArea_wrong");
                } else {
                    postAdressHide.setId("hide_white_textArea");
                }
            }

        });
    }

    @FXML
    private void logInPressed(ActionEvent event) {

        try {
            isFilledIn(firstNameTextField.getText());
            db.setFirstName(firstNameTextField.getText());
        } catch (IOException ioe) {
            firstNameTextField.setPromptText("First name must be filled in.");
            System.out.println("First name must be filled in.");
            missingField = true;
            firstNameTextField.setId("red_button");             //An example of how to handle missing field.
        }

        try {
            isFilledIn(lastNameTextField.getText());
            db.setLastName(lastNameTextField.getText());
        } catch(IOException ioe) {
            System.out.println("Last name must be filled in.");
            missingField = true;
        }

        try {
            isFilledIn(addressTextField.getText());
            db.setAddress(addressTextField.getText());
        } catch(IOException ioe) {
            System.out.println("Address must be filled in.");
            missingField = true;
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
            else {
                System.out.println("Invalid post code");
                missingField = true;
            }
        } catch(NullPointerException npe) {
            System.out.println("Postal code must be filled in.");
        }

        try {
            isFilledIn(postAddressTextField.getText());
            db.setPostAddress(postAddressTextField.getText());
        } catch(IOException ioe) {
            System.out.println("City must be filled in.");
            missingField = true;
        }

        //Even if these fields are left empty they don't produce a NullPointerException.
        db.setEMail(mailTextField.getText());
        db.setPhoneNumber(telephoneTextField.getText());
        db.setMobileNumber(mobileTextField.getText());

        // Byter till main view
        if(!missingField) { pController.setupShop(); }

        missingField = false;
    }

    private static void isFilledIn(String field) throws IOException {
        if(field.length() < 1) {
            throw new IOException();
        }
    }
}
