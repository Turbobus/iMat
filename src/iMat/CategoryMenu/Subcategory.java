package iMat.CategoryMenu;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class Subcategory extends AnchorPane {

    private final String name;
    private final List<SubcategoryItem> subcategoryItems = new ArrayList<>();

    private final SubcategoryHolder holder;

    public Subcategory(String name, List<String> subcategories) {

        this.name = name;

        for(String subcategoryName : subcategories) {
            this.subcategoryItems.add(new SubcategoryItem(subcategoryName));
        }

        this.holder = new SubcategoryHolder(subcategoryItems);

    }

    public SubcategoryHolder getHolder() { return this.holder; }

    @FXML
    private void onShowAllClicked() {
        System.out.println("Visa alla");
    }
}
