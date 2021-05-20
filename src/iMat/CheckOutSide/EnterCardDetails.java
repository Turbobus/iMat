package iMat.CheckOutSide;

import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EnterCardDetails extends AnchorPane {

    private final DB db = DB.getInstance();

    @FXML AnchorPane writeNewCard;
    @FXML AnchorPane useSavedCard;

    @FXML TextField cardNumber;
    @FXML TextField cardName;
    @FXML TextField cardMonth;
    @FXML TextField cardYear;
    @FXML TextField cardCVC;

    @FXML Button saveUntilNextTime;


    @FXML public void saveCardDetails(){

    }

    public EnterCardDetails(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EnterCardDetails.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setupPane();
        setupTextField();
    }

    private void setupPane(){
        if (db.getCardNumber().matches("")){
            writeNewCard.toFront();
        } else {
            useSavedCard.toFront();
        }

        writeNewCard.toFront();
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

        cardMonth.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardMonth.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2){
                cardMonth.setText(oldValue);
            }

        });

        cardYear.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardYear.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 2){
                cardYear.setText(oldValue);
            }

        });

        cardCVC.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                cardCVC.setText(newValue.replaceAll("[^\\d]", ""));

            }

            if (newValue.length() > 3){
                cardCVC.setText(oldValue);
            }
        });

    }

}
