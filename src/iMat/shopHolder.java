package iMat;

import iMat.CategoryMenu.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class shopHolder extends AnchorPane{
    private Controller pController;

    @FXML AnchorPane mainPane;      // Holder for all "component holder anchorpanes"


    // Holders for individual components. Resides in mainPane above
    @FXML AnchorPane headerPane;
    @FXML AnchorPane categoryPane;
    @FXML AnchorPane gridPane;
    @FXML AnchorPane cartPane;

    public shopHolder(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shopHolder.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        setupHeader();
        setupGrid();
        setupCategories();

        testSubcategory();
    }

    private void setupHeader(){
        headerPane.getChildren().clear();
        headerPane.getChildren().add(new header(this));
        headerPane.toFront();
    }

    private void setupGrid(){
        gridPane.getChildren().clear();
        gridPane.getChildren().add(new ShopGrid(this));
        gridPane.toFront();
    }

    private void setupCategories() {
        categoryPane.getChildren().clear();
        categoryPane.getChildren().add(new CategoryMenu(this));
        categoryPane.toFront();
    }

    private void testSubcategory() {
        //cartPane.getChildren().clear();
        //cartPane.getChildren().add(new ShopCart());
        //cartPane.toFront();

        CategoryMenu categoryMenu = new CategoryMenu(this);
        cartPane.getChildren().clear();
        cartPane.getChildren().add(categoryMenu.getVegetableSubcategory().getHolder());
        cartPane.toFront();

    }

    public Map<Integer, ProductCard> getProductCards(){ return pController.getProductCards(); }

}
