package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PayConfirmation extends AnchorPane {

    private final Controller pController;
    private final DB db = DB.getInstance();

    @FXML private AnchorPane payConfirmation;
    @FXML private Label purchaseInformationLbl; //den text som visar antal varor och totalt pris
    @FXML private Button cancelPurchaseButton;
    @FXML private Button confirmPurchaseButton;
    @FXML private Button closeButton;

    // Methods
    @FXML public void closeOverlay(){ pController.closeOverlay(); }

    @FXML public void buyShoppingCart() {
        db.placeOrder();
        pController.removeAllFromCart();
        pController.openPurchaseCompleted();
    }

    public PayConfirmation(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PayConfirmation.fxml"));
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
