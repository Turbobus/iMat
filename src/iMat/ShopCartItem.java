package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShopCartItem extends AnchorPane {

    private final Controller pController;
    private final int productId;
    private final DB db = DB.getInstance();

    @FXML Label prodName;
    @FXML Label currentPrice;

    @FXML private Pane plusButton;

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
        if(!amountTextCartItem.getText().matches("99")) {
            amountTextCartItem.setText("" + (Integer.parseInt(amountTextCartItem.getText()) + 1));
            pController.updateCartItemAmount(productId, (Integer.parseInt(amountTextCartItem.getText())));
        }
    }

    @FXML
    public void openDetailView(){
        pController.openDetailView(productId);
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
        shouldDisableButton(String.valueOf((int) item.getAmount()));
    }

    private void shouldDisableButton(String newValue){
        if (!newValue.matches("") && Integer.parseInt(newValue) >= 99){
            plusButton.setId("disabled_plus");
            plusCardButton.setId("green_add_button_disabled");

        } else {
            plusButton.setId("enabled_plus");
            plusCardButton.setId("green_add_button");
        }
    }

    private void setupTextField(){

        // force the field to be numeric only and updates the amount in shopping cart
        amountTextCartItem.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                amountTextCartItem.setText(newValue.replaceAll("[^\\d]", ""));

            } else if (newValue.matches("0")){

                amountTextCartItem.setText("1");

            } else if (!newValue.matches("")){

                pController.updateCartItemAmount(productId, Integer.parseInt(newValue));
            }

            shouldDisableButton(newValue);
        });

        // Clears the field when focused and sets a default value if the field is empty when focus is lost
        amountTextCartItem.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {
                // Focus gained
                amountTextCartItem.setText("");

            } else {
                // Focus lost
                if(amountTextCartItem.getText().matches("")){
                    amountTextCartItem.setText("1");
                }
            }
        });
    }
}
