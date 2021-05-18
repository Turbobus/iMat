package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Header extends AnchorPane {

    private AnchorPane pController;
    private Button earlierPurchaseButton;



    public Header(AnchorPane pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("header.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
    }
    @FXML
    private void earlierPurchaseButtonPressed(){

    }

    @FXML
    private void helpButtonpressed() {

    }

    @FXML
    private void accountButtonPressed() {

    }
}
