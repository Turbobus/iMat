package iMat.CategoryMenu;

import iMat.shopHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public abstract class Subcategory extends AnchorPane {

    private final shopHolder pController;

    @FXML private Button showAllButton;

    public Subcategory(shopHolder pController) {

        this.pController = pController;
    }

    @FXML
    private void onShowAllClicked() {
        System.out.println("Visa alla");
        showAllButton.setId("subcategory_pressed_buttons");
    }
}
