package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShopCart extends AnchorPane implements ShoppingCartListener {

    private final Controller pController;
    private final DB db = DB.getInstance();

    @FXML Button checkOutButton;
    @FXML Button emptyCartButton;
    @FXML FlowPane cartItemHolder;
    @FXML Label amountInCart;
    @FXML Label totalPriceOfCart;

    @FXML public void checkoutPressed(ActionEvent event){
        //cartItemHolder.getChildren().add(0, new ShopCartItem());
    }

    @FXML public void emptyCartPressed(ActionEvent event){
        //cartItemHolder.getChildren().add(0, new ShopCartItem());
    }

    public ShopCart(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopCart.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        db.setCartListener(this);
        db.reloadShoppingCart();

    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        cartItemHolder.getChildren().clear();
        for (ShoppingItem item : db.getAllShoppingItems()){
            cartItemHolder.getChildren().add(0, new ShopCartItem(item, pController));
        }
        totalPriceOfCart.setText("Totalt: " + String.format("%.2f",db.getTotalCartPrice()) + " kr");
        amountInCart.setText("Antal: " + db.getTotalAmountInCart());
    }
}
