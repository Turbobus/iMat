package iMat.CategoryMenu;

import iMat.shopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenu extends AnchorPane {

    private final shopHolder pController;

    @FXML private Button breadButton;
    @FXML private Button dairyButton;
    @FXML private Button sweetButton;

    private final Subcategory drinkSubcategory;
    private Subcategory vegetableSubcategory;
    private Subcategory fishAndMeatSubcategory;
    private Subcategory dryGoodsSubcategory;
    private Subcategory fruitSubcategory;

    public CategoryMenu(shopHolder pController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        List<String> items = new ArrayList<>();

        items.add("Visa alla");
        items.add("Drycker kalla");
        items.add("Drycker varma");
        drinkSubcategory = createNewSubcategory("drinks", items);
        items.clear();

        items.add("Visa alla");
        items.add("Bär");
        items.add("Kål");
        items.add("Potatis, ris");
        items.add("Örtkryddor");
        vegetableSubcategory = createNewSubcategory("vegetables", items);
        items.clear();

        items.add("Visa alla");
        items.add("Fisk");
        items.add("Kött");
        fishAndMeatSubcategory = createNewSubcategory("fish and meat", items);
        items.clear();

        items.add("Visa alla");
        items.add("Baljväxter");
        items.add("Mjöl, socker, salt");
        items.add("Nötter & frön");
        items.add("Pasta");
        dryGoodsSubcategory = createNewSubcategory("dryGoods", items);
        items.clear();

        items.add("Visa alla");
        items.add("Citrusfrukter");
        items.add("Exotiska frukter");
        items.add("Grönsaksfrukter");
        items.add("Meloner");
        items.add("Rotfrukter");
        items.add("Stenfrukter");
        fruitSubcategory = createNewSubcategory("fruit", items);
        items.clear();
    }

    public Subcategory createNewSubcategory(String nameOfSubcategory, List<String> subcategoryNames) {
        return new Subcategory(nameOfSubcategory, subcategoryNames);
    }

    @FXML
    private void onBreadClicked() {
        System.out.println("Bröd");
    }

    public Subcategory getDrinkSubcategory() { return this.drinkSubcategory; }

    public Subcategory getVegetableSubcategory() { return vegetableSubcategory; }

    public Subcategory getFishAndMeatSubcategory() { return fishAndMeatSubcategory; }

    public Subcategory getDryGoodsSubcategory() { return dryGoodsSubcategory; }

    public Subcategory getFruitSubcategory() { return fruitSubcategory; }


}
