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

    @FXML AnchorPane writeNewCard;
    @FXML AnchorPane useSavedCard;

    @FXML TextField cardNumber;
    @FXML TextField cardName;
    @FXML TextField cardMonth;
    @FXML TextField cardYear;
    @FXML TextField cardCVC;

    @FXML ImageView enterInfoVisaImg;
    @FXML ImageView enterInfoMasterImg;
    @FXML ImageView showInfoVisaImg;
    @FXML ImageView showInfoMasterImg;

    @FXML Label cardNumberText;
    @FXML Label cardNameText;
    @FXML Label cardMonthText;
    @FXML Label cardYearText;
    @FXML Label cardCVCText;

    @FXML Button saveUntilNextTime;

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


    //Textfields for account window default

    @FXML
    private Label firstNameTextField;
    @FXML
    private Label lastNameTextField;
    @FXML
    private Label addressTextField;
    @FXML
    private Label postalCodeTextField;
    @FXML
    private Label postAddressTextField;
    @FXML
    private Label mailTextField;
    @FXML
    private Label telephoneTextField;
    @FXML
    private Label mobileTextField;

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

    @FXML
    public void closeSettings(ActionEvent event) {
        pController.closeOverlay();
        settingsdefault.toFront();
        useSavedCard.toFront();
    }

    @FXML public void writeCardInfo(){
        saveUntilNextTime.setText("Spara uppgifterna igen");
        writeNewCard.toFront();
    }

    @FXML public void saveCardDetails(){
        if (isAllTrue(isCorrectInformation)){
            db.setCardNumber(cardNumber.getText());
            db.setHoldersName(cardName.getText());
            db.setValidMonth(Integer.parseInt(cardMonth.getText()));
            db.setValidYear(Integer.parseInt(cardYear.getText()));
            db.setVerificationCode(Integer.parseInt(cardCVC.getText()));
            db.setCardType(cardType);
            setupSavedCardInfo();
            useSavedCard.toFront();
        }
    }

    private boolean isAllTrue(boolean[] array) {
        for(boolean b : array) if(!b) return false;
        return true;
    }

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

        setupValidSettings();
        saveCardDetails();
        setupSettings();
        setupTextField();

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
                            firstNameTextField1.setId("blue_text_field_wrong");
                            isCorrectInformation[0] = false;
                        } else {
                            firstNameTextField1.setId("blue_text_field");
                            isCorrectInformation[0] = true;
                        }

                    }
                });

            lastNameTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

                if (!newValue) {
                    // Focus lost
                    if (lastNameTextField1.getText().matches("")) {
                        lastNameTextField1.setId("blue_text_field_wrong");
                        isCorrectInformation[1] = false;
                    } else {
                        lastNameTextField1.setId("blue_text_field");
                        isCorrectInformation[1] = true;
                    }

                }


            });

        addressTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (addressTextField1.getText().matches("")) {
                    addressTextField1.setId("blue_text_field_wrong");
                    isCorrectInformation[2] = false;
                } else {
                    addressTextField1.setId("blue_text_field");
                    isCorrectInformation[2] = true;
                }

            }


        });

        postAddressTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (postAddressTextField1.getText().matches("")) {
                    postAddressTextField1.setId("blue_text_field_wrong");
                    isCorrectInformation[3] = false;
                } else {
                    postAddressTextField1.setId("blue_text_field");
                    isCorrectInformation[3] = true;
                }

            }


        });

        postalCodeTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (postAddressTextField1.getText().matches("")) {
                    postAddressTextField1.setId("blue_text_field_wrong");
                    isCorrectInformation[3] = false;
                } else {
                    postAddressTextField1.setId("blue_text_field");
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
                    postalCodeTextField1.setId("blue_text_field_wrong");
                    isCorrectInformation[4] = false;
                } else {
                    postalCodeTextField1.setId("blue_text_field");
                    isCorrectInformation[4] = true;
                }

            }
        });

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

    public void setupCardPane(){
        if (db.getCardNumber().matches("")){
            writeNewCard.toFront();
        } else {
            setupSavedCardInfo();
            useSavedCard.toFront();
        }
    }

    private void setupSavedCardInfo(){
        cardNumberText.setText(db.getCardNumber());
        cardNameText.setText(db.getHoldersName());
        cardMonthText.setText("" + db.getValidMonth());
        cardYearText.setText("" + db.getValidYear());
        cardCVCText.setText("" + db.getVerificationCode());
        cardNumber.setText(db.getCardNumber());
        cardName.setText(db.getHoldersName());
        cardMonth.setText("" + db.getValidMonth());
        cardYear.setText("" + db.getValidYear());
        cardCVC.setText("" + db.getVerificationCode());
        isCorrectInformation = new boolean[]{true, true, true, true, true};
        displayCardType(db.getCardType());
        cardType = db.getCardType();
    }

    private void displayCardType(String cardType){
        enterInfoMasterImg.setOpacity(0);
        showInfoMasterImg.setOpacity(0);
        enterInfoVisaImg.setOpacity(0);
        showInfoVisaImg.setOpacity(0);

        if (cardType.matches("Visa")) {
            enterInfoVisaImg.setOpacity(1);
            showInfoVisaImg.setOpacity(1);
        } else if (cardType.matches("MasterCard")){
            enterInfoMasterImg.setOpacity(1);
            showInfoMasterImg.setOpacity(1);
        }
    }

    private void setupTextField(){

        cardNumber.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardNumber.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 16){
                cardNumber.setText(oldValue);
            }
        });

        cardNumber.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (cardNumber.getText().length() != 16 || (cardNumber.getText().charAt(0) != '4' && cardNumber.getText().charAt(0) != '5') ){
                    cardNumber.setId("blue_text_field_wrong");
                    displayCardType("No");
                    isCorrectInformation[0] = false;
                } else {
                    cardNumber.setId("blue_text_field");
                    if (cardNumber.getText().charAt(0) == '4'){

                        displayCardType("Visa");
                        cardType = "Visa";

                    } else {

                        displayCardType("MasterCard");
                        cardType = "MasterCard";
                    }

                    isCorrectInformation[0] = true;
                }
            }
