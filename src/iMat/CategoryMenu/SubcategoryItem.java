package iMat.CategoryMenu;

import iMat.Controller;
import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.util.List;

public class SubcategoryItem extends AnchorPane {

    private final Controller pController;

    @FXML private Button subcategoryButton;
    private final DB database = DB.getInstance();

    private final String name;

    public SubcategoryItem(Controller pController, String name, String itemText) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.pController = pController;

        this.subcategoryButton.setText(itemText);
        this.name = name;
    }

    @FXML
    private void onAction() {

        List<Product> pc = null;
        switch (this.subcategoryButton.getText()) {
            case "Fisk" -> pc = database.getCategoryProducts(ProductCategory.FISH);
            case "Kött" -> pc = database.getCategoryProducts(ProductCategory.MEAT);
            case "Visa alla" -> showAllEvent();
        }

        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(pc);
        }

    }

    private void showAllEvent() {
        switch (this.getName()) {
            case "drinks" -> System.out.println("You pressed Visa alla drycker.");
            case "vegetables" -> System.out.println("You pressed Visa alla grönsaker.");
            case "fish and meat" -> System.out.println("You pressed Visa alla Kött och fisk");
            case "dryGoods" -> System.out.println("You pressed Visa alla torrvaror");
            case "fruit" -> System.out.println("You pressed Visa alla frukt");
        }
    }

    public String getName() { return this.name; }

}
