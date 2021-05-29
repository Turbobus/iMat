package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    @FXML Label cardLabel;

    @FXML Button saveUntilNextTime;
    @FXML Button deleteUserInfo;
    @FXML Button deleteCardInfo;
    @FXML Button SaveAllandContinue;

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

    @FXML private AnchorPane confirmAccountPane;

    @FXML private AnchorPane confirmCardPane;

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
    @FXML private Label persInfoLabel;

    @FXML
    public void closeSettings(ActionEvent event) {
        pController.closeOverlay();
    }

    @FXML public void writeCardInfo(){
        saveUntilNextTime.setText("Spara uppgifterna igen");
        cardLabel.setText("Ändra kortuppgifter");
        deleteCardInfo.setVisible(true);
        writeNewCard.toFront();
    }

    @FXML public void saveCardDetails(){
        if (isAllTrue(isCorrectInformation) && !db.getFirstName().matches("") && db.getFirstName() != null){
            db.setCardNumber(cardNumber.getText());
            db.setHoldersName(cardName.getText());
            db.setValidMonth(Integer.parseInt(cardMonth.getText()));
            db.setValidYear(Integer.parseInt(cardYear.getText()));
            db.setVerificationCode(Integer.parseInt(cardCVC.getText()));
            db.setCardType(cardType);
            setupSavedCardInfo();
            useSavedCard.toFront();

            if (db.getCheckOutUpdater() != null) {
                db.getCheckOutUpdater().updateSavedCard();
            }
        }
    }

    @FXML public void deleteUserInfoPressed(ActionEvent event){
        confirmAccountPane.toFront();
    }

    @FXML public void deleteCardInfoPressed(ActionEvent event){
        confirmCardPane.toFront();
    }

    @FXML public void cardConfirmButtonPressed(ActionEvent event) {

        db.setCardNumber("");
        db.setHoldersName("");
        db.setValidMonth(0);              //Ändra
        db.setValidYear(0);               //Ändra
        db.setVerificationCode(0);        //Ändra
        db.setCardType("");

        setupSavedCardInfo();
        deleteCardInfo.toBack();
        writeNewCard.toFront();
        deleteCardInfo.setVisible(false);

    }

    @FXML public void accountConfirmButtonPressed(ActionEvent event) {

       db.setFirstName("");
       db.setLastName("");
       db.setAddress("");
       db.setPostAddress("");
       db.setPostCode("");
       db.setEMail("");
       db.setPhoneNumber("");
       db.setMobileNumber("");

        firstNameTextField1.setText("");
        lastNameTextField1.setText("");
        addressTextField1.setText("");
        postAddressTextField1.setText("");
        postalCodeTextField1.setText("");
        mailTextField1.setText("");
        telephoneTextField1.setText("");
        mobileTextField1.setText("");

        settingschanged.toFront();
        deleteUserInfo.toBack();
        setupSettings();

        db.setCardNumber("");
        db.setHoldersName("");
        db.setValidMonth(0);              //Ändra
        db.setValidYear(0);               //Ändra
        db.setVerificationCode(0);        //Ändra
        db.setCardType("");

        setupSavedCardInfo();
        deleteCardInfo.toBack();
        writeNewCard.toFront();
        deleteCardInfo.setVisible(false);

        updateButtons();

        if (db.getCheckOutUpdater() != null) {
            db.getCheckOutUpdater().doNotHaveAccount();
        }

    }

    @FXML public void cardDenyButtonPressed(ActionEvent event) {
        writeNewCard.toFront();
    }

    @FXML public void accountDenyButtonPressed(ActionEvent event) {
        settingschanged.toFront();
    }

    @FXML public void SaveAllandContinuePressed(ActionEvent event) {

        updatesettings();
        setupSavedCardInfo();
        pController.closeOverlay();
    }

    @FXML
    public void change1pressed(ActionEvent event) {

        settingschanged.toFront();

        persInfoLabel.setText("Ändra användaruppgifter");
        save1.setText("Spara användaruppgiterna");

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
    public void save1pressed(ActionEvent event) {
        updateSaveStatus();
        if (isFieldsRight){
            updatesettings();
            settingsdefault.toFront();
            deleteUserInfo.toFront();
            cardLabel.setText("Här kan du spara dina kortuppgifter");
            SaveAllandContinue.setId("green_button");
            SaveAllandContinue.setOnAction(this::SaveAllandContinuePressed);
        }


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
        setupSettings();
        setupTextField();
    }


    public void setupNextButton(boolean fromHeader) {

        if (fromHeader) {
            SaveAllandContinue.setText("Fortsätt handla");
            SaveAllandContinue.setId("green_button");
        } else {
            SaveAllandContinue.setText("Fortsätt betalningen");
            SaveAllandContinue.setId("green_button_disabled");
            SaveAllandContinue.setOnAction(null);
        }
    }

    private boolean isAllTrue(boolean[] array) {
        for(boolean b : array) if(!b) return false;
        return true;
    }

    private void clearDetails () {

        firstNameTextField1.setText("");
        lastNameTextField1.setText("");
        addressTextField1.setText("");
        postAddressTextField1.setText("");
        postalCodeTextField1.setText("");
        mailTextField1.setText("");
        telephoneTextField1.setText("");
        mobileTextField1.setText("");

        firstNameTextField1.setId("blue_text_field");
        lastNameTextField1.setId("blue_text_field");
        addressTextField1.setId("blue_text_field");
        postAddressTextField1.setId("blue_text_field");
        postalCodeTextField1.setId("blue_text_field");
        mailTextField1.setId("blue_text_field");
        telephoneTextField1.setId("blue_text_field");
        mobileTextField1.setId("blue_text_field");

        cardNumber.setText("");
        cardName.setText("");
        cardMonth.setText("");
        cardYear.setText("");
        cardCVC.setText("");

        cardNumber.setId("blue_text_field");
        cardName.setId("blue_text_field");
        cardMonth.setId("blue_text_field");
        cardYear.setId("blue_text_field");
        cardCVC.setId("blue_text_field");



    }

    public void setupUserInfo(){
        if (db.getFirstName() == null || db.getFirstName().matches("")){
            settingschanged.toFront();
            deleteUserInfo.toBack();
            persInfoLabel.setText("Ange dina användaruppgifter för att skapa ett konto");

            clearDetails();

        } else {
            setupSettings();
            settingsdefault.toFront();
            deleteUserInfo.toFront();

        }
    }

    private void updateUserInfoButtonVisual(){
        if (booleanIsUserFieldsRight()){
            save1.setId("addFavorites_blue");
        } else {
            save1.setId("addFavorites_blue_disabled");
        }

    }

    public void setupValidSettings () {

        firstNameTextField1.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUserInfoButtonVisual();
        });

        firstNameTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue) {
                        // Focus lost
                        if (firstNameTextField1.getText().matches("")) {
                            firstNameTextField1.setId("blue_text_field_wrong");
                        } else {
                            firstNameTextField1.setId("blue_text_field");
                        }

                    }
                });

        lastNameTextField1.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUserInfoButtonVisual();
        });

        lastNameTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (lastNameTextField1.getText().matches("")) {
                    lastNameTextField1.setId("blue_text_field_wrong");
                } else {
                    lastNameTextField1.setId("blue_text_field");
                }

            }
        });

        addressTextField1.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUserInfoButtonVisual();
        });

        addressTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (addressTextField1.getText().matches("")) {
                    addressTextField1.setId("blue_text_field_wrong");
                } else {
                    addressTextField1.setId("blue_text_field");
                }

            }


        });

        postAddressTextField1.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUserInfoButtonVisual();
        });

        postAddressTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if (postAddressTextField1.getText().matches("")) {
                    postAddressTextField1.setId("blue_text_field_wrong");
                } else {
                    postAddressTextField1.setId("blue_text_field");
                }

            }


        });

        postAddressTextField1.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUserInfoButtonVisual();
        });

        postAddressTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Focus lost
                if (postAddressTextField1.getText().matches("")) {
                    postAddressTextField1.setId("blue_text_field_wrong");
                } else {
                    postAddressTextField1.setId("blue_text_field");
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
            updateUserInfoButtonVisual();
        });

        postalCodeTextField1.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                // Focus lost
                if ( postalCodeTextField1.getText().length() != 5) {
                    postalCodeTextField1.setId("blue_text_field_wrong");
                } else {
                    postalCodeTextField1.setId("blue_text_field");
                }

            }
        });

    }

    private boolean booleanIsUserFieldsRight(){
        boolean flag = true;

        // Skum ordning, borde sätta true men har till false för det passade bättre
        if (isTextFieldEmpty(firstNameTextField1)) { flag = false; }
        if (isTextFieldEmpty(lastNameTextField1)) { flag = false; }
        if (isTextFieldEmpty(addressTextField1)) { flag = false; }
        if (isTextFieldEmpty(postAddressTextField1)) { flag = false; }
        if (isPostalCodeWrong()) { flag = false; }

        return flag;
    }

    public void updateSaveStatus(){
        isFieldsRight = booleanIsUserFieldsRight();
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

        if (db.getCheckOutUpdater() != null) {
            db.getCheckOutUpdater().haveAccount();
        }

        updateButtons();
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
            deleteCardInfo.setVisible(false);
            if(db.getFirstName().matches("") || db.getFirstName() == null){
                cardLabel.setText("Skapa ett konto innan du kan spara kortuppgifter");
            } else {
                cardLabel.setText("Här kan du spara dina kortuppgifter");
            }
            clearDetails();
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
            updateButtons();
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
        });

        cardName.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButtons();
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

        });

        cardMonth.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardMonth.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2){
                cardMonth.setText(oldValue);
            }
            updateButtons();
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
        });

        cardYear.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardYear.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2){
                cardYear.setText(oldValue);
            }
            updateButtons();

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
        });

        cardCVC.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardCVC.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 3){
                cardCVC.setText(oldValue);
            }

            updateButtons();

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
        });
    }

    private void updateButtons(){
        String nameId = cardName.getId();
        String numberId = cardNumber.getId();
        String monthId = cardMonth.getId();
        String yearId = cardYear.getId();
        String cvcId = cardCVC.getId();

        Node test = getScene().getFocusOwner();

        cardNumber.requestFocus();
        cardName.requestFocus();
        cardMonth.requestFocus();
        cardYear.requestFocus();
        cardCVC.requestFocus();
        saveUntilNextTime.requestFocus();

        cardName.setId(nameId);
        cardNumber.setId(numberId);
        cardMonth.setId(monthId);
        cardYear.setId(yearId);
        cardCVC.setId(cvcId);

        test.requestFocus();

        if (isAllTrue(isCorrectInformation) && !db.getFirstName().matches("") && db.getFirstName() != null){
            saveUntilNextTime.setId("addFavorites_blue");
        } else {
            saveUntilNextTime.setId("addFavorites_blue_disabled");
        }

    }
}
