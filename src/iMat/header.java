package iMat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class header extends AnchorPane {
    private shopHolder pController;



    public header(shopHolder pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("header.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
    }
}
