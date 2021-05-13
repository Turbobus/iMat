package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class EarlierPurchases extends AnchorPane{
    @FXML private AnchorPane earlierPurchases;
    @FXML private Button closeButton;
    @FXML private FlowPane purchasesFlowPane;

    private Controller pController;


    public EarlierPurchases(Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("earlierPurchases.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = controller;
        //showPurchases();
    }


    public void closeButtonPressed(ActionEvent event){
        pController.closeOverlay();
    }

/*


    //adds previous purchases to the flowpane
    public void showPurchases(){
        purchasesFlowPane.getChildren().clear();
        SingularPurchase purchase = new SingularPurchase(pController);

        purchasesFlowPane.getChildren().add(purchase.);

    }

 */



}
