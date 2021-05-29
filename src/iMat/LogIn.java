package iMat;

import javafx.application.Platform;
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

    private boolean isFieldsRight = true;

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

    public void updateNextButton(){
        boolean flag = false;

        if (isTextFieldEmpty(firstNameTextField)) { flag = true; }
        if (isTextFieldEmpty(lastNameTextField)) { flag = true; }
        if (isTextFieldEmpty(addressTextField)) { flag = true; }
        if (isTextFieldEmpty(postAddressTextField)) { flag = true; }
        if (isPostalCodeWrong()) { flag = true; }

        if (flag){
            // Knapp Ska vara grå
            nextButton.setId("white_button_disabled");
            isFieldsRight = false;
        } else{
            // Knapp ska ha färg
            nextButton.setId("white_button");
            isFieldsRight = true;
        }

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
            updateNextButton();
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

        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateNextButton();
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

        lastNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateNextButton();
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

        addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateNextButton();
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

        postAddressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateNextButton();
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

        mailTextField.focusedProperty().addListener((observable, oldValue, newValue) -> { updateNextButton(); });

        telephoneTextField.focusedProperty().addListener((observable, oldValue, newValue) -> { updateNextButton(); });

        mobileTextField.focusedProperty().addListener((observable, oldValue, newValue) -> { updateNextButton(); });
    }

    @FXML
    private void logInPressed(ActionEvent event) {
        if (isFieldsRight){
            db.setFirstName(firstNameTextField.getText());
            db.setLastName(lastNameTextField.getText());
            db.setAddress(addressTextField.getText());
            db.setPostCode(postalCodeTextField.getText());
            db.setPostAddress(postAddressTextField.getText());
            db.setEMail(mailTextField.getText());
            db.setPhoneNumber(telephoneTextField.getText());
            db.setMobileNumber(mobileTextField.getText());
            pController.setupShop();
        }
    }

    @FXML private void toStartUp() { pController.setupStartUp(); }

    private static void isFilledIn(String field) throws IOException {
        if(field.length() < 1) {
            throw new IOException();
        }
    }

    public void setFirstNameFocus() { Platform.runLater(()->firstNameTextField.requestFocus()); }
}
