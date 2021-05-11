package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class MeatFish_subcategory extends Subcategory {

    @FXML private Button meatButton;
    @FXML private Button fishButton;

    public MeatFish_subcategory(shopHolder pController) {

        super(pController);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategory_meat_fish.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void onMeatClicked() {
        System.out.println("Kött");
        meatButton.setId("subcategory_pressed_buttons");
    }

    @FXML
    private void onFishClicked() {
        System.out.println("Fisk");
        fishButton.setId("subcategory_pressed_buttons");
    }
}
