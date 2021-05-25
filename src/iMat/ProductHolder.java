package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;

public interface ProductHolder {


    void checkAllInCart();

    void checkAllOutOfCart();

    void putAllInCart();

    //void setButtonPutAllInCart(ActionEvent event);

    void takeOutOfCart();

    //void setButtonTakeOutOfCart(ActionEvent event);


    List<ProductItem> getItems();

    void reload();
}
