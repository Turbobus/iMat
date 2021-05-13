package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductItem extends AnchorPane {
    @FXML private ImageView productImgView;
    @FXML private Label productNameLabel;
    @FXML private Label productPriceLabel;
    @FXML private Label amountLabel;
    @FXML private Button buyAgainBtn;

    private Product product;
    private Controller controller;

    public ProductItem(Product product, Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.controller = controller;
        productNameLabel.setText(product.getName());
        //productImgView.setImage(product.getImageName());
        productPriceLabel.setText(product.getPrice()+" kr");
        //amountLabel.setText(product.get);
    }




}
