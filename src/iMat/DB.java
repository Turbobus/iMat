package iMat;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import se.chalmers.cse.dat216.project.*;
import se.chalmers.cse.dat216.project.util.IOUtilities;

import java.util.List;


// Connects the program to the backend
public class DB {


    private static DB instance = null;
    private IMatDataHandler iMatDataHandler;
    private boolean firstRun = true;
    private boolean firstRunReset = false;

    private DB() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
            instance.init();
        }
        return instance;
    }

    private void init() { iMatDataHandler = IMatDataHandler.getInstance(); }

    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public Product getProduct(int prodId) { return iMatDataHandler.getProduct(prodId); }

    public List<Product> getProductCategory(ProductCategory pc) { return iMatDataHandler.getProducts(pc); }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public void addFavourite(int prodId) { iMatDataHandler.addFavorite(prodId); }

    public void addToShoppingCart(int prodId) { iMatDataHandler.getShoppingCart().addProduct(iMatDataHandler.getProduct(prodId)); }

    public boolean isInCart(int prodId){
        List<ShoppingItem> items = iMatDataHandler.getShoppingCart().getItems();
        for (ShoppingItem item : items){
            if (item.getProduct().getProductId() == prodId) {
                return true;
            }
        }
        return false;
    }

    public void updateShoppingItemAmount(int prodId, int amount){
        List<ShoppingItem> items = iMatDataHandler.getShoppingCart().getItems();
        for (ShoppingItem item : items){
            if (item.getProduct().getProductId() == prodId) {
                item.setAmount(amount);
                reloadShoppingCart();
                break;
            }
        }
    }

    public void reloadShoppingCart(){
        iMatDataHandler.getShoppingCart().fireShoppingCartChanged(new ShoppingItem(new Product()), true);
    }

    public void removeShoppingItem(int prodId){
        List<ShoppingItem> items = iMatDataHandler.getShoppingCart().getItems();
        ShoppingItem temp = null;
        for (ShoppingItem item : items){
            if (item.getProduct().getProductId() == prodId) {
                temp = item;
                break;
            }
        }

        iMatDataHandler.getShoppingCart().removeItem(temp);
    }

    public ShoppingItem getShoppingItem(int prodId) {
        List<ShoppingItem> items = iMatDataHandler.getShoppingCart().getItems();
        for (ShoppingItem item : items){
            if (item.getProduct().getProductId() == prodId) {
                return item;
            }
        }
        return null;
    }

    public int getTotalAmountInCart() {
        int total = 0;
        for (ShoppingItem item : getAllShoppingItems()){
            total += item.getAmount();
        }
        return total;
    }

    public void placeOrder(){ iMatDataHandler.placeOrder(true); }

    public List<ShoppingItem> getAllShoppingItems() { return iMatDataHandler.getShoppingCart().getItems(); }

    public double getTotalCartPrice(){ return iMatDataHandler.getShoppingCart().getTotal(); }

    public void clearCart() { iMatDataHandler.getShoppingCart().clear(); }

    public void setCartListener(ShoppingCartListener listener) { iMatDataHandler.getShoppingCart().addShoppingCartListener(listener); }

    public boolean isFavourite(int prodId) { return iMatDataHandler.isFavorite(iMatDataHandler.getProduct(prodId)); }

    public void removeFavourite(int prodId) { iMatDataHandler.removeFavorite(iMatDataHandler.getProduct(prodId)); }

    public void setFirstName(String firstName) { iMatDataHandler.getCustomer().setFirstName(firstName); }

    public void setLastName(String lastName) { iMatDataHandler.getCustomer().setLastName(lastName); }

    public void setAddress(String address) { iMatDataHandler.getCustomer().setAddress(address); }

    public void setPostCode(String postCode) { iMatDataHandler.getCustomer().setPostCode(postCode); }

    public void setPostAddress(String postAddress) { iMatDataHandler.getCustomer().setPostAddress(postAddress);}

    public void setEMail(String eMail) { iMatDataHandler.getCustomer().setEmail(eMail);}

    public void setPhoneNumber(String phoneNumber) { iMatDataHandler.getCustomer().setPhoneNumber(phoneNumber);}

    public void setMobileNumber(String mobileNumber) { iMatDataHandler.getCustomer().setMobilePhoneNumber(mobileNumber);}

    public void setCardType(String cardType) {
        iMatDataHandler.getCreditCard().setCardType(cardType);
    }

    public void setCardNumber(String cardNumber) {
        iMatDataHandler.getCreditCard().setCardNumber(cardNumber);
    }

    public void setHoldersName(String holdersName) {
        iMatDataHandler.getCreditCard().setHoldersName(holdersName);
    }

    public void setValidMonth(int validMonth) {
        iMatDataHandler.getCreditCard().setValidMonth(validMonth);
    }

    public void setValidYear(int validYear) {
        iMatDataHandler.getCreditCard().setValidYear(validYear);
    }

    public void setVerificationCode(int verificationCode) {
        iMatDataHandler.getCreditCard().setVerificationCode(verificationCode);
    }


    public String getFirstName() { return iMatDataHandler.getCustomer().getFirstName(); }

    public String getAddress() {
        return iMatDataHandler.getCustomer().getAddress();
    }

    public String getEmail() {
        return iMatDataHandler.getCustomer().getEmail();
    }

    public String getLastName() {
        return iMatDataHandler.getCustomer().getLastName();
    }

    public String getPhoneNumber() {
        return iMatDataHandler.getCustomer().getPhoneNumber();
    }

    public String getMobilePhoneNumber() {
        return iMatDataHandler.getCustomer().getMobilePhoneNumber();
    }

    public String getPostAddress() {
        return iMatDataHandler.getCustomer().getPostAddress();
    }

    public String getPostCode() {
        return iMatDataHandler.getCustomer().getPostCode();
    }

    public String getCardType() {
        return this.iMatDataHandler.getCreditCard().getCardType();
    }

    public String getCardNumber() {
        return this.iMatDataHandler.getCreditCard().getCardNumber();
    }

    public String getHoldersName() {
        return this.iMatDataHandler.getCreditCard().getHoldersName();
    }

    public int getValidMonth() {
        return this.iMatDataHandler.getCreditCard().getValidMonth();
    }

    public int getValidYear() {
        return this.iMatDataHandler.getCreditCard().getValidYear();
    }

    public int getVerificationCode() {
        return this.iMatDataHandler.getCreditCard().getVerificationCode();
    }

    public boolean isFirstRun() {
        return this.iMatDataHandler.isFirstRun();
    }









}
