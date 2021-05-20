package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;



public class ProductItem extends AnchorPane {
    private boolean inCart = false;

    private final DB db = DB.getInstance();

    @FXML private AnchorPane bProductItem;
    @FXML private ImageView bProductImgView;
    @FXML private Label bProductNameLabel;
    @FXML private Label bProductPriceLabel;
    @FXML private Label bAmountLabel;
    @FXML private Button bBuyAgainBtn;


    @FXML private TextField amountTextField;
    @FXML private AnchorPane gProductItem;
    @FXML private Label gProductNameLabel;
    @FXML private Label gProductPriceLabel;
    @FXML private ImageView gProductImgView;



    private ShoppingItem item;
    private ProductHolder pController;
    private Controller controller;
    private int productID;

    public ProductItem(ShoppingItem item, ProductHolder pController, Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.item = item;
        this.pController = pController;
        this.controller=controller;
        setup();
    }

    private void setup(){
        //blue item
        bProductNameLabel.setText(item.getProduct().getName());
        bProductImgView.setImage(db.getImage(item.getProduct(),76,76));
        controller.roundImage(bProductImgView,29);
        bProductPriceLabel.setText(item.getProduct().getPrice()+" kr");
        bAmountLabel.setText(String.valueOf(item.getAmount()));

        //green item
        gProductNameLabel.setText(item.getProduct().getName());
        gProductImgView.setImage(db.getImage(item.getProduct(),76,76));
        controller.roundImage(gProductImgView,29);
        gProductPriceLabel.setText(item.getProduct().getPrice()+" kr");
        // ska hamna i textfield: item.getAmount()
        setupTextField();
        productID = item.getProduct().getProductId();
    }

    public ShoppingItem getShoppingItem(){
        return item;
    }
    public void hideLabel(){
        bAmountLabel.setOpacity(0);

    }
    @FXML
    public void decreaseButtonPressed2(ActionEvent event){
        int newValue = Integer.parseInt(amountTextField.getText()) - 1;

        if (newValue <= 0) {
            outOfCart();
            controller.removeFromCart(productID);
        } else {
            controller.updateCartItemAmount(productID, newValue);
            amountTextField.setText("" + newValue);
        }
    }

    @FXML
    public void increaseButtonPressed2(ActionEvent event){

        amountTextField.setText("" + (Integer.parseInt(amountTextField.getText()) + 1));
        controller.updateCartItemAmount(productID,Integer.parseInt(amountTextField.getText()));
    }

    @FXML
    public void putInCart(ActionEvent event){
        inCart();
        pController.checkAllInCart();
    }
    public void inCart(){
        gProductItem.toFront();
        controller.addToCart(productID);
        inCart = true;
    }

    public boolean isInCart(){
        return inCart;

    }
    public void outOfCart(){
        bProductItem.toFront();
        controller.removeFromCart(productID);
        inCart = false;
    }

    public void setUpFromCart(double amount){
        gProductItem.toFront();
        amountTextField.setText(String.valueOf((int) amount));
    }



    private void setupTextField(){

        // force the field to be numeric only and updates the amount in shopping cart
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                amountTextField.setText(newValue.replaceAll("[^\\d]", ""));

            } else if (newValue.matches("0")){

                amountTextField.setText("1");

            } else if (!newValue.matches("")){

                if(Integer.parseInt(newValue) >= 100){
                    amountTextField.setText("" + Integer.parseInt(newValue)/10);
                }

                /*pController.updateCartItemAmount(productId, Integer.parseInt(newValue));
                totalPrice.setText("Totalt pris: " + String.format("%.2f", Integer.parseInt(newValue) * productPrice) + " kr");

                 */
            }
        });

        // Clears the field when focused and sets a default value if the field is empty when focus is lost
        amountTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {
                // Focus gained
                amountTextField.setText("");

            } else {
                // Focus lost
                if(amountTextField.getText().matches("")){
                    amountTextField.setText("1");
                }
            }
        });

    }






}
