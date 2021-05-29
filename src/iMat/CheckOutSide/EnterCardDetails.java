package iMat.CheckOutSide;

import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EnterCardDetails extends AnchorPane {

    private final DB db = DB.getInstance();
    private boolean[] isCorrectInformation = {false, false, false, false, false};
    private String cardType;
    private final CheckOutPanel pController;

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

    public EnterCardDetails(CheckOutPanel pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterCardDetails.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        setupPane();
        setupTextField();

    }

    public void setupPane(){
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


    private void updateButtons(){
        String nameId = cardName.getId();
        String numberId = cardNumber.getId();
        String monthId = cardMonth.getId();
        String yearId = cardYear.getId();
        String cvcId = cardCVC.getId();

        Node test = null;


        try {
            if (getScene().getFocusOwner() != null) {
                test = getScene().getFocusOwner();
            }
        } catch (NullPointerException npe ) {
            test = null;
        }

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

        if (test != null) {
            test.requestFocus();
        }

        if (isAllTrue(isCorrectInformation)){
            saveUntilNextTime.setId("addFavorites_blue");
        } else {
            saveUntilNextTime.setId("addFavorites_blue_disabled");
        }
        pController.updateButtonState();
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

            pController.updateButtonState();
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
            pController.updateButtonState();
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
            pController.updateButtonState();
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
            pController.updateButtonState();
        });
    }


    public String getCardType(){ return cardType; }
    public boolean correctCardInfo(){ return isAllTrue(isCorrectInformation); }
}
