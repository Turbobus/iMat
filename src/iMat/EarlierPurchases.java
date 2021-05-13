package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class EarlierPurchases extends AnchorPane{
    @FXML private AnchorPane earlierPurchases;
    @FXML private ImageView closeImgView;
    @FXML private FlowPane purchasesFlowPane;

    private Controller controller;


    public EarlierPurchases(Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("earlierPurchases.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
    }

}
