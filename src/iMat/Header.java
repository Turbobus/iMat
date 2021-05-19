package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Header extends AnchorPane {

    private Controller pController;


    @FXML private Button earlierPurchaseButton;
    @FXML private TextField searchTextField;


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

    @FXML
    private void searchPressed(){
        pController.search(searchTextField.getText());
    }

    public Header(Controller pController){
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

}
