package iMat;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.util.IOUtilities;

import java.util.List;


// Connects the program to the backend
public class DB {


    private static DB instance = null;
    private IMatDataHandler iMatDataHandler;

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
}
