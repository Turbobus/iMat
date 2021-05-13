package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class SingularPurchase {
    //green version
    @FXML private Label gDateLabel;
    @FXML private Label gAmountLabel;
    @FXML private Button gShowProducts;
    @FXML private Button gTakeOutCart;
    @FXML private AnchorPane gSingularPurchaseBack;
    @FXML private AnchorPane gSingularPurchaseFront;

    //blue version
    @FXML private Button bShowProducts;
    @FXML private Label bDateLabel;
    @FXML private Label bAmountLabel;
    @FXML private Button bPutAllInCart;
    @FXML private FlowPane bAllProductsPane;
    @FXML private AnchorPane bSingularPurchaseBack;
    @FXML private AnchorPane bSingularPurchaseFront;

    private Controller controller;

    public SingularPurchase(Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("singularPurchase.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
    }

    private void putAllInCart(ActionEvent event){
        gSingularPurchaseBack.toFront();
        gSingularPurchaseFront.toFront();

    }



}


