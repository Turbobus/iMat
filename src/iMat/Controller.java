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

    private final DetailView detailView = new DetailView(this);

    @FXML AnchorPane window;
    @FXML AnchorPane darkPane;
    @FXML AnchorPane putHere;

    @FXML
    public void closeOverlay(){
        putHere.getChildren().clear();
        window.toFront();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createProductCards();


        // Behöver kolla ifall det är första gången eller inte och välja vilken som ska visas först baserat på det

        //setupLogIn();

        setupShop();
    }

    private void setupLogIn(){
        window.getChildren().clear();
        window.getChildren().add(new LogIn(this));
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

    public void openDetailView(){

        openOverlay(detailView);
    }

    private void openOverlay(AnchorPane overlay){
        putHere.getChildren().clear();

        putHere.setPrefWidth(overlay.getPrefWidth());
        putHere.setPrefHeight(overlay.getPrefHeight());


        putHere.getChildren().add(overlay);

        // Centers the overlay
        double x = (darkPane.getPrefWidth() / 2) - (overlay.getPrefWidth()/2);
        double y = (darkPane.getPrefHeight() / 2) - (overlay.getPrefHeight()/2);

        putHere.setLayoutX(x);
        putHere.setLayoutY(y);
        darkPane.toFront();
    }


    // Tror detta udner kan ts bort. Behövdes i labben när man bytte saker i listan så får se här med

//    private void updateRecipeList(){
//        searchResult.getChildren().clear();
//        for (Recipe recipe : rbc.getRecipes()){
//            searchResult.getChildren().add(recipeListItemMap.get(recipe.hashCode()));
//        }
//    }

}
