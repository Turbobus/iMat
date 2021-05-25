package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EarlierPurchases extends AnchorPane implements ShoppingCartListener {
    @FXML private AnchorPane earlierPurchases;
    @FXML private Button closeButton;
    @FXML private FlowPane purchasesFlowPane;
    @FXML private Label titleLabel;
    @FXML private ImageView iconImgView;
    @FXML private Pane iconPane;

    private Controller pController;
    private DB db = DB.getInstance();
    private List<ProductHolder> holder = new ArrayList<>();
    private List<ProductHolder> favourites = new ArrayList<>();
    private List<ProductHolder> purchases = new ArrayList<>();


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

        db.setCartListener(this);
    }


    public void closeButtonPressed(ActionEvent event){
        pController.closeOverlay();
    }




    //adds previous purchases to the flowpane
    public void showPurchases(){
        purchases.clear();
        titleLabel.setText("Tidigare köp");
        iconPane.setId("earlier_purchase_svg");
        //clear list
        purchasesFlowPane.getChildren().clear();
        for(Order order : db.getOrders()){
            SingularPurchase purchase = new SingularPurchase(pController, order);
            //purchase.reload();
            purchases.add(purchase);
            purchasesFlowPane.getChildren().add(0,purchase);

        }

        /*for (ShoppingItem item : db.getAllShoppingItems()){
            for (ProductItem s :  favourites)
            {
                if(item.getProduct().getProductId() == s.getShoppingItem().getProduct().getProductId()){
                    s.setUpFromCart(item.getAmount());
                }
            }
        }

         */
    }

    public void showFavourites(){
        favourites.clear();
        titleLabel.setText("Favoriter");
        iconPane.setId("heartIcon_white");
        purchasesFlowPane.getChildren().clear();
        FavouritesButtons favouritesButtons = new FavouritesButtons(pController);
        favourites.add(favouritesButtons);
        purchasesFlowPane.getChildren().add(favouritesButtons);

    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        holder.clear();
        holder.addAll(favourites);
        holder.addAll(purchases);
        for(int i = 0; i<holder.size();i++){
            holder.get(i).reload();

        }
    }
}
