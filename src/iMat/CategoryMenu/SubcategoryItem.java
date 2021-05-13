package iMat.CategoryMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SubcategoryItem extends AnchorPane {

    @FXML private Button subcategoryButton;

    public SubcategoryItem(String itemText) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.subcategoryButton.setText(itemText);
    }

    @FXML
    private void onAction() {
        switch (this.subcategoryButton.getText()) {
            case "Fisk" -> System.out.println("You pressed Fisk.");
            case "Kött" -> System.out.println("You pressed Kött.");
        }
    }

}
