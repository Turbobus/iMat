package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ShopGrid extends AnchorPane {

    private final shopHolder pController;
    private final DB db = DB.getInstance();


    @FXML Label currentPlace;           // Vilken kategori/subkategori (stora texten)
    @FXML Label lowerDown;              // " < " mellan de två knapparna
    @FXML Button mainCategoryButton;    // Breadcrumb knappen till vänster. Håller huvudkategori
    @FXML Button subCategoryButton;     // Breadcrumb knappen till höger. Håller subkategorier

    @FXML GridPane cardHolder;          // Griden som håller produktkorten


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
        cardHolder.getChildren().clear();

        int index1 = 0;
        int index2 = 0;


        for(int i = 0; i < 1; i++){
            cardHolder.add(new ProductCard(this), index1, index2);
            index1++;
            if( index1 %4 == 0){
                index2++;
                index1 = 0;
            }
        }
    }




//    private void updateProductGrid(List<Product> products) { //list argument contains products to show
//        productGrid.getChildren().clear();
//        int index1 = 0;
//        int index2 = 0;
//        for (Product product : products){
//            productPanel productItem = productMap.get(product.getName());
//            productGrid.add(productItem, index1, index2);
//
//            index1++;
//            if( index1 %3 == 0){
//                index2++;
//                index1 = 0;
//            }
//        }
//    }


}
