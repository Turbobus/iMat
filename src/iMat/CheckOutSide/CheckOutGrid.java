package iMat.CheckOutSide;

import iMat.DB;
import iMat.ProductCard;
import iMat.ShopCartItem;
import iMat.ShopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class CheckOutGrid extends AnchorPane {

    private final CheckOutHolder pController;
    private final DB db = DB.getInstance();


    @FXML Label currentPlace;           // Vilken kategori/subkategori (stora texten)
    @FXML Label lowerDown;              // " < " mellan de två knapparna
    @FXML Button mainCategoryButton;    // Breadcrumb knappen till vänster. Håller huvudkategori
    @FXML Button subCategoryButton;     // Breadcrumb knappen till höger. Håller subkategorier

    @FXML GridPane cardHolder;          // Griden som håller produktkorten


    public CheckOutGrid(CheckOutHolder pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOutGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;


        populateCards(DB.getInstance().getAllShoppingItems());          // Temp Vet inte om vi kommer ha kvar detta
    }


    private void populateCards(List<ShoppingItem> products){
        cardHolder.getChildren().clear();


        int index1 = 0;
        int index2 = 0;


        for(ShoppingItem product : products){
            ProductCard card = pController.getProductCards().get(product.getProduct().getProductId());
            cardHolder.add(card, index1, index2);


            index1++;
            if( index1 %4 == 0){
                index2++;
                index1 = 0;
            }
        }
    }
}