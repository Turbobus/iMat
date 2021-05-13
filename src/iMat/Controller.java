package iMat;

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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller extends AnchorPane implements Initializable {

    private final DB db = DB.getInstance();

    private final Map<Integer, ProductCard> productCards = new HashMap<>();
    public Map<Integer, ProductCard> getProductCards(){ return productCards; }

    private final DetailView detailView = new DetailView(this);
    private final empty_cart empty_cart = new empty_cart();

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

        setupShop();
    }

    private void setupLogIn(){
        window.getChildren().clear();
        window.getChildren().add(new LogIn(this));
        window.toFront();
    }

    public void setupShop(){
        window.getChildren().clear();
        window.getChildren().add(new shopHolder(this));
        window.toFront();
    }

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


    // Tror detta udner kan ts bort. Behövdes i labben när man bytte saker i listan så får se här med

//    private void updateRecipeList(){
//        searchResult.getChildren().clear();
//        for (Recipe recipe : rbc.getRecipes()){
//            searchResult.getChildren().add(recipeListItemMap.get(recipe.hashCode()));
//        }
//    }

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

    public void updateCartItemAmount(int prodId, int newAmount){
        db.updateShoppingItemAmount(prodId, newAmount);
        productCards.get(prodId).setUpFromCart(newAmount);
    }

    public void updateGridCard(int prodId) {
        productCards.get(prodId).updateCard();
    }

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
