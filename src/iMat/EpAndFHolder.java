package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.*;

public class EpAndFHolder<a> extends AnchorPane implements ShoppingCartListener {
    @FXML private AnchorPane earlierPurchases;
    @FXML private Button closeButton;
    @FXML private FlowPane purchasesFlowPane;
    @FXML private Label titleLabel;
    @FXML private ImageView iconImgView;
    @FXML private Pane iconPane;
    @FXML private Label noHoldersLabel;
    @FXML private AnchorPane noHoldersPane;

    private Controller pController;
    private DB db = DB.getInstance();
    private List<ProductHolder> holder = new ArrayList<>();
    private List<ProductHolder> favourites = new ArrayList<>();
    private List<ProductHolder> purchases = new ArrayList<>();


    public EpAndFHolder(Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EpAndFHolder.fxml"));
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


    private List<Order> sortInComingOrders(List<Order> listToBeSorted){
        listToBeSorted.sort(Comparator.comparing(Order::getDate));
        return listToBeSorted;
    }

    //adds previous purchases to the flowpane
    public void showPurchases(){
        purchases.clear();
        titleLabel.setText("Tidigare köp");
        iconPane.setId("earlier_purchase_svg");
        iconImgView.setOpacity(0);
        iconPane.setOpacity(1);
        //clear list
        purchasesFlowPane.getChildren().clear();
        noHoldersLabel.setText("Du har inga tidigare köp än, om du köper något kommer köpet att visas här");
        if(db.getOrders().size()==0){
            noHoldersPane.setOpacity(1);
            noHoldersPane.toFront();
        }
        else{
            noHoldersPane.setOpacity(0);
            noHoldersPane.toBack();
            for(Order order : sortInComingOrders(db.getOrders())){
                SingularPurchase purchase = new SingularPurchase(pController, order, this);
                //purchase.reload();
                purchases.add(purchase);
                purchasesFlowPane.getChildren().add(0,purchase);

            }
        }


    }

    public void showFavourites(){
        favourites.clear();
        titleLabel.setText("Favoriter");
        iconImgView.setOpacity(1);
        iconPane.setOpacity(0);
        purchasesFlowPane.getChildren().clear();
        noHoldersLabel.setText("Du har inga favoriter än, om du lägger till en vara som favorit visas den här");
        if(db.getFavorites().size()==0){
            noHoldersPane.setOpacity(1);
            noHoldersPane.toFront();
        }
        else{
            noHoldersPane.setOpacity(0);
            noHoldersPane.toBack();
            FavouritesButtons favouritesButtons = new FavouritesButtons(pController, this);
            favourites.add(favouritesButtons);
            purchasesFlowPane.getChildren().add(favouritesButtons);
        }


    }

    public void makeBlue(int prodId) {
        holder.clear();
        holder.addAll(favourites);
        holder.addAll(purchases);
        for(int i = 0; i<holder.size();i++){
            holder.get(i).makeBlue(prodId);

        }
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
