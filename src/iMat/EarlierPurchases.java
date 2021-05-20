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
    List<SingularPurchase> purchasesList = new ArrayList<SingularPurchase>();


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
        showPurchases();

    }


    public void closeButtonPressed(ActionEvent event){
        pController.closeOverlay();
    }




    //adds previous purchases to the flowpane
    private void showPurchases(){

        //hårdkodat fult, allt ska bytas ut!!!!!!!!!!

        Order o1 = new Order();
        List<ShoppingItem> items = new ArrayList<ShoppingItem>(
                Arrays.asList(new ShoppingItem(new Product()), new ShoppingItem(new Product()), new ShoppingItem(new Product())));

        o1.setItems(items);
        purchasesFlowPane.getChildren().clear();
        SingularPurchase purchase = new SingularPurchase(pController, o1);
        purchasesFlowPane.getChildren().add(purchase);

        Order o2 = new Order();
        List<ShoppingItem> items2 = new ArrayList<ShoppingItem>(
                Arrays.asList(new ShoppingItem(new Product()), new ShoppingItem(new Product()), new ShoppingItem(new Product())));

        o2.setItems(items2);
        SingularPurchase purchase2 = new SingularPurchase(pController,o2);

        purchasesFlowPane.getChildren().add(purchase2);

        Order o3 = new Order();
        List<ShoppingItem> items3 = new ArrayList<ShoppingItem>(
                Arrays.asList(new ShoppingItem(new Product()), new ShoppingItem(new Product()), new ShoppingItem(new Product())));

        o3.setItems(items3);
        SingularPurchase purchase3 = new SingularPurchase(pController,o3);

        purchasesFlowPane.getChildren().add(purchase3);



        purchasesList.add(purchase);
        purchasesList.add(purchase2);
        purchasesList.add(purchase3);
    }






}
