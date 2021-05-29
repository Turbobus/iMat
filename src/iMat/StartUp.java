package iMat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartUp extends AnchorPane {

    private final Controller pController;

    @FXML private Button createAccountButton;
    @FXML private Button GuestLogInButton;

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

        Platform.runLater(()->createAccountButton.requestFocus());
    }

    @FXML
    private void createAccount() { pController.setupLogIn(); pController.setLogInFirstNameFocus(); }

    @FXML
    private void setUpShop() { pController.setupShop(); }
}
