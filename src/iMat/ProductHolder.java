package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface ProductHolder {


    void checkAllInCart();

    void checkAllOutOfCart();

    void putAllInCart();

    //void setButtonPutAllInCart(ActionEvent event);

    void takeOutOfCart();

    //void setButtonTakeOutOfCart(ActionEvent event);
}
