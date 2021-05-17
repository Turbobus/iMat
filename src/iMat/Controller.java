package iMat;

import iMat.CheckOutSide.CheckOutHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller extends AnchorPane implements Initializable {

    private final DB db = DB.getInstance();

    private final Map<Integer, ProductCard> productCards = new HashMap<>();
    public Map<Integer, ProductCard> getProductCards(){ return productCards; }

    private final DetailView detailView = new DetailView(this);
    private final EmptyCart EmptyCart = new EmptyCart(this);

    @FXML AnchorPane window;
    @FXML AnchorPane darkPane;
    @FXML AnchorPane putHere;

    @FXML
    public void closeOverlay(){
        putHere.getChildren().clear();
        window.toFront();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createProductCards();


        // Behöver kolla ifall det är första gången eller inte och välja vilken som ska visas först baserat på det

        //setupLogIn();

        //setupShop();

        setupCheckOut();
    }

    private void setupLogIn(){
        window.getChildren().clear();
        window.getChildren().add(new LogIn(this));
        window.toFront();
    }

    public void setupShop(){
        window.getChildren().clear();
        window.getChildren().add(new ShopHolder(this));
        window.toFront();
    }

    public void setupCheckOut(){
        window.getChildren().clear();
        window.getChildren().add(new CheckOutHolder(this));
        window.toFront();
    }

    // Creates the product card map for the shoppingcard
    private void createProductCards(){
        for (Product product : db.getProducts()) {
            ProductCard card = new ProductCard(product, this);
            productCards.put(product.getProductId(), card);
        }

        for (ShoppingItem item : db.getAllShoppingItems()){
            productCards.get(item.getProduct().getProductId()).setUpFromCart(item.getAmount());
        }
    }


    public void openDetailView(int prodId){
        detailView.setupInfo(db.getProduct(prodId));
        openOverlay(detailView);
    }

    public void openEmptyCart(){ openOverlay(EmptyCart); }

    // Opens a given overlay in the center of the screen
    private void openOverlay(AnchorPane overlay){
        putHere.getChildren().clear();

        putHere.setPrefWidth(overlay.getPrefWidth());
        putHere.setPrefHeight(overlay.getPrefHeight());


        putHere.getChildren().add(overlay);

        // Centers the overlay
        double x = (darkPane.getPrefWidth() / 2) - (overlay.getPrefWidth()/2);
        double y = (darkPane.getPrefHeight() / 2) - (overlay.getPrefHeight()/2);

        putHere.setLayoutX(x);
        putHere.setLayoutY(y);
        darkPane.toFront();
    }


    // Adds a product into the shoppingcart
    public void addToCart(int prodId){
        db.addToShoppingCart(prodId);
        productCards.get(prodId).setUpFromCart(1);
    }

    // Removes a product from the shoppingcart
    public void removeFromCart(int prodId) {
        db.removeShoppingItem(prodId);
        productCards.get(prodId).removeFromCart();
    }

    // Removes all products from shoppingcart
    public void removeAllFromCart(){
        for(ShoppingItem item : db.getAllShoppingItems()){
            productCards.get(item.getProduct().getProductId()).removeFromCart();
        }
        db.clearCart();
    }

    // Updates a cart item with a new amount
    public void updateCartItemAmount(int prodId, int newAmount){
        if(newAmount >= 100) { newAmount /= 10; }
        db.updateShoppingItemAmount(prodId, newAmount);
        productCards.get(prodId).setUpFromCart(newAmount);
    }

    // Updates the gridcard
    public void updateGridCard(int prodId) {
        productCards.get(prodId).updateCard();
    }

    // Rounds the corners of a given imageview with a specified amount
    public void roundImage(ImageView img, int amount) {
        Rectangle clip = new Rectangle(img.getFitWidth(), img.getFitHeight());
        clip.setArcWidth(amount);
        clip.setArcHeight(amount);
        img.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img.snapshot(parameters, null);

        img.setClip(null);
        img.setImage(image);
    }

}
