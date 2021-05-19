package iMat;

import iMat.CategoryMenu.CategoryListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.util.List;

public class ShopGrid extends AnchorPane implements CategoryListener {

    private final ShopHolder pController;

    @FXML Label currentPlace;           // Vilken kategori/subkategori (stora texten)
    @FXML Label lowerDown;              // " < " mellan de två knapparna
    @FXML Button mainCategoryButton;    // Breadcrumb knappen till vänster. Håller huvudkategori
    @FXML Button subCategoryButton;     // Breadcrumb knappen till höger. Håller subkategorier

    @FXML GridPane cardHolder;          // Griden som håller produktkorten


    public ShopGrid(ShopHolder pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        this.currentPlace.setText("Hem");
        //this.mainCategoryButton.setText("Hem");
        //this.subCategoryButton.setText("Hem");

       populateCards(DB.getInstance().getProducts());          // Temp Vet inte om vi kommer ha kvar detta
    }

    @Override
    public void updateBreadCrumbs(ProductCategory pc) {
        switch (pc) {
            case COLD_DRINKS -> {
                currentPlace.setText("Drycker kalla");
                mainCategoryButton.setText("Dryck");
                subCategoryButton.setText("Drycker kalla");
            }
            case HOT_DRINKS -> {
                currentPlace.setText("Drycker varma");
                mainCategoryButton.setText("Dryck");
                subCategoryButton.setText("Drycker varma");
            }
            case BERRY -> {
                currentPlace.setText("Bär");
                mainCategoryButton.setText("Grönsaker");
                subCategoryButton.setText("Bär");
            }
            case CABBAGE -> {
                currentPlace.setText("Kål");
                mainCategoryButton.setText("Grönsaker");
                subCategoryButton.setText("Kål");
            }
            case POTATO_RICE -> {
                currentPlace.setText("Potatis, ris");
                mainCategoryButton.setText("Grönsaker");
                subCategoryButton.setText("Potatis, ris");
            }
            case HERB -> {
                currentPlace.setText("Örtkryddor");
                mainCategoryButton.setText("Grönsaker");
                subCategoryButton.setText("Örtkryddor");
            }
            case FISH -> {
                currentPlace.setText("Fisk");
                mainCategoryButton.setText("Kött och fisk");
                subCategoryButton.setText("Fisk");
            }
            case MEAT -> {
                currentPlace.setText("Kött");
                mainCategoryButton.setText("Kött och fisk");
                subCategoryButton.setText("Kött");
            }
            case POD -> {
                currentPlace.setText("Baljväxter");
                mainCategoryButton.setText("Torrvaror");
                subCategoryButton.setText("Baljväxter");
            }
            case FLOUR_SUGAR_SALT -> {
                currentPlace.setText("Mjöl, socker, salt");
                mainCategoryButton.setText("Torrvaror");
                subCategoryButton.setText("Mjöl, socker, salt");
            }
            case NUTS_AND_SEEDS -> {
                currentPlace.setText("Nötter & frön");
                mainCategoryButton.setText("Torrvaror");
                subCategoryButton.setText("Nötter & frön");
            }
            case PASTA -> {
                currentPlace.setText("Pasta");
                mainCategoryButton.setText("Torrvaror");
                subCategoryButton.setText("Pasta");
            }
            case CITRUS_FRUIT -> {
                currentPlace.setText("Citrusfrukter");
                mainCategoryButton.setText("Frukt");
                subCategoryButton.setText("Citrusfrukter");
            }
            case EXOTIC_FRUIT -> {
                currentPlace.setText("Exotiska frukter");
                mainCategoryButton.setText("Frukt");
                subCategoryButton.setText("Exotiska frukter");
            }
            case VEGETABLE_FRUIT -> {
                currentPlace.setText("Grönsaksfrukter");
                mainCategoryButton.setText("Frukt");
                subCategoryButton.setText("Grönsaksfrukter");
            }
            case MELONS -> {
                currentPlace.setText("Meloner");
                mainCategoryButton.setText("Frukt");
                subCategoryButton.setText("Meloner");
            }
            case ROOT_VEGETABLE -> {
                currentPlace.setText("Rotfrukter");
                mainCategoryButton.setText("Frukt");
                subCategoryButton.setText("Rotfrukter");
            }
            case FRUIT -> {
                currentPlace.setText("Stenfrukter");
                mainCategoryButton.setText("Frukt");
                subCategoryButton.setText("Stenfrukter");
            }
        }
    }

    @Override
    public void populateCards(List<Product> products){
        cardHolder.getChildren().clear();


        int index1 = 0;
        int index2 = 0;


        for(Product product : products){
            ProductCard card = pController.getProductCards().get(product.getProductId());
            cardHolder.add(card, index1, index2);


            index1++;
            if( index1 %4 == 0){
                index2++;
                index1 = 0;
            }
        }
    }

    @Override
    public void bringToFront() {
        pController.getpController().setupShop();
        System.out.println("Hal");
    }

}
