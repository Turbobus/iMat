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
    @FXML private Pane plusButton;
    @FXML private Button addButton;



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
        // ska hamna i textfield: item.getAmount()
        setupTextField();
        bProductNameLabel.setText(item.getProduct().getName());
        bProductImgView.setImage(db.getImage(item.getProduct(),76,76));
        controller.roundImage(bProductImgView,29);
        bAmountLabel.setText("Antal: " + (int)item.getAmount());

        //green item
        gProductNameLabel.setText(item.getProduct().getName());
        gProductImgView.setImage(db.getImage(item.getProduct(),76,76));
        controller.roundImage(gProductImgView,29);

        productID = item.getProduct().getProductId();


        //gProductPriceLabel.setText(String.format("%.2f",item.getTotal()) + " kr");
        bProductPriceLabel.setText(item.getProduct().getPrice() + "  " + item.getProduct().getUnit());
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
        } else {
            controller.updateCartItemAmount(productID, newValue);
            amountTextField.setText("" + newValue);
        }
    }

    @FXML
    public void increaseButtonPressed2(ActionEvent event){

        if(!amountTextField.getText().matches("99")) {
            amountTextField.setText("" + (Integer.parseInt(amountTextField.getText()) + 1));
            controller.updateCartItemAmount(productID,Integer.parseInt(amountTextField.getText()));
        }
    }

    @FXML
    public void putInCart(ActionEvent event){
        inCart();
        pController.checkAllInCart();
        gProductPriceLabel.setText(String.format("%.2f",item.getAmount()*item.getProduct().getPrice()) + " kr");
        amountTextField.setText(String.valueOf((int) item.getAmount()));
    }

    public void inCart(){
        gProductItem.toFront();
        inCart = true;
        controller.addToCart(productID);

    }

    public boolean isInCart(){
        return inCart;

    }
    public void outOfCart(){
        bProductItem.toFront();
        inCart = false;
        controller.removeFromCart(productID);
        pController.getController().makeBlue(productID);
        pController.checkAllOutOfCart(); //behövs kansek ej

    }

    public void setUpFromCart(double amount){
        gProductItem.toFront();
        gProductPriceLabel.setText(String.format("%.2f",item.getProduct().getPrice()*amount) + " kr");
        amountTextField.setText(String.valueOf((int) amount));
        inCart = true;
        pController.checkAllInCart();
    }

    public void makeBlue(){
        bProductItem.toFront();
        //bProductPriceLabel.setText(item.getProduct().getPrice() + "  " + item.getProduct().getUnit());
        inCart = false;
        pController.checkAllOutOfCart();
    }

    public void setInCart(boolean b){
        inCart = b;
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
                    amountTextField.setText("" + oldValue);
                    gProductPriceLabel.setText("Totalt pris: " + String.format("%.2f", Integer.parseInt(oldValue) * (item.getProduct().getPrice())) + " kr");
                } else {
                    gProductPriceLabel.setText("Totalt pris: " + String.format("%.2f", Integer.parseInt(newValue) * (item.getProduct().getPrice())) + " kr");
                }

                controller.updateCartItemAmount(productID, Integer.parseInt(newValue));
            }

            if (!newValue.matches("") && Integer.parseInt(newValue) >= 99){

                plusButton.setId("disabled_plus");
                addButton.setId("green_add_button_disabled");

            } else {
                plusButton.setId("enabled_plus");
                addButton.setId("green_add_button");
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
