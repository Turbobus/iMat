package iMat.CategoryMenu;

import iMat.shopHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class Subcategory extends AnchorPane {

    private final shopHolder pController;

    List<Button_subcategory> drinkList = new ArrayList<>();
    List<Button_subcategory> vegetableList = new ArrayList<>();
    List<Button_subcategory> fishMeatList = new ArrayList<>();
    List<Button_subcategory> dryGoodsList = new ArrayList<>();
    List<Button_subcategory> fruitsList = new ArrayList<>();

    public Subcategory(shopHolder pController) {

        drinkList.add(new Button_subcategory("Visa alla"));
        drinkList.add(new Button_subcategory("Drycker kalla"));
        drinkList.add(new Button_subcategory("Drycker varma"));

        vegetableList.add(new Button_subcategory("Visa alla"));
        vegetableList.add(new Button_subcategory("Bär"));
        vegetableList.add(new Button_subcategory("Kål"));
        vegetableList.add(new Button_subcategory("Potatis, ris"));
        vegetableList.add(new Button_subcategory("Örtkryddor"));

        fishMeatList.add(new Button_subcategory("Visa alla"));
        fishMeatList.add(new Button_subcategory("Fisk"));
        fishMeatList.add(new Button_subcategory("Kött"));

        dryGoodsList.add(new Button_subcategory("Visa alla"));
        dryGoodsList.add(new Button_subcategory("Baljväxter"));
        dryGoodsList.add(new Button_subcategory("Mjöl, socker, salt"));
        dryGoodsList.add(new Button_subcategory("Nötter & frön"));
        dryGoodsList.add(new Button_subcategory("Pasta"));

        fruitsList.add(new Button_subcategory("Visa alla"));
        fruitsList.add(new Button_subcategory("Citrusfrukter"));
        fruitsList.add(new Button_subcategory("Exotiska frukter"));
        fruitsList.add(new Button_subcategory("Grönsaksfrukter"));
        fruitsList.add(new Button_subcategory("Meloner"));
        fruitsList.add(new Button_subcategory("Rotfrukter"));
        fruitsList.add(new Button_subcategory("Stenfrukter"));

        this.pController = pController;
    }

    @FXML
    private void onShowAllClicked() {
        System.out.println("Visa alla");
    }
}
