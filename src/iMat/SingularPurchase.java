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
import java.util.Calendar;
import java.util.Date;

public class SingularPurchase extends AnchorPane implements ProductHolder{

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

    private DB db = DB.getInstance();


    private Controller pController;
    private Order order;
    private ArrayList<ProductItem> items = new ArrayList<>();


    public SingularPurchase(Controller pController, Order order){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("singularPurchase.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        this.order = order;
        setUp();
    }


    private void setUp(){
        int total = 0;
        //creates productitems out of orders, and calculates what the price of the whole purchase is
        for(int i = 0;i<order.getItems().size();i++){
            ProductItem product = new ProductItem(order.getItems().get(i),this, pController);
            items.add(product);
            total += product.getShoppingItem().getTotal();
        }
        bAmountLabel.setText("Totalt: "+ total + " kr");
        gAmountLabel.setText("Totalt: "+ total + " kr");
        Calendar date = dateToCalendar(order.getDate());
        //green card
        //gDateLabel.setText(order.getDate().toString());


        //blue card
        //bDateLabel.setText(date.getWeekYear());

    }

    public void reload(){
        for (ShoppingItem item : db.getAllShoppingItems()){
            for (ProductItem s :  items)
            {
                if(item.getProduct().getProductId() == s.getShoppingItem().getProduct().getProductId()){
                    s.setUpFromCart(item.getAmount());
                }
            }
        }
    }

    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

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
            if(!(items.get(i).isInCart())){
                takeOutOfCart();;
            }
        }

    }

    public void putAllInCart(){
        gProductFlowPane.getChildren().clear();
        gSingularPurchaseBack.setPrefHeight(bSingularPurchaseBack.getPrefHeight());
        gSingularPurchaseBack.toFront();
        for (ProductItem s :  items)
        {
            if(!(s.isInCart())){
                s.inCart();
            }
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
            if(s.isInCart()){
                s.outOfCart();
            }
        }
        if(expanded){
            addProductItems(bProductFlowPane);
        }
    }

    @FXML public void setButtonTakeOutOfCart(ActionEvent event){


        takeOutOfCart();
    }

    private void hideProducts(){
        bSingularPurchaseBack.setPrefHeight(130);
        gSingularPurchaseBack.setPrefHeight(130);
        singularPurchaseCard.setPrefHeight(130);
        expanded = false;
    }
    private void addItems(FlowPane fPane){
        bProductFlowPane.getChildren().clear();
        gProductFlowPane.getChildren().clear();

        if(expanded){
            hideProducts();
        }
        else{
            singularPurchaseCard.setPrefHeight(180+78*(order.getItems().size()));
            gSingularPurchaseBack.setPrefHeight(180+78*(order.getItems().size()));
            bSingularPurchaseBack.setPrefHeight(180+78*(order.getItems().size()));
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
        addItems(bProductFlowPane);
    }

    public void gShowProducts(ActionEvent event){
        addItems(gProductFlowPane);
    }





}


