package iMat.CategoryMenu;

import iMat.ShopHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenu extends AnchorPane {

    @FXML private Button breadButton;
    @FXML private Pane drinkPane;
    @FXML private Pane fruitPane;
    @FXML private Pane vegetablePane;
    @FXML private Pane fishAndMeatPane;
    @FXML private Pane dryGoodsPane;

    @FXML private Button dairyButton;
    @FXML private Button sweetButton;

    @FXML private FlowPane subcategoryPane;

    private final Subcategory drinkSubcategory;
    private final Subcategory vegetableSubcategory;
    private final Subcategory fishAndMeatSubcategory;
    private final Subcategory dryGoodsSubcategory;
    private final Subcategory fruitSubcategory;

    private boolean mouseOnSubCategory;

    public CategoryMenu() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        List<String> items = new ArrayList<>();

        items.add("Drycker varma");
        items.add("Drycker kalla");
        items.add("Visa alla");
        drinkSubcategory = createNewSubcategory("drinks", items);
        items.clear();

        items.add("Örtkryddor");
        items.add("Potatis, ris");
        items.add("Kål");
        items.add("Bär");
        items.add("Visa alla");
        vegetableSubcategory = createNewSubcategory("vegetables", items);
        items.clear();

        items.add("Kött");
        items.add("Fisk");
        items.add("Visa alla");
        fishAndMeatSubcategory = createNewSubcategory("fish and meat", items);
        items.clear();

        items.add("Pasta");
        items.add("Nötter & frön");
        items.add("Mjöl, socker, salt");
        items.add("Baljväxter");
        items.add("Visa alla");
        dryGoodsSubcategory = createNewSubcategory("dryGoods", items);
        items.clear();

        items.add("Stenfrukter");
        items.add("Rotfrukter");
        items.add("Meloner");
        items.add("Grönsaksfrukter");
        items.add("Exotiska frukter");
        items.add("Citrusfrukter");
        items.add("Visa alla");
        fruitSubcategory = createNewSubcategory("fruit", items);
        items.clear();
    }

    public Subcategory createNewSubcategory(String nameOfSubcategory, List<String> subcategoryNames) {
        return new Subcategory(nameOfSubcategory, subcategoryNames);
    }

    @FXML private void onMouseExitSubcategory() { mouseOnSubCategory = false; closeSubcategory(); }

    public void closeSubcategory() {
        if (!mouseOnSubCategory) {
            subcategoryPane.getChildren().clear();
            subcategoryPane.toBack();
            subcategoryPane.setLayoutX(-500.0);
        }
    }

    public void openDrinkSubcategory(){
        subcategoryPane.getChildren().clear();
        //mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(getDrinkPane().getPrefWidth());
        subcategoryPane.setLayoutY(getDrinkPane().getLayoutY());

        subcategoryPane.getChildren().add(getDrinkSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openFruitSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(getFruitPane().getPrefWidth());
        subcategoryPane.setLayoutY(getFruitPane().getLayoutY());

        subcategoryPane.getChildren().add(getFruitSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openVegetableSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(getVegetablePane().getPrefWidth());
        subcategoryPane.setLayoutY(getVegetablePane().getLayoutY());

        subcategoryPane.getChildren().add(getVegetableSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openFishAndMeatSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(getFishAndMeatPane().getPrefWidth());
        subcategoryPane.setLayoutY(getFishAndMeatPane().getLayoutY());

        subcategoryPane.getChildren().add(getFishAndMeatSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    public void openDryGoodsSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        //Behövs en bättre uträkning av y-koordinat.
        subcategoryPane.setLayoutX(getDryGoodsPane().getPrefWidth());
        subcategoryPane.setLayoutY(getDryGoodsPane().getLayoutY());

        subcategoryPane.getChildren().add(getDryGoodsSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void onMouseLeave() { closeSubcategory(); }

    public Subcategory getDrinkSubcategory() { return this.drinkSubcategory; }

    public Subcategory getVegetableSubcategory() { return vegetableSubcategory; }

    public Subcategory getFishAndMeatSubcategory() { return fishAndMeatSubcategory; }

    public Subcategory getDryGoodsSubcategory() { return dryGoodsSubcategory; }

    public Subcategory getFruitSubcategory() { return fruitSubcategory; }

    public Button getBreadButton() { return breadButton; }

    public Pane getDrinkPane() { return drinkPane; }

    public Pane getFruitPane() { return fruitPane; }

    public Pane getVegetablePane() { return vegetablePane; }

    public Pane getFishAndMeatPane() { return fishAndMeatPane; }

    public Pane getDryGoodsPane() { return dryGoodsPane; }

    public Button getDairyButton() { return dairyButton; }

    public Button getSweetButton() { return sweetButton; }

}
