package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class header extends AnchorPane {

    private final Controller pController;

    @FXML
    private Button myAccountButton;


    @FXML
    public void accountButtonPressed (ActionEvent event) {
        pController.openAccountView();
    }

    public header(Controller pController) {
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
