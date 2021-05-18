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

    @FXML private ImageView bProductImgView;
    @FXML private Label bProductNameLabel;
    @FXML private Label bProductPriceLabel;
    @FXML private Label bAmountLabel;
    @FXML private Button bBuyAgainBtn;


    @FXML private TextField amountTextField;
    @FXML private AnchorPane gProductItem;
    @FXML private AnchorPane bProductItem;


    private ShoppingItem item;
    private SingularPurchase pController;

    public ProductItem(ShoppingItem item, SingularPurchase pController){
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
        setup();
    }

    private void setup(){
        bProductNameLabel.setText(item.getProduct().getName());
        //bProductImgView.setImage(item.getImageName());
        bProductPriceLabel.setText(item.getProduct().getPrice()+" kr");
        bAmountLabel.setText(String.valueOf(item.getAmount()));
    }
    @FXML
    public void decreaseButtonPressed2(ActionEvent event){
        int newValue = Integer.parseInt(amountTextField.getText()) - 1;

        if (newValue <= 0) {
            outOfCart();
            pController.checkAllOutOfCart();
        } else {
            amountTextField.setText("" + newValue);
        }
    }

    @FXML
    public void increaseButtonPressed2(ActionEvent event){

        amountTextField.setText("" + (Integer.parseInt(amountTextField.getText()) + 1));
    }

    @FXML
    public void putInCart(ActionEvent event){
        inCart();
        pController.checkAllInCart();
    }
    public void inCart(){
        gProductItem.toFront();
        inCart = true;
    }

    public boolean isInCart(){
        return inCart;

    }
    public void outOfCart(){
        bProductItem.toFront();
        inCart = false;
    }






}
