package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FavouritesButtons extends AnchorPane {
    Favourites pController;

    public FavouritesButtons(Favourites pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("favouritesButtons.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.pController=pController;
    }
    @FXML
    public void setButtonPutAllInCart(ActionEvent event){
        pController.putAllInCart();

    }

    @FXML public void setButtonTakeOutOfCart(ActionEvent event){
        pController.takeOutOfCart();
    }

}
