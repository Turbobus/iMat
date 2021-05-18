package iMat;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

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

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
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


    public void addFavourite(int prodId) { iMatDataHandler.addFavorite(prodId); }

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






















}
