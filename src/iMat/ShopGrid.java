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
        this.mainCategoryButton.setText("Hem");
        this.lowerDown.setText("");
        this.subCategoryButton.setText("");

       populateCards(DB.getInstance().getProducts());          // Temp Vet inte om vi kommer ha kvar detta
    }

    private void setBreadCrumbText(String current, String mainCategory, boolean lower, String subCategory) {
        currentPlace.setText(current);
        mainCategoryButton.setText(mainCategory);
        lowerDown.setText("");
        subCategoryButton.setText("");
        if(lower) {
            lowerDown.setText(">");
            subCategoryButton.setText(subCategory);
        }
    }

    @Override
    public void updateBreadCrumbs(ProductCategory pc, String showAll) {
        if(showAll == null) {
            switch (pc) {
                case BREAD ->  setBreadCrumbText("Bröd", "Bröd", false, "");
                case DAIRIES ->  setBreadCrumbText("Mejeri", "Mejeri", false, "");
                case SWEET ->  setBreadCrumbText("Sötsaker", "Sötsaker", false, "");

                case COLD_DRINKS -> setBreadCrumbText("Drycker kalla", "Dryck", true, "Drycker kalla");
                case HOT_DRINKS -> setBreadCrumbText("Drycker varma", "Dryck", true, "Drycker varma");

                case BERRY -> setBreadCrumbText("Bär", "Grönsaker", true, "Bär");
                case CABBAGE -> setBreadCrumbText("Kål", "Grönsaker", true, "Kål");
                case POTATO_RICE -> setBreadCrumbText("Potatis, ris", "Grönsaker", true, "Potatis, ris");
                case HERB -> setBreadCrumbText("Örtkryddor", "Grönsaker", true, "Örtkryddor");

                case FISH -> setBreadCrumbText("Fisk", "Kött & fisk", true, "Fisk");
                case MEAT -> setBreadCrumbText("Kött", "Kött & fisk", true, "Kött");

                case POD -> setBreadCrumbText("Baljväxter", "Torrvaror", true, "Baljväxter");
                case FLOUR_SUGAR_SALT -> setBreadCrumbText("Mjöl, socker, salt", "Torrvaror", true, "Mjöl, socker, salt");
                case NUTS_AND_SEEDS -> setBreadCrumbText("Nötter & frön", "Torrvaror", true, "Nötter & frön");
                case PASTA -> setBreadCrumbText("Pasta", "Torrvaror", true, "Pasta");

                case CITRUS_FRUIT -> setBreadCrumbText("Citrusfrukter", "Frukt", true, "Citrusfrukter");
                case EXOTIC_FRUIT -> setBreadCrumbText("Exotiska frukter", "Frukt", true, "Exotiska frukter");
                case VEGETABLE_FRUIT -> setBreadCrumbText("Grönsaksfrukter", "Frukt", true, "Grönsaksfrukter");
                case MELONS -> setBreadCrumbText("Meloner", "Frukt", true, "Meloner");
                case ROOT_VEGETABLE -> setBreadCrumbText("Rotfrukter", "Frukt", true, "Rotfrukter");
                case FRUIT -> setBreadCrumbText("Stenfrukter", "Frukt", true, "Stenfrukter");
            }
        }
        else {
            switch (showAll) {
                case "home" -> setBreadCrumbText("Hem", "Hem", false, "");
                case "drinks" -> setBreadCrumbText("Dryck", "Dryck", true, "Visa alla");
                case "fruit" -> setBreadCrumbText("Frukt", "Frukt", true, "Visa alla");
                case "vegetables" -> setBreadCrumbText("Grönsaker", "Grönsaker", true, "Visa alla");
                case "fish and meat" -> setBreadCrumbText("Kött och fisk", "Kött och fisk", true, "Visa alla");
                case "dryGoods" -> setBreadCrumbText("Torrvaror", "Torrvaror", true, "Visa alla");
                default -> setBreadCrumbText(showAll, "Hem", true, showAll);
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
    }

}
