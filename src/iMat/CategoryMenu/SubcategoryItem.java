package iMat.CategoryMenu;

import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class SubcategoryItem extends AnchorPane {

    @FXML private Button subcategoryButton;
    private final DB database = DB.getInstance();

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
    }

    @FXML
    private void onAction() {
        switch (this.subcategoryButton.getText()) {
            case "Fisk" :
                System.out.println("You pressed Fisk.");
                database.getProductCategory(ProductCategory.FISH);
                break;

            case "Kött" : System.out.println("You pressed Kött."); break;
            case "Visa alla" : showAllEvent(); break;
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