//            pController.updateButtonState();
        });

        cardName.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (cardName.getText().matches("")){
                    cardName.setId("blue_text_field_wrong");
                    isCorrectInformation[1] = false;
                } else {
                    cardName.setId("blue_text_field");
                    isCorrectInformation[1] = true;
                }
            }

//            pController.updateButtonState();
        });

        cardMonth.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardMonth.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2){
                cardMonth.setText(oldValue);
            }
        });

        cardMonth.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (cardMonth.getText().matches("") || cardMonth.getText().matches("0") || Integer.parseInt(cardMonth.getText()) > 12 ){
                    cardMonth.setId("blue_text_field_wrong");
                    isCorrectInformation[2] = false;
                } else {
                    cardMonth.setId("blue_text_field");
                    isCorrectInformation[2] = true;
                }
            }
//            pController.updateButtonState();
        });

        cardYear.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardYear.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2){
                cardYear.setText(oldValue);
            }

        });

        cardYear.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (cardYear.getText().matches("") || Integer.parseInt(cardYear.getText()) < 20 || Integer.parseInt(cardYear.getText()) > 30 ){
                    cardYear.setId("blue_text_field_wrong");
                    isCorrectInformation[3] = false;
                } else {
                    cardYear.setId("blue_text_field");
                    isCorrectInformation[3] = true;
                }
            }
//            pController.updateButtonState();
        });

        cardCVC.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardCVC.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 3){
                cardCVC.setText(oldValue);
            }

        });

        cardCVC.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (cardCVC.getText().length() != 3){
                    cardCVC.setId("blue_text_field_wrong");
                    isCorrectInformation[4] = false;
                } else {
                    cardCVC.setId("blue_text_field");
                    isCorrectInformation[4] = true;
                }
            }
//            pController.updateButtonState();
        });
    }
}
