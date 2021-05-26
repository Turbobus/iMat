package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartUp extends AnchorPane {

    private final Controller pController;

    public StartUp(Controller pController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startUp.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
    }

    @FXML
    private void createAccount() { pController.setupLogIn(); }

    @FXML
    private void setUpShop() { pController.setupShop(); }
}
