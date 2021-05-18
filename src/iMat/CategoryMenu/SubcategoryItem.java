package iMat.CategoryMenu;

import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryItem extends AnchorPane {

    @FXML private Button subcategoryButton;
    private final DB database = DB.getInstance();
    private final List<CategoryListener> categoryListeners = new ArrayList<>();

    private final String name;

    public SubcategoryItem(String name, String itemText) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.subcategoryButton.setText(itemText);
        this.name = name;

        //categoryListeners.add()
    }

    @FXML
    private void onAction() {

        List<Product> pc = null;
        switch (this.subcategoryButton.getText()) {
            case "Fisk" -> {
                System.out.println("You pressed Fisk.");
                pc = database.getCategoryProducts(ProductCategory.FISH);
            }
            case "Kött" -> System.out.println("You pressed Kött.");
            case "Visa alla" -> showAllEvent();
        }

        for(CategoryListener c : categoryListeners) {
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
