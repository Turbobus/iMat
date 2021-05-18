package iMat.CheckOutSide;

import iMat.DB;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CheckOutPanel extends AnchorPane {


    public CheckOutPanel(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOutPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //this.pController = pController;

    }
}
