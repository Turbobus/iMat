package iMat.CheckOutSide;

import iMat.Controller;
import iMat.DB;
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

import java.io.IOException;

public class CheckOutProductCard extends AnchorPane {
    private final Controller pController;
    private final DB db = DB.getInstance();
    private final int productId;


    // Green card
    @FXML private AnchorPane greenCard;
    @FXML private Button minusCardButton;
    @FXML private Button plusCardButton;
    @FXML private TextField amountTextCard;
    @FXML private Pane plusButton;

    @FXML private Label gProdName;
    @FXML private Label gPrice;
    @FXML private ImageView gImg;
    @FXML private ImageView gEcoImg;
    @FXML private AnchorPane gfavImg;


    @FXML
    public void decreaseButtonPressed(ActionEvent event){

        // Öppna overlay som frågar om man är säker allternativt någon ångra knapp

        int newValue = Integer.parseInt(amountTextCard.getText()) - 1;

        if (newValue <= 0) {
            pController.removeFromCart(productId);
            //blueCard.toFront();
        } else {
            pController.updateCartItemAmount(productId, newValue);
            amountTextCard.setText("" + newValue);
        }
    }

    @FXML
    public void increaseButtonPressed(ActionEvent event){
        if(!amountTextCard.getText().matches("99")) {
            amountTextCard.setText("" + (Integer.parseInt(amountTextCard.getText()) + 1));
            pController.updateCartItemAmount(productId, (Integer.parseInt(amountTextCard.getText())));
        }
    }

    @FXML
    public void openDetailView(){
        pController.openDetailView(productId);
    }

    public CheckOutProductCard(Product product, Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOutProductCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        this.productId = product.getProductId();


        setupInfo(product);
        setupTextField();
    }


    private void setupInfo(Product product){


        // The green version of the card
        gProdName.setText(product.getName());
        gPrice.setText(product.getPrice() + product.getUnit());
        gPrice.setText(product.getPrice() + "  " + product.getUnit());
        gImg.setImage(db.getImage(product, 262, 177));
        pController.roundImage(gImg, 57);


        // If the product is an eco product, the eco image gets shown
        if (product.isEcological()){
            gEcoImg.setOpacity(1);
        }

        // Sets favourite image and other dynamic things already in the database
        updateCard();
    }

    private void setupTextField(){

        // force the field to be numeric only and updates the amount in shopping cart
        amountTextCard.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {

                amountTextCard.setText(newValue.replaceAll("[^\\d]", ""));

            } else if (newValue.matches("0")){

                amountTextCard.setText("1");

            } else if (!newValue.matches("")){

                pController.updateCartItemAmount(productId, Integer.parseInt(newValue));
            }

            if (!newValue.matches("") && Integer.parseInt(newValue) >= 99){

                plusButton.setId("disabled_plus");
                plusCardButton.setId("green_add_button_disabled");

            } else {
                plusButton.setId("enabled_plus");
                plusCardButton.setId("green_add_button");
            }
        });

        // Clears the field when focused and sets a default value if the field is empty when focus is lost
        amountTextCard.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {
                // Focus gained
                amountTextCard.setText("");

            } else {
                // Focus lost
                if(amountTextCard.getText().matches("")){
                    amountTextCard.setText("1");
                }
            }
        });
    }

    // Sets favourite image and other dynamic things
    public void updateCard(){

        if (!db.isFavourite(productId)){
            gfavImg.setOpacity(0);
        } else {
            gfavImg.setOpacity(1);
        }
    }

    public void setUpFromCart(double amount){
        greenCard.toFront();
        amountTextCard.setText(String.valueOf((int) amount));
    }

}
