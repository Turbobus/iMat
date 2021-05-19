package iMat.CategoryMenu;

import iMat.Controller;
import iMat.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryItem extends AnchorPane implements CategoryButtonUpdater {

    private final Controller pController;

    @FXML private Button subcategoryButton;
    private final DB database = DB.getInstance();

    private final String name;
    private boolean initialized = false;

    private static final List<SubcategoryItem> allItems = new ArrayList<>();
    private static final List<CategoryButtonUpdater> eventListeners = new ArrayList<>();

    public SubcategoryItem(Controller pController, String name, String itemText) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.pController = pController;

        this.subcategoryButton.setText(itemText);
        this.name = name;

        allItems.add(this);
    }

    @FXML
    private void onAction() {

        List<Product> pc = null;
        switch (this.subcategoryButton.getText()) {
            case "Drycker kalla" -> pc = database.getCategoryProducts(ProductCategory.COLD_DRINKS);
            case "Drycker varma" -> pc = database.getCategoryProducts(ProductCategory.HOT_DRINKS);

            case "Bär" -> pc = database.getCategoryProducts(ProductCategory.BERRY);
            case "Kål" -> pc = database.getCategoryProducts(ProductCategory.CABBAGE);
            case "Potatis, ris" -> pc = database.getCategoryProducts(ProductCategory.POTATO_RICE);
            case "Örtkryddor" -> pc = database.getCategoryProducts(ProductCategory.HERB);

            case "Fisk" -> pc = database.getCategoryProducts(ProductCategory.FISH);
            case "Kött" -> pc = database.getCategoryProducts(ProductCategory.MEAT);

            case "Baljväxter" -> pc = database.getCategoryProducts(ProductCategory.POD);
            case "Mjöl, socker, salt" -> pc = database.getCategoryProducts(ProductCategory.FLOUR_SUGAR_SALT);
            case "Nötter & frön" -> pc = database.getCategoryProducts(ProductCategory.NUTS_AND_SEEDS);
            case "Pasta" -> pc = database.getCategoryProducts(ProductCategory.PASTA);

            case "Citrusfrukter" -> pc = database.getCategoryProducts(ProductCategory.CITRUS_FRUIT);
            case "Exotiska frukter" -> pc = database.getCategoryProducts(ProductCategory.EXOTIC_FRUIT);
            case "Grönsaksfrukter" -> pc = database.getCategoryProducts(ProductCategory.VEGETABLE_FRUIT);
            case "Meloner" -> pc = database.getCategoryProducts(ProductCategory.MELONS);
            case "Rotfrukter" -> pc = database.getCategoryProducts(ProductCategory.ROOT_VEGETABLE);
            case "Stenfrukter" -> pc = database.getCategoryProducts(ProductCategory.FRUIT);

            case "Visa alla" -> pc = showAllEvent();
        }

        for(CategoryListener c : pController.getCategoryListeners()) {
            c.populateCards(pc);
            c.updateBreadCrumbs(pc.get(0).getCategory());
            c.bringToFront();
        }
        updateCategoryButtons(this);
    }

    private void initializeEventListeners() {
        if (!initialized) {
            eventListeners.addAll(allItems);
            eventListeners.add(pController.getShopHolder().getCategoryMenu());
            initialized = true;
        }
    }

    private void updateCategoryButtons(SubcategoryItem clicked) {
        initializeEventListeners();
        for(CategoryButtonUpdater ce : eventListeners) {
            ce.updateButtonStyle(clicked);
        }
    }

    private List<Product> showAllEvent() {
        List<Product> pc = new ArrayList<>();
        switch (this.getName()) {
            case "drinks" -> {
                pc.addAll(database.getCategoryProducts(ProductCategory.COLD_DRINKS));
                pc.addAll(database.getCategoryProducts(ProductCategory.HOT_DRINKS));
            }
            case "vegetables" -> {
                pc.addAll(database.getCategoryProducts(ProductCategory.BERRY));
                pc.addAll(database.getCategoryProducts(ProductCategory.CABBAGE));
                pc.addAll(database.getCategoryProducts(ProductCategory.POTATO_RICE));
                pc.addAll(database.getCategoryProducts(ProductCategory.HERB));
            }
            case "fish and meat" -> {
                pc.addAll(database.getCategoryProducts(ProductCategory.FISH));
                pc.addAll(database.getCategoryProducts(ProductCategory.MEAT));
            }
            case "dryGoods" -> {
                pc.addAll(database.getCategoryProducts(ProductCategory.POD));
                pc.addAll(database.getCategoryProducts(ProductCategory.FLOUR_SUGAR_SALT));
                pc.addAll(database.getCategoryProducts(ProductCategory.NUTS_AND_SEEDS));
                pc.addAll(database.getCategoryProducts(ProductCategory.PASTA));
            }
            case "fruit" -> {
                pc.addAll(database.getCategoryProducts(ProductCategory.CITRUS_FRUIT));
                pc.addAll(database.getCategoryProducts(ProductCategory.EXOTIC_FRUIT));
                pc.addAll(database.getCategoryProducts(ProductCategory.VEGETABLE_FRUIT));
                pc.addAll(database.getCategoryProducts(ProductCategory.MELONS));
                pc.addAll(database.getCategoryProducts(ProductCategory.ROOT_VEGETABLE));
                pc.addAll(database.getCategoryProducts(ProductCategory.FRUIT));
            }
        }
        return pc;
    }

    @Override
    public void updateButtonStyle(SubcategoryItem clicked) {
        for(SubcategoryItem si : allItems) {
            si.subcategoryButton.setId("subcategory_buttons");;
        }
        clicked.subcategoryButton.setId("subcategory_pressed_buttons");
    }

    public String getName() { return this.name; }

    public List<SubcategoryItem> getAllItems() { return allItems; }

    public Button getSubcategoryButton() { return this.subcategoryButton; }

}
