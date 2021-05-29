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
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

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
    @FXML private Pane gArrowPane;

    //blue version
    @FXML private Button bShowProducts;
    @FXML private Label bDateLabel;
    @FXML private Label bAmountLabel;
    @FXML private Button bPutAllInCart;
    @FXML private FlowPane bAllProductsPane;
    @FXML private AnchorPane bSingularPurchaseBack;
    @FXML private AnchorPane bSingularPurchaseFront;
    @FXML private FlowPane bProductFlowPane;
    @FXML private Pane bArrowPane;

    private boolean expanded = false;

    private DB db = DB.getInstance();


    private Controller controller;
    private Order order;
    private ArrayList<ProductItem> items = new ArrayList<>();
    private EarlierPurchases pController;


    public SingularPurchase(Controller controller, Order order,EarlierPurchases pController ){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("singularPurchase.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        this.controller = controller;
        this.order = order;
        setUp();
    }


    private void setUp(){
        int total = 0;
        //creates productitems out of orders, and calculates what the price of the whole purchase is
        for(int i = 0;i<order.getItems().size();i++){
            ProductItem product = new ProductItem(order.getItems().get(i),this, controller);
            items.add(product);
            total += product.getShoppingItem().getTotal();
        }
        reload();

        bAmountLabel.setText("Totalt: "+ total + " kr");
        gAmountLabel.setText("Totalt: "+ total + " kr");

        //sets date
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date2 = simpleDateFormat.format(order.getDate());
        bDateLabel.setText(date2);
        gDateLabel.setText(date2);



    }


    @Override
    public EarlierPurchases getController() {
        return pController;
    }

    @Override
    public void reload(){
        for (ShoppingItem item :  db.getAllShoppingItems()){
            for (ProductItem s : items)
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

        for(ProductItem item : items){
            if (!item.isInCart()) {
                return;
            }
        }

        gProductFlowPane.getChildren().clear();
        gSingularPurchaseBack.setPrefHeight(bSingularPurchaseBack.getPrefHeight());
        gSingularPurchaseBack.toFront();

        if(expanded){
            addProductItems(gProductFlowPane);
        }
        //putAllInCart();
    }

    public void checkAllOutOfCart(){
        bProductFlowPane.getChildren().clear();
        bSingularPurchaseBack.setPrefHeight(gSingularPurchaseBack.getPrefHeight());
        bSingularPurchaseBack.toFront();

        if(expanded){
            addProductItems(bProductFlowPane);
        }
    }

    public void putAllInCart(){

        for (ProductItem s :  items)
        {
            if(!(s.isInCart())){
                s.inCart();
                controller.updateCartItemAmount(s.getShoppingItem().getProduct().getProductId(),(int) s.getShoppingItem().getAmount());
            }
        }

        gProductFlowPane.getChildren().clear();
        gSingularPurchaseBack.setPrefHeight(bSingularPurchaseBack.getPrefHeight());
        gSingularPurchaseBack.toFront();

        if(expanded){
            addProductItems(gProductFlowPane);
        }
    }

    @FXML
    public void setButtonPutAllInCart(ActionEvent event){
        putAllInCart();

    }

    public void takeOutOfCart(){


        for (ProductItem s :  items)
        {
            if(s.isInCart()){
                s.outOfCart();
            }
        }

        bProductFlowPane.getChildren().clear();
        bSingularPurchaseBack.setPrefHeight(gSingularPurchaseBack.getPrefHeight());
        bSingularPurchaseBack.toFront();

        if(expanded){
            addProductItems(bProductFlowPane);
        }
    }

    @FXML public void setButtonTakeOutOfCart(ActionEvent event){

        takeOutOfCart();
    }


    @Override
    public void makeBlue(int productId) {
        for(ProductItem item: items){
            if(item.getShoppingItem().getProduct().getProductId()==productId){
                item.makeBlue();
                //checkAllOutOfCart();
            }
        }
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
            gArrowPane.setRotate(90);
            bArrowPane.setRotate(90);
        }
        else{
            bArrowPane.setRotate(270);
            gArrowPane.setRotate(270);
            singularPurchaseCard.setPrefHeight(130+88*(items.size()));
            gSingularPurchaseBack.setPrefHeight(130+88*(items.size()));
            bSingularPurchaseBack.setPrefHeight(130+88*(items.size()));
            addProductItems(fPane);
            expanded = true;

        }
    }

    private void addProductItems(FlowPane pane){
        pane.getChildren().clear();
        for(ProductItem item : items){
            pane.getChildren().add(item);
        }
    }

    @FXML
    public void showProducts(ActionEvent event){
        addItems(bProductFlowPane);

    }

    @FXML
    public void gShowProducts(ActionEvent event){
        addItems(gProductFlowPane);

    }


    @Override
    public ArrayList<ProductItem> getItems() {
        return items;
    }
}


