package iMat;

import java.util.List;

public interface ProductHolder {


    void checkAllInCart();

    void checkAllOutOfCart();

    void putAllInCart();

    //void setButtonPutAllInCart(ActionEvent event);

    void takeOutOfCart();

    //void setButtonTakeOutOfCart(ActionEvent event);

    EpAndFHolder getController();

    void makeBlue(int productId);


    List<ProductItem> getItems();

    void reload();
}
