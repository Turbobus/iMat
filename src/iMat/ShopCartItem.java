package iMat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ShopCartItem extends AnchorPane {


    public ShopCartItem(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopCartItem.fxml"));
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
