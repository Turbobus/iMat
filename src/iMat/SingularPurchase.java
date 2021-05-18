package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.PointLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;

public class SingularPurchase extends AnchorPane{

    @FXML private AnchorPane singularPurchaseCard;
    //green version
    @FXML private Label gDateLabel;
    @FXML private Label gAmountLabel;
    @FXML private Button gShowProducts;
    @FXML private Button gTakeOutCart;
    @FXML private AnchorPane gSingularPurchaseBack;
    @FXML private AnchorPane gSingularPurchaseFront;
    @FXML private FlowPane gProductFlowPane;

    //blue version
    @FXML private Button bShowProducts;
    @FXML private Label bDateLabel;
    @FXML private Label bAmountLabel;
    @FXML private Button bPutAllInCart;
    @FXML private FlowPane bAllProductsPane;
    @FXML private AnchorPane bSingularPurchaseBack;
    @FXML private AnchorPane bSingularPurchaseFront;
    @FXML private FlowPane bProductFlowPane;

    private boolean expanded = false;


    private EarlierPurchases pController;
    private Order order;
    private ArrayList<ProductItem> items = new ArrayList<>();

    public SingularPurchase(EarlierPurchases controller, Order order){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("singularPurchase.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = controller;
        this.order = order;
        setUp();
    }


    private void setUp(){
        //green card
        //gDateLabel.setText(order.getDate().toString());
        //gAmountLabel.setText(order.get);

        //blue card
        //bDateLabel.setText(order.getDate().toString());
        //bAmountLabel;

        for(int i = 0;i<order.getItems().size();i++){
            ProductItem product = new ProductItem(order.getItems().get(i), this);
            items.add(product);
        }

    }


    public void checkAllInCart(){
        for(int i = 0;i<items.size();i++){
            if(!(items.get(i).isInCart())){
                return;
            }
        }
        putAllInCart();
    }

    public void checkAllOutOfCart(){
        for(int i = 0;i<items.size();i++){
            if(items.get(i).isInCart()){
                return;
            }
        }
        takeOutOfCart();
    }

    public void putAllInCart(){
        gProductFlowPane.getChildren().clear();
        gSingularPurchaseBack.setPrefHeight(bSingularPurchaseBack.getPrefHeight());
        gSingularPurchaseBack.toFront();
        for (ProductItem s :  items)
        {
            s.inCart();
        }
        if(expanded){
            addProductItems(gProductFlowPane);
        }

    }

    @FXML
    public void setButtonPutAllInCart(ActionEvent event){
        putAllInCart();

    }

    public void takeOutOfCart(){
        bProductFlowPane.getChildren().clear();
        bSingularPurchaseBack.setPrefHeight(gSingularPurchaseBack.getPrefHeight());
        bSingularPurchaseBack.toFront();
        for (ProductItem s :  items)
        {
            s.outOfCart();
        }
        if(expanded){
            addProductItems(bProductFlowPane);
        }


    }

    @FXML void setButtonTakeOutOfCart(ActionEvent event){
        takeOutOfCart();
    }

    private void hideProducts(){
        bSingularPurchaseBack.setPrefHeight(130);
        gSingularPurchaseBack.setPrefHeight(130);
        singularPurchaseCard.setPrefHeight(130);
        expanded = false;
    }
    private void addItems(FlowPane fPane, AnchorPane aPane){
        bProductFlowPane.getChildren().clear();
        gProductFlowPane.getChildren().clear();

        if(expanded){
            hideProducts();
        }
        else{
            singularPurchaseCard.setPrefHeight(180+78*(order.getItems().size()));
            aPane.setPrefHeight(180+78*(order.getItems().size()));
            addProductItems(fPane);
            expanded = true;

        }
    }

    private void addProductItems(FlowPane pane){
        pane.getChildren().clear();
        for(int i = 0;i<items.size();i++){
            pane.getChildren().add(items.get(i));
        }
    }

    public void showProducts(ActionEvent event){
        addItems(bProductFlowPane, bSingularPurchaseBack);
    }

    public void gShowProducts(ActionEvent event){
        addItems(gProductFlowPane, gSingularPurchaseBack);
    }





}


