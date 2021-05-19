package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class header extends AnchorPane {

    private Controller pController;
    private Button earlierPurchaseButton;



    public header(Controller pController){
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
        pController.openEarlierPurchases();
    }

    @FXML
    private void helpButtonpressed() {
        pController.openHelp();
    }

    @FXML
    private void accountButtonPressed() {
        pController.openAccountView();
    }
}
