package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class settings extends AnchorPane {

    private final Controller pController;
    DB db = DB.getInstance();

    //Buttons for window default
    @FXML private Button closeAccount;
    @FXML private Button settings1;
    @FXML private Button settings2;
    @FXML private Button save1;
    @FXML private Button abort1;
    @FXML private Button save2;
    @FXML private Button abort2;
    @FXML private Button newcard;
    @FXML private MenuButton typeofcard;





    //Anchor panes
    @FXML private AnchorPane settingsdefault;
    @FXML private AnchorPane settingschanged;
    @FXML private AnchorPane paymentdefault;
    @FXML private AnchorPane paymentchanged;

    //Textfields for payment window default
    @FXML private TextField cardnumber;
    @FXML private TextField cardholername;
    @FXML private TextField validityperiod;
    @FXML private TextField cvc;

    //Textfields for payment window changed
    @FXML private TextField cardnumber1;
    @FXML private TextField cardholername1;
    @FXML private TextField validityperiod1;
    @FXML private TextField cvc1;


    //Textfields for account window default

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField postAddressTextField;
    @FXML private TextField mailTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField mobileTextField;

    //Textfields for account window changed

    @FXML private TextField firstNameTextField1;
    @FXML private TextField lastNameTextField1;
    @FXML private TextField addressTextField1;
    @FXML private TextField postalCodeTextField1;
    @FXML private TextField postAddressTextField1;
    @FXML private TextField mailTextField1;
    @FXML private TextField telephoneTextField1;
    @FXML private TextField mobileTextField1;


    private static boolean missingField = false;

    public settings(Controller pController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        settingsdefault.toFront();
        setupSettings();
    }

    @FXML
    public void change1pressed(ActionEvent event){

        settingschanged.toFront();

        firstNameTextField1.setText(db.getFirstName());
        lastNameTextField1.setText(db.getLastName());
        addressTextField1.setText(db.getAddress());
        postalCodeTextField1.setText(db.getPostCode());
        postAddressTextField1.setText(db.getPostAddress());
        mailTextField1.setText(db.getEmail());
        telephoneTextField1.setText(db.getPhoneNumber());
        mobileTextField1.setText(db.getMobilePhoneNumber());
    }



    @FXML
    public void change2pressed(ActionEvent event){

        paymentchanged.toFront();

    }

    @FXML
    public void abort1pressed(ActionEvent event){

        settingsdefault.toFront();

    }
    @FXML
    public void save1pressed(ActionEvent event){

        updatesettings();
        savedSettingsValid();
    }

    @FXML
    public void closeSettings(ActionEvent event){
        pController.closeOverlay();
        settingsdefault.toFront();
    }

    public void updatesettings() {

        firstNameTextField.setText(firstNameTextField1.getText());
        db.setFirstName(firstNameTextField1.getText());

        lastNameTextField.setText(lastNameTextField1.getText());
        db.setLastName(lastNameTextField1.getText());

        addressTextField.setText(addressTextField1.getText());
        db.setAddress(addressTextField1.getText());

        postAddressTextField.setText(postAddressTextField1.getText());
        db.setPostAddress(postAddressTextField1.getText());

        postalCodeTextField.setText(postalCodeTextField1.getText());
        db.setPostCode(postalCodeTextField1.getText());

        mailTextField.setText(mailTextField1.getText());
        db.setEMail(mailTextField1.getText());

        telephoneTextField.setText(telephoneTextField1.getText());
        db.setPhoneNumber(telephoneTextField1.getText());

        mobileTextField.setText(mobileTextField1.getText());
        db.setMobileNumber(mobileTextField1.getText());
    }



    public void setupSettings(){

        firstNameTextField.setText(db.getFirstName());
        lastNameTextField.setText(db.getLastName());
        addressTextField.setText(db.getAddress());
        postAddressTextField.setText(db.getPostAddress());
        postalCodeTextField.setText(db.getPostCode());
        mailTextField.setText(db.getEmail());
        telephoneTextField.setText(db.getPhoneNumber());
        mobileTextField.setText(db.getMobilePhoneNumber());


        try {
            isFilledIn(cardnumber.getText());
            isFilledIn(cardholername.getText());
            isFilledIn(validityperiod.getText());
            isFilledIn(cvc.getText());

        } catch (IOException ioe) {
            newcard.toFront();
            typeofcard.toFront();
        }
        settings2.toFront();

    }

    @FXML
    private void savedSettingsValid() {

        try {
            isFilledIn(firstNameTextField1.getText());

        } catch (IOException ioe) {
            firstNameTextField1.setPromptText("First name must be filled in.");
            System.out.println("First name must be filled in.");
            missingField = true;
            firstNameTextField1.setId("red_button");             //An example of how to handle missing field.
        }

        try {
            isFilledIn(lastNameTextField1.getText());

        } catch(IOException ioe) {
            System.out.println("Last name must be filled in.");
            missingField = true;
            lastNameTextField1.setId("red_button");
        }

        try {
            isFilledIn(addressTextField1.getText());

        } catch(IOException ioe) {
            System.out.println("Address must be filled in.");
            missingField = true;
            addressTextField1.setId("red_button");
        }

        try {
            StringBuilder sb = new StringBuilder();
            for(char c : postalCodeTextField1.getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if(sb.length() == 5) {
                System.out.println("Postal code successfully set");
            }
            else {
                System.out.println("Invalid post code");
                missingField = true;
                postalCodeTextField1.setId("red_button");
            }
        } catch(NullPointerException npe) {
            System.out.println("Postal code must be filled in.");
        }

        try {
            isFilledIn(postAddressTextField1.getText());

        } catch(IOException ioe) {
            System.out.println("City must be filled in.");
            postAddressTextField1.setId("red_button");
            missingField = true;
        }


        if(!missingField)
            settingsdefault.toFront();

        missingField = false;




    }

    private static void isFilledIn(String field) throws IOException {
        if(field.length() < 1) {
            throw new IOException();
        }
    }


}
