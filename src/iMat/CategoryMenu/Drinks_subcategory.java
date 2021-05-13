package iMat.CategoryMenu;

import iMat.ShopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class Drinks_subcategory extends Subcategory {

    @FXML private Button coldDrinksButton;
    @FXML private Button warmDrinksButton;

    public Drinks_subcategory(ShopHolder pController) {

        super(pController);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategory_drinks.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void onColdDrinksClicked() {
        System.out.println("Kalla drycker");
        coldDrinksButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onWarmDrinksClicked() {
        System.out.println("Varma drycker");
        warmDrinksButton.setId("subcategory_pressed_buttons");
    }
}
