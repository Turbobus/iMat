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
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EarlierPurchases extends AnchorPane{
    @FXML private AnchorPane earlierPurchases;
    @FXML private Button closeButton;
    @FXML private FlowPane purchasesFlowPane;
    @FXML private Label titleLabel;
    @FXML private ImageView iconImgView;

    private Controller pController;
    List<SingularPurchase> purchasesList = new ArrayList<>();
    private DB db = DB.getInstance();


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
        titleLabel.setText("Tidigare köp");
        /*iconImgView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "iMat/img/earlierPurchase_White.png")));

         */


    }


    public void closeButtonPressed(ActionEvent event){
        pController.closeOverlay();
    }




    //adds previous purchases to the flowpane
    public void showPurchases(){

        purchasesFlowPane.getChildren().clear();
        for(int i = 0;i<db.getOrders().size();i++){
            SingularPurchase purchase = new SingularPurchase(pController, db.getOrders().get(i));
            purchase.reload();
            purchasesList.add(purchase);
            purchasesFlowPane.getChildren().add(0,purchasesList.get(i));

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






}
