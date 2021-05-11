package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.Objects;

public class ProductCard extends AnchorPane{

    private final Controller pController;
    private final DB db = DB.getInstance();
    private final int productId;


    // Blue card
    @FXML private AnchorPane blueCard;
    @FXML private Button addToCartButtonCard;

    @FXML private Label bProdName;
    @FXML private Label bPrice;
    @FXML private ImageView bImg;
    @FXML private ImageView bEcoImg;
    @FXML private AnchorPane bfavImg;


    // Green card
    @FXML private AnchorPane greenCard;
    @FXML private Button minusCardButton;
    @FXML private Button plusCardButton;
    @FXML private TextField amountTextCard;

    @FXML private Label gProdName;
    @FXML private Label gPrice;
    @FXML private ImageView gImg;
    @FXML private ImageView gEcoImg;
    @FXML private AnchorPane gfavImg;

    @FXML
    public void addToCartPressed(ActionEvent event){
        greenCard.toFront();
    }

    @FXML
    public void decreaseButtonPressed(ActionEvent event){
        int newValue = Integer.parseInt(amountTextCard.getText()) - 1;

        if (newValue <= 0) {
            blueCard.toFront();
        } else {
            amountTextCard.setText("" + newValue);
        }
    }

    @FXML
    public void increaseButtonPressed(ActionEvent event){

        amountTextCard.setText("" + (Integer.parseInt(amountTextCard.getText()) + 1));
    }

    @FXML
    public void openDetailView(){
        pController.openDetailView(productId);
    }

    public ProductCard(Product product, Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
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

        // The blue version of the card
        bProdName.setText(product.getName());
        bPrice.setText(product.getPrice() + "  " + product.getUnit());
        bImg.setImage(db.getImage(product, 266, 181));
        pController.roundImage(bImg, 57);


        // The green version of the card
        gProdName.setText(product.getName());
        gPrice.setText(product.getPrice() + product.getUnit());
        gPrice.setText(product.getPrice() + "  " + product.getUnit());
        gImg.setImage(db.getImage(product, 262, 177));
        pController.roundImage(gImg, 57);


        // If the product is an eco product, the eco image gets shown
        if (product.isEcological()){
            bEcoImg.setOpacity(1);
            gEcoImg.setOpacity(1);
        }

        // Sets favourite image and other dynamic things already in the database
        updateCard();
    }

    private void setupTextField(){

        // force the field to be numeric only
        amountTextCard.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountTextCard.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if(newValue.matches("")){
                amountTextCard.setText("1");
            }
        });
    }

    // Sets favourite image and other dynamic things
    public void updateCard(){

        if (!db.isFavourite(productId)){
            bfavImg.setOpacity(0);
            gfavImg.setOpacity(0);
        } else {
            bfavImg.setOpacity(1);
            gfavImg.setOpacity(1);
        }




    }
}
