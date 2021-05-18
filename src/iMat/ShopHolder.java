package iMat;

import iMat.CategoryMenu.CategoryMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class ShopHolder extends AnchorPane{
    private Controller pController;


    @FXML AnchorPane mainPane;      // Holder for all "component holder anchorpanes"


    // Holders for individual components. Resides in mainPane above
    @FXML AnchorPane headerPane;
    @FXML AnchorPane categoryPane;
    @FXML AnchorPane gridPane;
    @FXML AnchorPane cartPane;

    public ShopHolder(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopHolder.fxml"));
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
        setupCart();
    }

    private void setupHeader(){
        headerPane.getChildren().clear();
        headerPane.getChildren().add(new header(pController));
        headerPane.toFront();
    }

    private void setupGrid(){
        gridPane.getChildren().clear();
        gridPane.getChildren().add(new ShopGrid(this));
        gridPane.toFront();
    }

    private void setupCategories() {
        categoryPane.getChildren().clear();
        categoryPane.getChildren().add(new CategoryMenu());
        categoryPane.toFront();
    }

    private void setupCart() {
        cartPane.getChildren().clear();
        cartPane.getChildren().add(new ShopCart(pController));
        cartPane.toFront();
    }

    public Map<Integer, ProductCard> getProductCards(){ return pController.getProductCards(); }

}
