package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class ShopCart extends AnchorPane {

    @FXML Button checkOutButton;
    @FXML FlowPane cartItemHolder;

    @FXML public void checkoutPressed(ActionEvent event){
        cartItemHolder.getChildren().add(0, new ShopCartItem());
    }

    public ShopCart(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //this.pController = pController;


    }
}
