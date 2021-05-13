package iMat.CategoryMenu;

import iMat.shopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenu extends AnchorPane {

    private final shopHolder pController;

    @FXML private Button breadButton;
    @FXML private Button dairyButton;
    @FXML private Button sweetButton;

    private List<String> drinks = new ArrayList<>();
    private Subcategory drinkSubcategory;

    private List<String> vegetables = new ArrayList<>();
    private Subcategory vegetableSubcategory;

    private List<String> fishAndMeat = new ArrayList<>();
    private Subcategory fishAndMeatSubcategory;

    private List<String> dryGoods = new ArrayList<>();
    private Subcategory dryGoodsSubcategory;

    private List<String> fruits = new ArrayList<>();
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

        drinks.add("Visa alla");
        drinks.add("Drycker kalla");
        drinks.add("Drycker varma");
        drinkSubcategory = createNewSubcategory("drinks", drinks);

        vegetables.add("Visa alla");
        vegetables.add("Bär");
        vegetables.add("Kål");
        vegetables.add("Potatis, ris");
        vegetables.add("Örtkryddor");
        vegetableSubcategory = createNewSubcategory("vegetables", vegetables);

        fishAndMeat.add("Visa alla");
        fishAndMeat.add("Fisk");
        fishAndMeat.add("Kött");
        fishAndMeatSubcategory = createNewSubcategory("fish and meat", fishAndMeat);

        dryGoods.add("Visa alla");
        dryGoods.add("Baljväxter");
        dryGoods.add("Mjöl, socker, salt");
        dryGoods.add("Nötter & frön");
        dryGoods.add("Pasta");
        dryGoodsSubcategory = createNewSubcategory("dryGoods", dryGoods);

        fruits.add("Visa alla");
        fruits.add("Citrusfrukter");
        fruits.add("Exotiska frukter");
        fruits.add("Grönsaksfrukter");
        fruits.add("Meloner");
        fruits.add("Rotfrukter");
        fruits.add("Stenfrukter");
        fruitSubcategory = createNewSubcategory("fruit", fruits);
    }

    public Subcategory createNewSubcategory(String nameOfSubcategory, List<String> subcategoryNames) {
        return new Subcategory(nameOfSubcategory, subcategoryNames);
    }

    @FXML
    private void onMouseEnterDrink() {

        //new new_subcategory().button
    }

}
