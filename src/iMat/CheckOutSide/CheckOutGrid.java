package iMat.CheckOutSide;

import iMat.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class CheckOutGrid extends AnchorPane implements ShoppingCartListener {

    private final Controller pController;
    private final DB db = DB.getInstance();


    @FXML Label currentPlace;           // Vilken kategori/subkategori (stora texten)
    @FXML Label lowerDown;              // " < " mellan de två knapparna
    @FXML Label amountLabel;            // Antal varor i kundvagn label
    @FXML Button mainCategoryButton;    // Breadcrumb knappen till vänster. Håller huvudkategori
    @FXML Button subCategoryButton;     // Breadcrumb knappen till höger. Håller subkategorier

    @FXML GridPane cardHolder;          // Griden som håller produktkorten


    @FXML
    public void goHome(){
        pController.setupShop();
    }

    public CheckOutGrid(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOutGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        db.setCartListener(this);
        updateAmountLabel();
        populateCards(DB.getInstance().getAllShoppingItems());          // Temp Vet inte om vi kommer ha kvar detta
    }


    private void populateCards(List<ShoppingItem> products){
        cardHolder.getChildren().clear();


        int index1 = 0;
        int index2 = 0;


        for(ShoppingItem product : products){
            CheckOutProductCard card = pController.getCheckOutProductCards().get(product.getProduct().getProductId());
            cardHolder.add(card, index1, index2);


            index1++;
            if( index1 %3 == 0){
                index2++;
                index1 = 0;
            }
        }
    }

    private void updateAmountLabel(){
        amountLabel.setText("Antal: " + db.getTotalAmountInCart() + " st");
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateAmountLabel();
        populateCards(db.getAllShoppingItems());
    }
}
