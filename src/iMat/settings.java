package iMat;

import com.sun.javafx.scene.control.behavior.MenuButtonBehavior;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class settings extends AnchorPane {

    private final Controller pController;
    DB db = DB.getInstance();

    //Buttons for window default
    @FXML
    private Button closeAccount;
    @FXML
    private Button settings1;
    @FXML
    private Button settings2;
    @FXML
    private Button save1;
    @FXML
    private Button abort1;
    @FXML
    private Button save2;
    @FXML
    private Button abort2;
    @FXML
    private Button newcard;
    @FXML
    private Button visa;
    @FXML
    private MenuButton typeofcard;
    @FXML
    private MenuItem mastercardtype;


    @FXML
    private ImageView mastercardpic;
    @FXML
    private ImageView visapic;

    //Anchor panes
    @FXML
    private AnchorPane settingsdefault;
    @FXML
    private AnchorPane settingschanged;
    @FXML
    private AnchorPane paymentdefault;
    @FXML
    private AnchorPane paymentchanged;

    //Textfields for payment window default
    @FXML
    private TextField cardnumber;
    @FXML
    private TextField cardholername;
    @FXML
    private TextField validmonth;
    @FXML
    private TextField validyear;
    @FXML
    private TextField cvc;

    //Textfields for payment window changed
    @FXML
    private TextField cardnumber1;
    @FXML
    private TextField cardholername1;
    @FXML
    private TextField validmonth1;
    @FXML
    private TextField validyear1;
    @FXML
    private TextField cvc1;


    //Textfields for account window default

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField postAddressTextField;
    @FXML
    private TextField mailTextField;
    @FXML
    private TextField telephoneTextField;
    @FXML
    private TextField mobileTextField;

    //Textfields for account window changed

    @FXML
    private TextField firstNameTextField1;
    @FXML
    private TextField lastNameTextField1;
    @FXML
    private TextField addressTextField1;
    @FXML
    private TextField postalCodeTextField1;
    @FXML
    private TextField postAddressTextField1;
    @FXML
    private TextField mailTextField1;
    @FXML
    private TextField telephoneTextField1;
    @FXML
    private TextField mobileTextField1;


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

        setupTextField();

    }

    @FXML
    void visatypepressed(ActionEvent event) {

        db.setCardType("Visa");


        typeofcard.setGraphic(visapic);
        typeofcard.setText("Visa");
        mastercardtype.setGraphic(mastercardpic);
        mastercardtype.setText("Mastercard");
    }

    @FXML
    void mastercardtypepressed(ActionEvent event) {


        if (mastercardtype.getGraphic() == visapic) {
            db.setCardType("Visa");
            mastercardtype.setGraphic(mastercardpic);
            mastercardtype.setText("Mastercard");

            typeofcard.setGraphic(visapic);
            typeofcard.setText("Visa");

        } else {
            db.setCardType("Mastercard");
            mastercardtype.setGraphic(visapic);
            mastercardtype.setText("Visa");
            typeofcard.setGraphic(mastercardpic);
            typeofcard.setText("Mastercard");

        }

    }

    @FXML
    public void change1pressed(ActionEvent event) {

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
    public void change2pressed(ActionEvent event) {

        cardholername1.setText(cardholername.getText());
        cardnumber1.setText(cardnumber.getText());
        validmonth1.setText(validmonth.getText());
        validyear1.setText(validyear.getText());
        cvc1.setText(cvc.getText());

        paymentchanged.toFront();

    }

    @FXML
    public void save2pressed(ActionEvent event) {

        savedPaymentValid();
        updatepayment();
        newcard.setVisible(false);

    }

    @FXML
    public void newcardpressed(ActionEvent event) {


        paymentchanged.toFront();

    }

    @FXML
    public void abort1paymentpressed(ActionEvent event) {


        if (db.isFirstRun()) {

            newcard.toFront();
            paymentdefault.toFront();

        } else
            newcard.toBack();
        paymentdefault.toFront();

    }

    @FXML
    public void abort1pressed(ActionEvent event) {


        settingsdefault.toFront();

    }

    @FXML
    public void save1pressed(ActionEvent event) {

        savedSettingsValid();
        updatesettings();

    }

    @FXML
    public void closeSettings(ActionEvent event) {
        pController.closeOverlay();
        settingsdefault.toFront();
        paymentdefault.toFront();
    }

    public void updatepayment() {

        cardnumber.setText(cardnumber1.getText());
        db.setCardNumber(cardnumber1.getText());

        cardholername.setText(cardholername1.getText());
        db.setHoldersName(cardnumber1.getText());


        try {
            db.setValidMonth(Integer.parseInt(validmonth1.getText()));
            validmonth.setText(validmonth1.getText());
        } catch (NumberFormatException e) {
            validmonth1.setId("red_button");
        }

        try {
            db.setValidYear(Integer.parseInt(validyear1.getText()));
            validyear.setText(validyear1.getText());
        } catch (NumberFormatException e) {
            validyear1.setId("red_button");
        }

        try {
            db.setVerificationCode(Integer.parseInt(cvc1.getText()));
            cvc.setText(cvc1.getText());
        } catch (NumberFormatException e) {
            validmonth1.setId("red_button");
        }


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


    public void paymentSettingsFirstRun() {
        if (db.isFirstRun()) {
            cardnumber.setText("");
            cardholername.setText("");
            validmonth.setText("");
            validyear.setText("");
            cvc.setText("");
            newcard.toFront();

        }
    }

    public void setupPayment() {


        //Fill payment window textfields with databse information

        cardnumber.setText(db.getCardNumber());
        cardholername.setText(db.getHoldersName());


        validmonth.setText(String.valueOf(db.getValidMonth()));
        validyear.setText(String.valueOf(db.getValidYear()));
        cvc.setText(String.valueOf(db.getVerificationCode()));


        try {
            isFilledIn(cardnumber.getText());
            isFilledIn(cardholername.getText());
            isFilledIn(validmonth.getText());
            isFilledIn(validyear.getText());
            isFilledIn(cvc.getText());


        } catch (IOException ioe) {

            newcard.toFront();
            missingField = true;
        }

        if (!missingField)
            paymentdefault.toFront();
        settings2.toFront();

        missingField = false;

    }

    public void setupSettings() {

        //Fill settings window textfields with databse information

        firstNameTextField.setText(db.getFirstName());
        lastNameTextField.setText(db.getLastName());
        addressTextField.setText(db.getAddress());
        postAddressTextField.setText(db.getPostAddress());
        postalCodeTextField.setText(db.getPostCode());
        mailTextField.setText(db.getEmail());
        telephoneTextField.setText(db.getPhoneNumber());
        mobileTextField.setText(db.getMobilePhoneNumber());


    }

    private void savedPaymentValid() {

        try {
            StringBuilder sb = new StringBuilder();
            for (char c : cardnumber1.getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if (sb.length() == 16) {

            } else {
                cardnumber1.setPromptText("Felaktigt kortnummer");
                missingField = true;
                cardnumber1.setId("red_button");
            }
        } catch (NullPointerException npe) {
            cardnumber1.setPromptText("Kortnummer behöver fyllas i.");
        }


        try {
            isFilledIn(cardholername1.getText());

        } catch (IOException ioe) {
            cardholername1.setPromptText("Var vänlig fyll i ditt namn");
            missingField = true;
            cardholername1.setId("red_button");
        }

        try {
            StringBuilder sb = new StringBuilder();
            for (char c : validmonth1.getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if (sb.length() == 2) {

            } else {
                System.out.println("Felaktigt månad");
                missingField = true;
                validmonth1.setId("red_button");
            }
        } catch (NullPointerException npe) {
            validmonth1.setPromptText("Månaden behöver fyllas i");
        }

        try {
            StringBuilder sb = new StringBuilder();
            for (char c : validyear1.getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if (sb.length() == 2) {

            } else {
                System.out.println("Felaktigt månad");
                missingField = true;
                validyear1.setId("red_button");
            }
        } catch (NullPointerException npe) {
            validyear1.setPromptText("Året behöver fyllas i");
        }


        try {
            StringBuilder sb = new StringBuilder();
            for (char c : cvc1.getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if (sb.length() == 3) {

            } else {
                System.out.println("Felaktigt månad");
                missingField = true;
                cvc1.setId("red_button");
            }
        } catch (NullPointerException npe) {
            cvc1.setPromptText("CVC behöver fyllas i");
        }


        if (!missingField) {
            paymentdefault.toFront();

        }
        missingField = false;

    }

    private void savedSettingsValid() {

        try {
            isFilledIn(firstNameTextField1.getText());

        } catch (IOException ioe) {
            firstNameTextField1.setPromptText("First name must be filled in.");
            missingField = true;
            firstNameTextField1.setId("red_button");             //An example of how to handle missing field.
        }

        try {
            isFilledIn(lastNameTextField1.getText());

        } catch (IOException ioe) {

            missingField = true;
            lastNameTextField1.setId("red_button");
        }

        try {
            isFilledIn(addressTextField1.getText());

        } catch (IOException ioe) {
            System.out.println("Address must be filled in.");
            missingField = true;
            addressTextField1.setId("red_button");
        }

        try {
            StringBuilder sb = new StringBuilder();
            for (char c : postalCodeTextField1.getText().toCharArray()) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if (sb.length() == 5) {
                System.out.println("Postal code successfully set");
            } else {
                System.out.println("Invalid post code");
                missingField = true;
                postalCodeTextField1.setId("red_button");
            }
        } catch (NullPointerException npe) {
            System.out.println("Postal code must be filled in.");
        }

        try {
            isFilledIn(postAddressTextField1.getText());

        } catch (IOException ioe) {
            System.out.println("City must be filled in.");
            postAddressTextField1.setId("red_button");
            missingField = true;
        }


        if (!missingField)
            settingsdefault.toFront();

        missingField = false;


    }

    private static void isFilledIn(String field) throws IOException {
        if (field.length() < 1) {
            throw new IOException();
        }
    }

    private void setupTextField(){

        // force the field to be numeric only and updates the amount in shopping cart
        cvc1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cvc1.setText(newValue.replaceAll("[^\\dd]", ""));

            } else if (newValue.matches("0")){

                cvc1.setText("1");

            } else if (!newValue.matches("")){

                if(Integer.parseInt(newValue) >= 100){
                    cvc1.setText("" + Integer.parseInt(newValue)/10);
                }

            }
        });

        // Clears the field when focused and sets a default value if the field is empty when focus is lost
        cvc1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {
                // Focus gained
                cvc1.setText("");

            } else {
                // Focus lost
                if(cvc1.getText().matches("")){
                    cvc1.setText("1");
                }
            }
        });

    }





}
