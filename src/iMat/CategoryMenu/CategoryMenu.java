package iMat.CategoryMenu;

import iMat.Controller;
import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.ProductCategory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenu extends AnchorPane implements CategoryButtonUpdater {

    private final DB database = DB.getInstance();
    private final Controller pController;

    @FXML private Pane drinkPane;
    @FXML private Pane fruitPane;
    @FXML private Pane vegetablePane;
    @FXML private Pane fishAndMeatPane;
    @FXML private Pane dryGoodsPane;

    @FXML private Button breadButton;
    @FXML private Button dairyButton;
    @FXML private Button sweetButton;

    @FXML private Button drinkButton;
    @FXML private Button fruitButton;
    @FXML private Button vegetableButton;
    @FXML private Button fishAndMeatButton;
    @FXML private Button dryGoodButton;

    @FXML private FlowPane subcategoryPane;

    private final SubcategoryItem breadItem;
    private final SubcategoryItem dairyItem;
    private final SubcategoryItem sweetItem;

    private final List<CategoryButtonUpdater> eventListeners = new ArrayList<>();

    private final Subcategory drinkSubcategory;
    private final Subcategory vegetableSubcategory;
    private final Subcategory fishAndMeatSubcategory;
    private final Subcategory dryGoodsSubcategory;
    private final Subcategory fruitSubcategory;

    private boolean mouseOnSubCategory;

    public CategoryMenu(Controller pController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        breadItem = new SubcategoryItem(pController, "BREAD", "bread");
        //breadItem.setVisible(false);
        dairyItem = new SubcategoryItem(pController, "DAIRY", "dairy");
        sweetItem = new SubcategoryItem(pController, "SWEET", "sweet");

        List<SubcategoryItem> allSubcategoryButtons = breadItem.getAllItems();
        eventListeners.addAll(allSubcategoryButtons);
        eventListeners.add(this);


        List<String> items = new ArrayList<>();

        items.add("Drycker varma");
        items.add("Drycker kalla");
        items.add("Visa alla");
        drinkSubcategory = createNewSubcategory(pController, "drinks", items);
        items.clear();

        items.add("Örtkryddor");
        items.add("Potatis, ris");
        items.add("Kål");
        items.add("Bär");
        items.add("Visa alla");
        vegetableSubcategory = createNewSubcategory(pController, "vegetables", items);
        items.clear();

        items.add("Kött");
        items.add("Fisk");
        items.add("Visa alla");
        fishAndMeatSubcategory = createNewSubcategory(pController, "fish and meat", items);
        items.clear();

        items.add("Pasta");
        items.add("Nötter & frön");
        items.add("Mjöl, socker, salt");
        items.add("Baljväxter");
        items.add("Visa alla");
        dryGoodsSubcategory = createNewSubcategory(pController, "dryGoods", items);
        items.clear();

        items.add("Stenfrukter");
        items.add("Rotfrukter");
        items.add("Meloner");
        items.add("Grönsaksfrukter");
        items.add("Exotiska frukter");
        items.add("Citrusfrukter");
        items.add("Visa alla");
        fruitSubcategory = createNewSubcategory(pController, "fruit", items);
        items.clear();
    }

    public Subcategory createNewSubcategory(Controller pController, String nameOfSubcategory, List<String> subcategoryNames) {
        return new Subcategory(pController, nameOfSubcategory, subcategoryNames);
    }

    @FXML private void displayBread() {
        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(database.getCategoryProducts(ProductCategory.BREAD));
            c.bringToFront();
        }
        updateCategoryButtons(this.breadItem);
    }

    @FXML private void displayDairy() {
        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(database.getCategoryProducts(ProductCategory.DAIRIES));
            c.bringToFront();
        }
        updateCategoryButtons(this.dairyItem);
    }

    @FXML private void displaySweet() {
        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(database.getCategoryProducts(ProductCategory.SWEET));
            c.bringToFront();
        }
        updateCategoryButtons(this.sweetItem);
    }

    private void updateCategoryButtons(SubcategoryItem clicked) {
        for(CategoryButtonUpdater ce : eventListeners) {
            ce.updateButtonStyle(clicked);
        }
    }

    @Override
    public void updateButtonStyle(SubcategoryItem clicked) {
        drinkPane.setId("category_multichoice_buttons");
        vegetablePane.setId("category_multichoice_buttons");
        fishAndMeatPane.setId("category_multichoice_buttons");
        dryGoodsPane.setId("category_multichoice_buttons");
        fruitPane.setId("category_multichoice_buttons");

        breadButton.setId("category_buttons");
        dairyButton.setId("category_buttons");
        sweetButton.setId("category_buttons");

        switch (clicked.getName()) {
            case "drinks" -> drinkPane.setId("category_pressed_multichoice_buttons");
            case "vegetables" -> vegetablePane.setId("category_pressed_multichoice_buttons");
            case "fish and meat" -> fishAndMeatPane.setId("category_pressed_multichoice_buttons");
            case "dryGoods" -> dryGoodsPane.setId("category_pressed_multichoice_buttons");
            case "fruit" -> fruitPane.setId("category_pressed_multichoice_buttons");
        }

        switch (clicked.getName()) {
            case "BREAD" -> breadButton.setId("category_pressed_buttons");
            case "DAIRY" -> dairyButton.setId("category_pressed_buttons");
            case "SWEET" -> sweetButton.setId("category_pressed_buttons");
        }

        if(clicked.getSubcategoryButton().getText().equals("Visa alla")) {
            switch (clicked.getName()) {
                case "drinks" -> drinkButton.setId("category_pressed_showAll_multichoice_buttons");
                case "vegetables" -> vegetableButton.setId("category_pressed_showAll_multichoice_buttons");
                case "fish and meat" -> fishAndMeatButton.setId("category_pressed_showAll_multichoice_buttons");
                case "dryGoods" -> dryGoodButton.setId("category_pressed_showAll_multichoice_buttons");
                case "fruit" -> fruitButton.setId("category_pressed_showAll_multichoice_buttons");
            }
        }
    }

    @FXML private void onMouseExitSubcategory() { mouseOnSubCategory = false; closeSubcategory(); }

    @FXML private void closeSubcategory() {
        if (!mouseOnSubCategory) {
            subcategoryPane.getChildren().clear();
            subcategoryPane.toBack();
            subcategoryPane.setLayoutX(-500.0);
        }
    }

    @FXML private void openDrinkSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getDrinkPane().getPrefWidth());
        subcategoryPane.setLayoutY(getDrinkPane().getLayoutY() - getDrinkPane().getHeight()/1.5);

        subcategoryPane.getChildren().add(getDrinkSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openFruitSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getFruitPane().getPrefWidth());
        subcategoryPane.setLayoutY(getFruitPane().getLayoutY() - getFruitSubcategory().getHolder().getHeight()/2);

        subcategoryPane.getChildren().add(getFruitSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openVegetableSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getVegetablePane().getPrefWidth());
        subcategoryPane.setLayoutY(getVegetablePane().getLayoutY() - getVegetableSubcategory().getHolder().getHeight()/2);

        subcategoryPane.getChildren().add(getVegetableSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openFishAndMeatSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getFishAndMeatPane().getPrefWidth());
        subcategoryPane.setLayoutY(getFishAndMeatPane().getLayoutY() - getFishAndMeatSubcategory().getHolder().getHeight()/2);

        subcategoryPane.getChildren().add(getFishAndMeatSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openDryGoodsSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getDryGoodsPane().getPrefWidth());
        subcategoryPane.setLayoutY(getDryGoodsPane().getLayoutY() - getDryGoodsSubcategory().getHolder().getHeight()/2);

        subcategoryPane.getChildren().add(getDryGoodsSubcategory().getHolder());
        subcategoryPane.toFront();
    }

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
