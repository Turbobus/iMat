package iMat;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Vegetables_subcategory extends Subcategory {


    public Vegetables_subcategory(shopHolder pController) {

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
}
