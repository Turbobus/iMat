package iMat.CheckOutSide;

import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EnterCardDetails extends AnchorPane {

    private final DB db = DB.getInstance();

    @FXML AnchorPane writeNewCard;
    @FXML AnchorPane useSavedCard;

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
    }

    private void setupPane(){
        if (db.getCardNumber().matches("")){
            writeNewCard.toFront();
        } else {
            useSavedCard.toFront();
        }
    }


}
