package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShopCartItem extends AnchorPane {

    private final Controller pController;
    private final int productId;
    private final DB db = DB.getInstance();

    @FXML Label prodName;
    @FXML Label currentPrice;

    @FXML ImageView prodImg;

    @FXML Button minusCardButton;
    @FXML Button plusCardButton;

    @FXML TextField amountTextCartItem;


    // FXML methods
    @FXML
    public void decreaseButtonPressed(ActionEvent event){
        int newValue = Integer.parseInt(amountTextCartItem.getText()) - 1;

        if (newValue <= 0) {
            pController.removeFromCart(productId);
        } else {
            pController.updateCartItemAmount(productId, newValue);
            amountTextCartItem.setText("" + newValue);
        }
    }

    @FXML
    public void increaseButtonPressed(ActionEvent event){
        amountTextCartItem.setText("" + (Integer.parseInt(amountTextCartItem.getText()) + 1));
        pController.updateCartItemAmount(productId, (Integer.parseInt(amountTextCartItem.getText())));
    }

    public ShopCartItem(ShoppingItem item, Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        this.productId = item.getProduct().getProductId();
        setUp(item);
        setupTextField();
    }

    private void setUp(ShoppingItem item){
        prodName.setText(item.getProduct().getName());
        currentPrice.setText(String.format("%.2f",item.getTotal()) + " kr");
        amountTextCartItem.setText("" + (int) item.getAmount());
        prodImg.setImage(db.getImage(item.getProduct(), 78, 78));
        pController.roundImage(prodImg, 30);
    }

    private void setupTextField(){
        // force the field to be numeric only
        amountTextCartItem.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountTextCartItem.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if(newValue.matches("")){
                amountTextCartItem.setText("1");
            }
//            if(Integer.parseInt(newValue) >= 100){
//                amountTextCartItem.setText("99");
//            }

        });
    }
}
