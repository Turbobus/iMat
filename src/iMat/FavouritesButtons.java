package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FavouritesButtons extends AnchorPane implements ProductHolder{
    //EarlierPurchases pController;
    Controller controller;
    private DB db = DB.getInstance();
    private boolean allInCart = false;

    @FXML private FlowPane bProductFlowPane;
    @FXML private FlowPane gProductFlowPane;
    @FXML private AnchorPane favouritesCard;
    @FXML private AnchorPane gFavouritesCard;
    @FXML private AnchorPane bFavouritesCard;
    private ArrayList<ProductItem> favourites = new ArrayList<>();

    public FavouritesButtons(Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("favouritesButtons.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller=controller;
        setup();

    }

    private void setup(){
        favourites.clear();
        for(int i = 0;i<db.getFavorites().size();i++){
            ProductItem product = new ProductItem(new ShoppingItem(db.getFavorites().get(i)),this, controller);
            product.hideLabel();
            favourites.add(product);
        }

        for (ShoppingItem item : db.getAllShoppingItems()){
            for (ProductItem s :  favourites)
            {
                if(item.getProduct().getProductId() == s.getShoppingItem().getProduct().getProductId()){
                    s.setUpFromCart(item.getAmount());
                    s.setInCart(true);

                }
            }
        }
        showPurchases();
    }
    @FXML
    public void setButtonPutAllInCart(ActionEvent event){
        putAllInCart();

    }

    @FXML public void setButtonTakeOutOfCart(ActionEvent event){
        takeOutOfCart();
    }

    private void showPurchases(){
        //checkAllInCart();
        favouritesCard.setPrefHeight(99+88*favourites.size());
        bFavouritesCard.setPrefHeight(99+88*favourites.size());
        bProductFlowPane.getChildren().clear();
        for(int i = 0;i<favourites.size();i++){
            bProductFlowPane.getChildren().add(favourites.get(i));
        }
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
        //gProductFlowPane.getChildren().clear();
        //gFavouritesCard.setPrefHeight(bFavouritesCard.getPrefHeight());
        //gFavouritesCard.toFront();
        for (ProductItem s :  favourites)
        {
            if(!(s.isInCart())){
                s.inCart();
            }
        }
        //addProductItems(gProductFlowPane);


    }


    public void takeOutOfCart(){
        //bProductFlowPane.getChildren().clear();
        //bFavouritesCard.setPrefHeight(gFavouritesCard.getPrefHeight());
        //bFavouritesCard.toFront();
        for (ProductItem s :  favourites)
        {
            if(s.isInCart()){
                s.outOfCart();

            }

        }
        //addProductItems(bProductFlowPane);
    }


    private void addProductItems(FlowPane pane){
        pane.getChildren().clear();
        for(int i = 0;i<favourites.size();i++){
            pane.getChildren().add(favourites.get(i));
        }
    }



    @Override
    public List<ProductItem> getItems() {
        return favourites;
    }

    @Override
    public void reload() {
        for (ShoppingItem item : db.getAllShoppingItems()){
            for (ProductItem s :  favourites)
            {
                if(item.getProduct().getProductId() == s.getShoppingItem().getProduct().getProductId()){
                    s.setUpFromCart(item.getAmount());
                }
            }
        }

    }
}
