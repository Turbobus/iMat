package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class DetailView extends AnchorPane {

    private final Controller pController;
    private final DB db = DB.getInstance();
    private int productId;

    // Blue card
    @FXML private AnchorPane blueCard;
    @FXML private Button addToCartButton;

    @FXML private Label bProdName;
    @FXML private Label bPrice;
    @FXML private Label bEco;
    @FXML private Label bCategory;
    @FXML private ImageView bImg;
    @FXML private ImageView bEcoImg;
    @FXML private Button bFavButton;
    @FXML private Pane bHeartIcon;
    @FXML private AnchorPane bfavImg;


    // Green card
    @FXML private AnchorPane greenCard;
    @FXML private Button minusCardButton;
    @FXML private Button plusCardButton;
    @FXML private TextField amountTextCard;

    @FXML private Label gProdName;
    @FXML private Label gPrice;
    @FXML private Label gEco;
    @FXML private Label totalPrice;
    @FXML private Label gCategory;
    @FXML private ImageView gImg;
    @FXML private ImageView gEcoImg;
    @FXML private Button gFavButton;
    @FXML private Pane gHeartIcon;
    @FXML private AnchorPane gfavImg;


    @FXML
    public void closePane(ActionEvent event){
        pController.closeOverlay();
    }

    @FXML
    public void addToCartPressed(ActionEvent event){
        pController.addToCart(productId);
        greenCard.toFront();
    }

    @FXML
    public void decreaseButtonPressed(ActionEvent event){
        int newValue = Integer.parseInt(amountTextCard.getText()) - 1;

        if (newValue <= 0) {
            pController.removeFromCart(productId);
            blueCard.toFront();
        } else {
            pController.updateCartItemAmount(productId, newValue);
            amountTextCard.setText("" + newValue);
        }
    }

    @FXML
    public void increaseButtonPressed(ActionEvent event){
        amountTextCard.setText("" + (Integer.parseInt(amountTextCard.getText()) + 1));
        pController.updateCartItemAmount(productId, (Integer.parseInt(amountTextCard.getText())));
    }

    @FXML
    public void favouriteButtonPressed(ActionEvent event){
        if (db.isFavourite(productId)){
            db.removeFavourite(productId);
        } else {
            db.addFavourite(productId);
        }
        setupFavIcon();
        pController.updateGridCard(productId);
    }

    public DetailView(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        setupTextField();
    }

    public void setupInfo(Product product){
        this.productId = product.getProductId();

        // The blue version of the card
        bProdName.setText(product.getName());
        bPrice.setText(product.getPrice() + "  " + product.getUnit());
        bCategory.setText("Kategori: " + getCategoryName(product.getCategory()));
        bImg.setImage(db.getImage(product, 276, 246));
        pController.roundImage(bImg, 28);


        // The green version of the card
        gProdName.setText(product.getName());
        gPrice.setText(product.getPrice() + product.getUnit());
        gPrice.setText(product.getPrice() + "  " + product.getUnit());
        gCategory.setText("Kategori: " + getCategoryName(product.getCategory()));
        gImg.setImage(db.getImage(product, 276, 246));
        pController.roundImage(gImg, 28);


        // If the product is an eco product, the eco image gets shown end correct text is set
        if (product.isEcological()){
            bEco.setText("Ekologisk: Ja");
            gEco.setText("Ekologisk: Ja");
            bEcoImg.setOpacity(1);
            gEcoImg.setOpacity(1);
        } else {
            bEco.setText("Ekologisk: Nej");
            gEco.setText("Ekologisk: Nej");
            bEcoImg.setOpacity(0);
            gEcoImg.setOpacity(0);
        }
        setupFavIcon();

        if(db.isInCart(productId)){
            setUpFromCart();
        } else { blueCard.toFront(); }
    }

    private void setupFavIcon(){
        if (!db.isFavourite(productId)){
            bFavButton.setText("Lägg till som favorit !");
            gFavButton.setText("Lägg till som favorit !");
            bFavButton.setId("addFavorites_blue");
            bHeartIcon.setId("heartIcon_blue");
            gFavButton.setId("addFavorites_green");
            gHeartIcon.setId("heartIcon_green");
            bfavImg.setOpacity(0);
            gfavImg.setOpacity(0);
        } else {
            bFavButton.setText("Ta bort favorit");
            gFavButton.setText("Ta bort favorit");
            bFavButton.setId("addFavorites_blue_active");
            bHeartIcon.setId("heartIcon_blue_active");
            gFavButton.setId("addFavorites_green_active");
            gHeartIcon.setId("heartIcon_green_active");
            bfavImg.setOpacity(1);
            gfavImg.setOpacity(1);
        }
    }

    private void setUpFromCart(){
        ShoppingItem item = db.getShoppingItem(productId);
        greenCard.toFront();
        amountTextCard.setText("" + (int) item.getAmount());
        totalPrice.setText("Totalt pris: " + item.getTotal() + " kr");
    }

    // Gets the swedish name for the categories
    private String getCategoryName(ProductCategory c){
        return switch (c){
            case POD -> "Baljväxter";
            case BREAD -> "Bröd";
            case BERRY -> "Bär" ;
            case CITRUS_FRUIT -> "Citrusfrukter";
            case HOT_DRINKS -> "Varma drycker";
            case COLD_DRINKS -> "Kalla drycker";
            case EXOTIC_FRUIT -> "Exotiska frukter";
            case FISH -> "Fisk";
            case VEGETABLE_FRUIT -> "Grönsaker";
            case CABBAGE -> "Kål";
            case MEAT -> "Kött";
            case DAIRIES -> "Mejeri";
            case MELONS -> "Meloner";
            case FLOUR_SUGAR_SALT -> "Mjöl, socker, salt";
            case NUTS_AND_SEEDS -> "Frön och nötter";
            case PASTA -> "Pasta";
            case POTATO_RICE -> "Potatis & ris";
            case ROOT_VEGETABLE -> "Rotfrukter";
            case FRUIT -> "Frukter";
            case SWEET -> "Sötsaker";
            case HERB -> "Örter";
        };
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
//            if(Integer.parseInt(newValue) >= 100){
//                amountTextCard.setText("99");
//            }
        });

    }

}
