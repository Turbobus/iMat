package iMat.CheckOutSide;

import iMat.Controller;
import iMat.ProductCard;
import iMat.ShopGrid;
import iMat.header;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Map;

public class CheckOutHolder extends AnchorPane {



    private final Controller pController;

    // Holders for individual components. Resides in mainPane above
    @FXML AnchorPane headerPane;
    @FXML AnchorPane categoryPane;
    @FXML AnchorPane gridPane;
    @FXML AnchorPane methodPane;

    private boolean mouseOnSubCategory;
    @FXML private void onMouseExitSubcategory() { mouseOnSubCategory = false; }

    public CheckOutHolder(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOutHolder.fxml"));
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
        //setupCategories();
        //setupLeftPanel();
    }


    private void setupHeader(){
        headerPane.getChildren().clear();
        headerPane.getChildren().add(new header(this));
        headerPane.toFront();
    }

    private void setupGrid(){
        gridPane.getChildren().clear();
        gridPane.getChildren().add(new CheckOutGrid(this));
        gridPane.toFront();
    }

    private void setupCategories() {
        categoryPane.getChildren().clear();
        //categoryPane.getChildren().add(categoryMenu);
        categoryPane.toFront();
    }

    private void setupLeftPanel() {
        methodPane.getChildren().clear();
        methodPane.getChildren().add(new AnchorPane());
        methodPane.toFront();
    }


    public Map<Integer, ProductCard> getProductCards(){ return pController.getProductCards(); }
}
