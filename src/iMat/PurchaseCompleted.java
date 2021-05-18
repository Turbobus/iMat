package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PurchaseCompleted extends AnchorPane {

    private final Controller pController;


    @FXML private AnchorPane purchaseCompleted;
    @FXML private Button backEarlierPurchaseButton;
    @FXML private Button backHomeButton;
    @FXML private ImageView closeImgView;

    // Methods

    @FXML public void closeOverlay(){ pController.closeOverlay(); }
    @FXML public void openEarlierPurchase(){  }
    @FXML public void backToStart(){
        pController.closeOverlay();
        pController.setupShop();
    }

    public PurchaseCompleted(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("purchaseCompleted.fxml"));
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
