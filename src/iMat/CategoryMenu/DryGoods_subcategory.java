package iMat.CategoryMenu;

import iMat.ShopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class DryGoods_subcategory extends Subcategory {

    @FXML private Button legumesButton;
    @FXML private Button flourButton;
    @FXML private Button nutsButton;
    @FXML private Button pastaButton;

    public DryGoods_subcategory(ShopHolder pController) {

        super(pController);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategory_dryGoods.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void onLegumesClicked() {
        System.out.println("Baljväter");
        legumesButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onFlourClicked() {
        System.out.println("Mjöl, socker, salt");
        flourButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onNutsClicked() {
        System.out.println("Nötter, frön");
        nutsButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onPastaClicked() {
        System.out.println("Pasta");
        pastaButton.setId("subcategory_pressed_buttons");
    }
}
