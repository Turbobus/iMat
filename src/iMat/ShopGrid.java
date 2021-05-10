package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.IOException;

public class ShopGrid extends AnchorPane {

    private final shopHolder pController;
    private final DB db = DB.getInstance();

    @FXML GridPane cardHolder;
    @FXML Label currentPlace;
    @FXML Button mainCategoryButton;
    @FXML Button subCategoryButton;

    public ShopGrid(shopHolder pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        populateCards();
    }


    private void populateCards(){
        for(int i = 0; i < 1; i++){
            cardHolder.getChildren().add(i, new ProductCard(this));
        }
    }
}
