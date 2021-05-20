package iMat;

import iMat.Controller;
import iMat.DB;
import iMat.ProductHolder;
import iMat.ProductItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;

public class Favourites extends AnchorPane implements ProductHolder{
    Controller pController;
    @FXML
    private Button closeButton;
    @FXML private FlowPane purchasesFlowPane;
    private ArrayList<ProductItem> favourites = new ArrayList<>();
    private DB db = DB.getInstance();
    private FavouritesButtons buttonCard;
    @FXML private Label titleLabel;

    public Favourites(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("earlierPurchases.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.pController = pController;
        titleLabel.setText("Favoriter");
        setUp();

    }

    public void setUp(){
        favourites.clear();
        for(int i = 0;i<db.getFavorites().size();i++){
            ProductItem product = new ProductItem(new ShoppingItem(db.getFavorites().get(i)),this, pController);
            product.hideLabel();
            favourites.add(product);
        }
        buttonCard = new FavouritesButtons(this);

        for (ShoppingItem item : db.getAllShoppingItems()){
            for (ProductItem s :  favourites)
            {
                if(item.getProduct().getProductId() == s.getShoppingItem().getProduct().getProductId()){
                    s.setUpFromCart(item.getAmount());
                }
            }
        }
        showPurchases();

    }

    private void showPurchases(){
        purchasesFlowPane.getChildren().clear();
        purchasesFlowPane.getChildren().add(buttonCard);
        for(int i = 0;i<favourites.size();i++){
            purchasesFlowPane.getChildren().add(favourites.get(i));
        }
    }

    public void closeButtonPressed(ActionEvent event){
        pController.closeOverlay();
    }

    public void checkAllInCart(){
        for(int i = 0;i<favourites.size();i++){
            if(!(favourites.get(i).isInCart())){
                return;
            }
        }
        putAllInCart();
    }

    public void checkAllOutOfCart(){
        for(int i = 0;i<favourites.size();i++){
            if(favourites.get(i).isInCart()){
                return;
            }
        }
        takeOutOfCart();
    }

    public void putAllInCart(){
        for (ProductItem s :  favourites)
        {
            if(!(s.isInCart())){
                s.inCart();
            }
        }

    }


    public void takeOutOfCart(){
        for (ProductItem s :  favourites)
        {
            if(s.isInCart()){
                s.outOfCart();

            }

        }
    }
}
