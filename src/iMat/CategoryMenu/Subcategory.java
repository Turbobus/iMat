package iMat.CategoryMenu;

import iMat.Controller;
import iMat.ShopHolder;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class Subcategory extends AnchorPane {

    private final List<SubcategoryItem> subcategoryItems = new ArrayList<>();

    private final SubcategoryHolder holder;


    public Subcategory(Controller pController, String name, List<String> subcategories) {

        for(String subcategoryName : subcategories) {
            this.subcategoryItems.add(new SubcategoryItem(pController, name, subcategoryName));
        }

        this.holder = new SubcategoryHolder(subcategoryItems);

    }

    public SubcategoryHolder getHolder() { return this.holder; }

}
