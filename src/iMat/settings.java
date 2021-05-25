package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;

public class settings extends AnchorPane {

    private final Controller pController;
    DB db = DB.getInstance();
    private boolean[] isCorrectInformation = {false, false, false, false, false};



    private String cardType;

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
    private MenuItem mastercardtype;


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

    // Combobox items

    @FXML
    private ImageView mastercardpic;
    @FXML
    private ImageView visapic;



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


       cardType();
        setupPayment2();
        setupValidSettings();
        saveCardDetails();
        setupSettings();






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
        isCorrectInformation = new boolean[]{true, true, true, true, true};

        paymentchanged.toFront();
        cardType();

    }

    @FXML
    public void save2pressed(ActionEvent event) {

       if(isAllTrue(isCorrectInformation)) {
            updatepayment();
            cardType();
            newcard.setVisible(false);
            paymentdefault.toFront();
        }

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
        updateNextButton();
        if (isFieldsRight){
            updatesettings();
            settingsdefault.toFront();
        }


    }

    public void setupValidSettings () {

        firstNameTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue) {
                        // Focus lost
                        if (firstNameTextField1.getText().matches("")) {
                            firstNameTextField1.setId("blue_text_field_wrong_2");
                            isCorrectInformation[0] = false;
                        } else {
                            firstNameTextField1.setId("blue_text_field_2");
                            isCorrectInformation[0] = true;
                        }

                    }
                });

            lastNameTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

                if (!newValue) {
                    // Focus lost
                    if (lastNameTextField1.getText().matches("")) {
                        lastNameTextField1.setId("blue_text_field_wrong_2");
                        isCorrectInformation[1] = false;
                    } else {
                        lastNameTextField1.setId("blue_text_field_2");
                        isCorrectInformation[1] = true;
                    }

                }


            });

        addressTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (addressTextField1.getText().matches("")) {
                    addressTextField1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[2] = false;
                } else {
                    addressTextField1.setId("blue_text_field_2");
                    isCorrectInformation[2] = true;
                }

            }


        });

        postAddressTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (postAddressTextField1.getText().matches("")) {
                    postAddressTextField1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[3] = false;
                } else {
                    postAddressTextField1.setId("blue_text_field_2");
                    isCorrectInformation[3] = true;
                }

            }


        });

        postalCodeTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (postAddressTextField1.getText().matches("")) {
                    postAddressTextField1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[3] = false;
                } else {
                    postAddressTextField1.setId("blue_text_field_2");
                    isCorrectInformation[3] = true;
                }

            }


        });

        postalCodeTextField1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                postalCodeTextField1.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 5) {
                postalCodeTextField1.setText(oldValue);
            }
        });

        postalCodeTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if ( postalCodeTextField1.getText().length() != 5) {
                    postalCodeTextField1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[4] = false;
                } else {
                    postalCodeTextField1.setId("blue_text_field_2");
                    isCorrectInformation[4] = true;
                }

            }
        });



        }

    @FXML
    public void closeSettings(ActionEvent event) {
        pController.closeOverlay();
        settingsdefault.toFront();
        paymentdefault.toFront();
    }

    public void updateNextButton(){
        boolean flag = false;

        if (isTextFieldEmpty(firstNameTextField1)) { flag = true; }
        if (isTextFieldEmpty(lastNameTextField1)) { flag = true; }
        if (isTextFieldEmpty(addressTextField1)) { flag = true; }
        if (isTextFieldEmpty(postAddressTextField1)) { flag = true; }
        if (isPostalCodeWrong()) { flag = true; }

        // Knapp ska ha färg
        isFieldsRight = !flag;

    }

    private boolean isFieldsRight = true;

    private boolean isTextFieldEmpty(TextField field){
        return field.getText().matches("");
    }

    private boolean isPostalCodeWrong(){
        return postalCodeTextField1.getText().length() != 5;
    }

    public void updatepayment() {

        cardnumber.setText(cardnumber1.getText());
        cardholername.setText(cardholername1.getText());
        validmonth.setText(validmonth1.getText());
        validyear.setText(validyear1.getText());
        cvc.setText(cvc1.getText());


        db.setCardNumber(cardnumber1.getText());
        db.setHoldersName(cardholername1.getText());
        db.setValidMonth(Integer.parseInt(validmonth1.getText()));
        db.setValidYear(Integer.parseInt(validyear1.getText()));
        db.setVerificationCode(Integer.parseInt(cvc1.getText()));


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
        if (db.getCardNumber().matches("")) {
            cardnumber.setText("");
            cardholername.setText("");
            validmonth.setText("");
            validyear.setText("");
            cvc.setText("");
            db.setCardType("");
            newcard.toFront();
            paymentdefault.toFront();


        }
        else {
            settings2.toFront();
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

    public void cardType() {

           visapic.setVisible(false);
           mastercardpic.setVisible(false);

           if(db.getCardType().equals("Visa")) {
               visapic.setVisible(true);
           } else if (db.getCardType().equals("MasterCard")) {
               mastercardpic.setVisible(true);
           }

    }

    public void setupPayment2() {

        cardnumber1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardnumber1.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 16) {
                cardnumber1.setText(oldValue);
            }
        });

        cardnumber1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (cardnumber1.getText().length() != 16 || (cardnumber1.getText().charAt(0) != '4' && cardnumber1.getText().charAt(0) != '5')) {
                    cardnumber1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[0] = false;
                } else {
                    cardnumber1.setId("blue_text_field_2");
                    if (cardnumber1.getText().charAt(0) == '4') {
                          db.setCardType("Visa");
                       cardType = "Visa";
                    } else {
                        db.setCardType("MasterCard");
                        cardType = "MasterCard";


                        mastercardpic.setOpacity(100);
                    }
                    isCorrectInformation[0] = true;
                }

            }
        });

        cardholername1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (cardholername1.getText().matches("")) {
                    cardholername1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[1] = false;
                } else {
                    cardholername1.setId("blue_text_field_2");
                    isCorrectInformation[1] = true;
                }

            }
        });

        validmonth1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                validmonth1.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2) {
                validmonth1.setText(oldValue);
            }
        });

        validmonth1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if ( validmonth1.getText().matches("") ||  validmonth1.getText().matches("0") || Integer.parseInt( validmonth1.getText()) > 12) {
                    validmonth1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[2] = false;
                } else {
                    validmonth1.setId("blue_text_field_2");
                    isCorrectInformation[2] = true;
                }

            }
        });

        validyear1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                validyear1.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2) {
                validyear1.setText(oldValue);
            }

        });

        validyear1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (validyear1.getText().matches("") || Integer.parseInt(validyear1.getText()) < 20 || Integer.parseInt(validyear1.getText()) > 30) {
                    validyear1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[3] = false;
                } else {
                    validyear1.setId("blue_text_field_2");
                    isCorrectInformation[3] = true;
                }

            }
        });

        cvc1.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cvc1.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 3) {
                cvc1.setText(oldValue);
            }
        });

        cvc1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if ( cvc1.getText().length() != 3) {
                    cvc1.setId("blue_text_field_wrong_2");
                    isCorrectInformation[4] = false;
                } else {
                    cvc1.setId("blue_text_field_2");
                    isCorrectInformation[4] = true;
                }

            }
        });
    }

    private boolean isAllTrue(boolean[] array) {
        for(boolean b : array) if(!b) return false;
        return true;
    }

    @FXML public void saveCardDetails(){
        if (isAllTrue(isCorrectInformation)){
            db.setCardNumber(cardnumber1.getText());
            db.setHoldersName(cardholername1.getText());
            db.setValidMonth(Integer.parseInt(validmonth1.getText()));
            db.setValidYear(Integer.parseInt(validyear1.getText()));
            db.setVerificationCode(Integer.parseInt(cvc1.getText()));


        }
    }

    private static void isFilledIn(String field) throws IOException {
        if (field.length() < 1) {
            throw new IOException();
        }
    }








}
