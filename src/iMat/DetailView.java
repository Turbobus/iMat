package iMat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class DetailView extends AnchorPane {


    public DetailView(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

//        this.pController = pController;
//
//
//
//        setupInfo(product);
//        setupTextField();
    }

}
