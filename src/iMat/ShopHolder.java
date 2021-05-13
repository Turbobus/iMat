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

    @FXML FlowPane subcategoryPane;

    CategoryMenu categoryMenu = new CategoryMenu(this);
    private boolean mouseOnSubCategory;

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
        categoryPane.getChildren().add(categoryMenu);
        categoryPane.toFront();
    }

    private void setupCart() {
        cartPane.getChildren().clear();
        cartPane.getChildren().add(new ShopCart(pController));
        cartPane.toFront();
    }

    @FXML private void onMouseExitSubcategory() { mouseOnSubCategory = false; closeSubcategory(); }

    public void closeSubcategory() {
        if (!mouseOnSubCategory) {
            subcategoryPane.getChildren().clear();
            subcategoryPane.toBack();
        }
    }

    public void openDrinkSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(categoryMenu.getDrinkPane().getPrefWidth());
        subcategoryPane.setLayoutY(categoryMenu.getDrinkPane().getLayoutY());

        subcategoryPane.getChildren().add(categoryMenu.getDrinkSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openFruitSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(categoryMenu.getFruitPane().getPrefWidth());
        subcategoryPane.setLayoutY(categoryMenu.getFruitPane().getLayoutY());

        subcategoryPane.getChildren().add(categoryMenu.getFruitSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openVegetableSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(categoryMenu.getVegetablePane().getPrefWidth());
        subcategoryPane.setLayoutY(categoryMenu.getVegetablePane().getLayoutY());

        subcategoryPane.getChildren().add(categoryMenu.getVegetableSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openFishAndMeatSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(categoryMenu.getFishAndMeatPane().getPrefWidth());
        subcategoryPane.setLayoutY(categoryMenu.getFishAndMeatPane().getLayoutY());

        subcategoryPane.getChildren().add(categoryMenu.getFishAndMeatSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openDryGoodsSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(categoryMenu.getDryGoodsPane().getPrefWidth());
        subcategoryPane.setLayoutY(categoryMenu.getDryGoodsPane().getLayoutY());

        subcategoryPane.getChildren().add(categoryMenu.getDryGoodsSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public Map<Integer, ProductCard> getProductCards(){ return pController.getProductCards(); }

}
