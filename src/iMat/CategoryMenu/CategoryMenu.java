package iMat.CategoryMenu;

import iMat.Controller;
import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenu extends AnchorPane implements CategoryButtonUpdater {

    private static CategoryMenu instance = null;
    private final DB database = DB.getInstance();
    private final Controller pController;

    @FXML private Pane drinkPane;
    @FXML private Pane fruitPane;
    @FXML private Pane vegetablePane;
    @FXML private Pane fishAndMeatPane;
    @FXML private Pane dryGoodsPane;

    @FXML private Button homeButton;
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

    private CategoryMenu(Controller pController) {

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
        dairyItem = new SubcategoryItem(pController, "DAIRY", "dairy");
        sweetItem = new SubcategoryItem(pController, "SWEET", "sweet");

        List<SubcategoryItem> allSubcategoryButtons = SubcategoryItem.getAllItems();
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

        homeButton.setId("home_pressed_button");
    }

    public static void initialize(Controller pController) {
        if (instance == null) {
            instance = new CategoryMenu(pController);
        }
    }

    public static CategoryMenu getInstance() {
        return instance;
    }

    public Subcategory createNewSubcategory(Controller pController, String nameOfSubcategory, List<String> subcategoryNames) {
        return new Subcategory(pController, nameOfSubcategory, subcategoryNames);
    }

    @FXML public void toHomePage() {
        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(database.getProducts());
            c.updateBreadCrumbs(null, "home");
            c.bringToFront();
        }
        updateCategoryButtons(new SubcategoryItem(pController, "HOME", "Decoy"));
    }

    @FXML private void displayBread() {
        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(database.getCategoryProducts(ProductCategory.BREAD));
            c.updateBreadCrumbs(ProductCategory.BREAD, null);
            c.bringToFront();
        }
        updateCategoryButtons(this.breadItem);
    }

    @FXML private void displayDairy() {
        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(database.getCategoryProducts(ProductCategory.DAIRIES));
            c.updateBreadCrumbs(ProductCategory.DAIRIES, null);
            c.bringToFront();
        }
        updateCategoryButtons(this.dairyItem);
    }

    @FXML private void displaySweet() {
        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(database.getCategoryProducts(ProductCategory.SWEET));
            c.updateBreadCrumbs(ProductCategory.SWEET, null);
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
        pController.getShopHolder().setupCategories();
        homeButton.setId("home_button");
        drinkButton.setId("category_buttons");
        vegetableButton.setId("category_buttons");
        fishAndMeatButton.setId("category_buttons");
        dryGoodButton.setId("category_buttons");
        fruitButton.setId("category_buttons");

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

            case "HOME" -> homeButton.setId("home_pressed_button");
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

    @FXML public void showAllDrink() {
        SubcategoryItem drink = null;

        for(SubcategoryItem si : drinkSubcategory.getSubcategoryItems()){
            if(si.getName().equals("drinks") && si.getSubcategoryButton().getText().equals("Visa alla")) {
                drink = si;
            }
        }
        try {
            List<Product> products = drink.showAllEvent();
            drink.setShowAll(false);
            for(CategoryListener c : pController.getCategoryListeners()) {
                c.populateCards(products);
                c.updateBreadCrumbs(null, "drinks");
                c.bringToFront();
            }
            updateCategoryButtons(drink);
        } catch(NullPointerException npe) {
            npe.getMessage();
        }
    }

    @FXML public void showAllFruit() {
        SubcategoryItem fruit = null;

        for(SubcategoryItem si : fruitSubcategory.getSubcategoryItems()){
            if(si.getName().equals("fruit") && si.getSubcategoryButton().getText().equals("Visa alla")) {
                fruit = si;
            }
        }
        try {
            List<Product> products = fruit.showAllEvent();
            fruit.setShowAll(false);
            for(CategoryListener c : pController.getCategoryListeners()) {
                c.populateCards(products);
                c.updateBreadCrumbs(null, "fruit");
                c.bringToFront();
            }
            updateCategoryButtons(fruit);
        } catch(NullPointerException npe) {
            npe.getMessage();
        }
    }

    @FXML public void showAllVegetable() {
        SubcategoryItem vegetable = null;

        for(SubcategoryItem si : vegetableSubcategory.getSubcategoryItems()){
            if(si.getName().equals("vegetables") && si.getSubcategoryButton().getText().equals("Visa alla")) {
                vegetable = si;
            }
        }
        try {
            List<Product> products = vegetable.showAllEvent();
            vegetable.setShowAll(false);
            for(CategoryListener c : pController.getCategoryListeners()) {
                c.populateCards(products);
                c.updateBreadCrumbs(null, "vegetables");
                c.bringToFront();
            }
            updateCategoryButtons(vegetable);
        } catch(NullPointerException npe) {
            npe.getMessage();
        }
    }

    @FXML public void showAllFishAndMeat() {
        SubcategoryItem fishAndMeat = null;

        for(SubcategoryItem si : fishAndMeatSubcategory.getSubcategoryItems()){
            if(si.getName().equals("fish and meat") && si.getSubcategoryButton().getText().equals("Visa alla")) {
                fishAndMeat = si;
            }
        }
        try {
            List<Product> products = fishAndMeat.showAllEvent();
            fishAndMeat.setShowAll(false);
            for(CategoryListener c : pController.getCategoryListeners()) {
                c.populateCards(products);
                c.updateBreadCrumbs(null, "fish and meat");
                c.bringToFront();
            }
            updateCategoryButtons(fishAndMeat);
        } catch(NullPointerException npe) {
            npe.getMessage();
        }
    }

    @FXML public void showAllDryGood() {
        SubcategoryItem dryGood = null;

        for(SubcategoryItem si : dryGoodsSubcategory.getSubcategoryItems()){
            if(si.getName().equals("dryGoods") && si.getSubcategoryButton().getText().equals("Visa alla")) {
                dryGood = si;
            }
        }
        try {
            List<Product> products = dryGood.showAllEvent();
            dryGood.setShowAll(false);
            for(CategoryListener c : pController.getCategoryListeners()) {
                c.populateCards(products);
                c.updateBreadCrumbs(null, "dryGoods");
                c.bringToFront();
            }
            updateCategoryButtons(dryGood);
        } catch(NullPointerException npe) {
            npe.getMessage();
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
        subcategoryPane.setLayoutY(getDrinkPane().getLayoutY() - 90);

        subcategoryPane.getChildren().add(getDrinkSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openFruitSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getFruitPane().getPrefWidth());
        subcategoryPane.setLayoutY(getFruitPane().getLayoutY() - 250);

        subcategoryPane.getChildren().add(getFruitSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openVegetableSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getVegetablePane().getPrefWidth());
        subcategoryPane.setLayoutY(getVegetablePane().getLayoutY() - 170);

        subcategoryPane.getChildren().add(getVegetableSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openFishAndMeatSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getFishAndMeatPane().getPrefWidth());
        subcategoryPane.setLayoutY(getFishAndMeatPane().getLayoutY() - 90);

        subcategoryPane.getChildren().add(getFishAndMeatSubcategory().getHolder());
        subcategoryPane.toFront();
    }

    @FXML private void openDryGoodsSubcategory(){
        subcategoryPane.getChildren().clear();
        mouseOnSubCategory = true;

        subcategoryPane.setLayoutX(getDryGoodsPane().getPrefWidth());
        subcategoryPane.setLayoutY(getDryGoodsPane().getLayoutY() - 170);

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
