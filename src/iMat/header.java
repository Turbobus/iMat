package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class header extends AnchorPane {

    private AnchorPane pController;
    private Button earlierPurchaseButton;



    public header(AnchorPane pController){
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
    public void earlierPurchaseButtonPressed(ActionEvent event){
        pController.openEarlierPurchases();

    }

    public void helpButtonpressed(ActionEvent event){
        pController.openHelp();

    }
}
