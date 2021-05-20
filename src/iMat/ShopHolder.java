package iMat;

import iMat.CategoryMenu.CategoryMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Map;

public class ShopHolder extends AnchorPane {
    private final Controller pController;


    @FXML AnchorPane mainPane;      // Holder for all "component holder anchorpanes"

    private final Header header;
    private final ShopGrid shopGrid;
    private final CategoryMenu categoryMenu;

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
        this.header = new Header(pController);
        this.shopGrid = new ShopGrid(this);
        this.categoryMenu = new CategoryMenu(pController);

        setupHeader();
        setupGrid();
        setupCategories();
        setupCart();
    }

    private void setupHeader(){
        headerPane.getChildren().clear();
        headerPane.getChildren().add(this.header);
        headerPane.toFront();
    }

    private void setupGrid(){
        gridPane.getChildren().clear();
        gridPane.getChildren().add(this.shopGrid);
        gridPane.toFront();
    }

    public void setupCategories() {
        categoryPane.getChildren().clear();
        categoryPane.getChildren().add(this.categoryMenu);
        categoryPane.toFront();
    }

    private void setupCart() {
        cartPane.getChildren().clear();
        cartPane.getChildren().add(new ShopCart(pController));
        cartPane.toFront();
    }

    public Map<Integer, ProductCard> getProductCards(){ return pController.getProductCards(); }

    public ShopGrid getShopGrid() { return this.shopGrid; }

    public CategoryMenu getCategoryMenu() { return categoryMenu; }

    public Controller getpController() { return this.pController; }
}
