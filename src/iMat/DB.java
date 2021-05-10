package iMat;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

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

    private void init() {
        iMatDataHandler = IMatDataHandler.getInstance();
    }


    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }
}
