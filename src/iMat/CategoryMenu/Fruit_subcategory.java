package iMat.CategoryMenu;

import iMat.ShopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class Fruit_subcategory extends Subcategory {

    @FXML private Button citrusFruitButton;
    @FXML private Button exoticFruitButton;
    @FXML private Button vegetableFruitButton;
    @FXML private Button melonButton;
    @FXML private Button rootFruitButton;
    @FXML private Button stoneFruitButton;

    public Fruit_subcategory(ShopHolder pController) {

        super(pController);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategory_fruits.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void onCitrusFruitClicked() {
        System.out.println("Citrusfrukter");
        citrusFruitButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onExoticFruitClicked() {
        System.out.println("Exotiska frukter");
        exoticFruitButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onVegetableFruitClicked() {
        System.out.println("Grönsaksfrukter");
        vegetableFruitButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onMelonClicked() {
        System.out.println("Meloner");
        melonButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onRootFruitClicked() {
        System.out.println("Rotfrukter");
        rootFruitButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onStoneFruitClicked() {
        System.out.println("Stenfrukter");
        stoneFruitButton.setId("subcategory_pressed_buttons");
    }
}
