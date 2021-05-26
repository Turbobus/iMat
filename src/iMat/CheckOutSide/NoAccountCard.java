package iMat.CheckOutSide;

import iMat.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NoAccountCard extends AnchorPane {

    private final Controller pController;
    private final CheckOutPanel nearParent;


    @FXML private AnchorPane haveAccount;

    @FXML
    public void openAccountView(){
        pController.openAccountView();
    }

    @FXML
    public void changeCard(){
        nearParent.setupCardPane();
    }

    public NoAccountCard(Controller pController, CheckOutPanel nearParent){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NoAccountCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        this.nearParent = nearParent;
    }

    public void haveAccount(){
        haveAccount.toFront();
    }
}
