package iMat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class Subcategory extends AnchorPane {

    private final shopHolder pController;

    public Subcategory(shopHolder pController) {

        this.pController = pController;
    }

}
