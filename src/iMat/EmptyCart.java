package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmptyCart extends AnchorPane {

    private final Controller pController;


    @FXML
    public void closePane(ActionEvent event){
        pController.closeOverlay();
    }

    @FXML
    public void emptyCartPressed(ActionEvent event){
        pController.removeAllFromCart();
        pController.closeOverlay();
    }


    public EmptyCart(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmptyCart.fxml"));
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
