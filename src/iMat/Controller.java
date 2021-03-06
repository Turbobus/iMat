package iMat;

import iMat.CategoryMenu.*;
import iMat.CheckOutSide.CheckOutHolder;
import iMat.CheckOutSide.CheckOutProductCard;
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
import java.util.*;
import java.util.List;

public class Controller extends AnchorPane implements Initializable {

    private final DB db = DB.getInstance();

    private final Map<Integer, ProductCard> productCards = new HashMap<>();
    private final Map<Integer, CheckOutProductCard> checkOutProdCards = new HashMap<>();

    private final DetailView detailView = new DetailView(this);
    private final EmptyCart EmptyCart = new EmptyCart(this);
    private final PayConfirmation payConfirmation = new PayConfirmation(this);
    private final PurchaseCompleted purchaseCompleted = new PurchaseCompleted(this);
    private final EpAndFHolder epAndFHolder = new EpAndFHolder(this);
    private final Settings settings = new Settings(this);
    private final Help help = new Help(this);
    private final EpAndFHolder favourites = new EpAndFHolder(this);
    private final Header header = new Header(this);

    private final SubcategoryItem decoyItem = new SubcategoryItem(this, "Decoy", "Decoy");

    private static LogIn logIn;

    private static StartUp startUp;
    private static ShopHolder shopHolder;

    private final List<CategoryListener> categoryListeners = new ArrayList<>();
    private final List<CategoryButtonUpdater> categoryButtonUpdaters = new ArrayList<>();

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


        // Kommetera ut ifall det beh??vs.
        db.reset();




        createProductCards();
        CategoryMenu.initialize(this);

        logIn = new LogIn(this);
        shopHolder = new ShopHolder(this);

        startUp = new StartUp(this);

        categoryListeners.add(getShopHolder().getShopGrid());

        categoryButtonUpdaters.addAll(SubcategoryItem.getAllItems());
        categoryButtonUpdaters.add(CategoryMenu.getInstance());


        // "db.resetFirstRun()" g??r bara s?? att man kommer r??tt vid startup, gamla uppgiter ur databasen tas inte bort
        // Kommentera in och ut vid behov, se "db.reset()" ovan ocks?? ifall det vill g??ras
        //db.resetFirstRun();
        if(db.isFirstRun()){
            setupStartUp();
        } else {
            setupShop();
        }

        //Kommentera in ifall speciela sidor vill testas

        //setupStartUp();
        //setupLogIn();
        //setupShop();
        //setupCheckOut();
    }

    public void setupStartUp() {
        window.getChildren().clear();
        window.getChildren().add(startUp);
        window.toFront();
    }

    public void setupLogIn() {
        window.getChildren().clear();
        window.getChildren().add(logIn);
        window.toFront();
    }

    public void setupShop() {
        window.getChildren().clear();
        window.getChildren().add(shopHolder);
        getShopHolder().setupCategories();
        shopHolder.setupHeader(header);
        window.toFront();
    }

    public void setupCheckOut(){
        for(CategoryButtonUpdater cbu : categoryButtonUpdaters) {
            cbu.updateButtonStyle(decoyItem);
        }
        window.getChildren().clear();
        CheckOutHolder temp = new CheckOutHolder(this);
        temp.setupHeader(header);
        window.getChildren().add(temp);

        window.toFront();
    }

    // Creates the product card map for the shoppingcard
    private void createProductCards(){
        for (Product product : db.getProducts()) {
            ProductCard card = new ProductCard(product, this);
            CheckOutProductCard checkCard = new CheckOutProductCard(product, this);
            productCards.put(product.getProductId(), card);
            checkOutProdCards.put(product.getProductId(), checkCard);
        }

        for (ShoppingItem item : db.getAllShoppingItems()){
            productCards.get(item.getProduct().getProductId()).setUpFromCart(item.getAmount());
        }
    }

    public void openAccountView(boolean fromHeader){
        settings.setupCardPane();
        settings.setupUserInfo();
        settings.setupNextButton(fromHeader);
        openOverlay(settings);
    }


    public void openDetailView(int prodId){
        detailView.setupInfo(db.getProduct(prodId));

        openOverlay(detailView);
    }

    public void openEmptyCart(){ openOverlay(EmptyCart); }

    public void openPayConfirmation(String time, String cardType){

        payConfirmation.setupInfo(time, cardType);

        openOverlay(payConfirmation);
    }

    public void openPurchaseCompleted(){
        openOverlay(purchaseCompleted);
    }

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

    public void openEarlierPurchases(){
        epAndFHolder.showPurchases();
        openOverlay(epAndFHolder);
    }

    public void openHelp(){

        openOverlay(help);
    }

    public void openFavourites(){
        favourites.showFavourites();
        openOverlay(favourites);

    }



    // Adds a product into the shoppingcart
    public void addToCart(int prodId){
        if (!db.isInCart(prodId)) {
            db.addToShoppingCart(prodId);
            productCards.get(prodId).setUpFromCart(1);
        }
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
        checkOutProdCards.get(prodId).setUpFromCart(newAmount);
    }

    // Updates the gridcard
    public void updateGridCard(int prodId) {
        productCards.get(prodId).updateCard();
        checkOutProdCards.get(prodId).updateCard();
    }

    public void search(String word){
        for(CategoryListener c : getCategoryListeners()) {
            c.populateCards(db.getSearchResult(word));
            c.updateBreadCrumbs(null, word);
        }
        for(CategoryButtonUpdater cbu : categoryButtonUpdaters) {
            cbu.updateButtonStyle(decoyItem);
        }

        setupShop();
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

    public void setLogInFirstNameFocus() { logIn.setFirstNameFocus(); }

    public Map<Integer, ProductCard> getProductCards(){ return productCards; }
    public Map<Integer, CheckOutProductCard> getCheckOutProductCards(){ return checkOutProdCards; }

    public ShopHolder getShopHolder() { return shopHolder; }

    public List<CategoryListener> getCategoryListeners() { return this.categoryListeners; }
}
