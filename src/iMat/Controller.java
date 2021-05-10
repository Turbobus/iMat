package iMat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller extends AnchorPane implements Initializable {

    private final DB db = DB.getInstance();

    private final Map<Integer, ProductCard> productCards = new HashMap<>();
    public Map<Integer, ProductCard> getProductCards(){ return productCards; }

    @FXML AnchorPane window;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createProductCards();


        // Behöver kolla ifall det är första gången eller inte och välja vilken som ska visas först baserat på det

        setupLogIn();
    }

    private void setupLogIn(){
        window.getChildren().clear();
        window.getChildren().add(new logIn(this));
        window.toFront();
    }

    public void setupShop(){
        window.getChildren().clear();
        window.getChildren().add(new shopHolder(this));
        window.toFront();
    }

    private void createProductCards(){
        for (Product product : db.getProducts()) {
            ProductCard card = new ProductCard(product, this);
            productCards.put(product.getProductId(), card);
        }
    }

//    private void updateRecipeList(){
//        searchResult.getChildren().clear();
//        for (Recipe recipe : rbc.getRecipes()){
//            searchResult.getChildren().add(recipeListItemMap.get(recipe.hashCode()));
//        }
//    }

}
