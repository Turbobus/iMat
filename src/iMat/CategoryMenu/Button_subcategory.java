package iMat.CategoryMenu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Button_subcategory extends AnchorPane {

    @FXML private Button subcategoryButton;

    public Button_subcategory(String buttonText) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategory_button.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.subcategoryButton.setText(buttonText);
    }

    @FXML
    private void onAction() {
        switch (this.subcategoryButton.getText()) {
            case "Fisk" -> System.out.println("You pressed Fisk.");
            case "Kött" -> System.out.println("You pressed Kött.");
        }
    }

}
