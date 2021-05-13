package iMat.CategoryMenu;

import iMat.ShopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class Vegetables_subcategory extends Subcategory {

    @FXML private Button berriesButton;
    @FXML private Button kaleButton;
    @FXML private Button potatoRiceButton;
    @FXML private Button herbsButton;

    public Vegetables_subcategory(ShopHolder pController) {

        super(pController);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategory_vegetables.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void onBerriesClicked() {
        System.out.println("Bär");
        berriesButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onKaleClicked() {
        System.out.println("Kål");
        kaleButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onPotatoRiceClicked() {
        System.out.println("Potatis, ris");
        potatoRiceButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onHerbsClicked() {
        System.out.println("Örtkryddor");
        herbsButton.setId("subcategory_pressed_buttons");
    }
}
