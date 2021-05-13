package iMat.CategoryMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SubcategoryItem extends AnchorPane {

    @FXML private Button subcategoryButton;

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
            case "Fisk" -> System.out.println("You pressed Fisk.");
            case "Kött" -> System.out.println("You pressed Kött.");
            case "Bröd" -> System.out.println("You pressed Bröd.");
            case "Mejeri" -> System.out.println("You pressed Mejeri.");
            case "Sötsaker" -> System.out.println("You pressed Sötsaker.");
            case "Visa alla" -> showAllEvent();
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
