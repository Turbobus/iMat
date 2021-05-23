package iMat.CheckOutSide;

import iMat.Controller;
import iMat.DB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;

public class CheckOutPanel extends AnchorPane implements ShoppingCartListener {

    private final Controller pController;

    private ToggleGroup timeToggleGroup = new ToggleGroup();
    private boolean haveSetUpCardPane = false;
    private String time;
    private final EnterCardDetails enterCardDetails = new EnterCardDetails();


    @FXML AnchorPane cardInformationPanel;


    @FXML private RadioButton morning;
    @FXML private RadioButton day;
    @FXML private RadioButton evening;

    @FXML private Label totalPriceOfCart;
    @FXML private Button buyButton;


    @FXML public void placeOrder(){

        // Kolla så att man får placera order från kortuppgifterna
        pController.openPayConfirmation(time, enterCardDetails.getCardType());
    }

    public CheckOutPanel(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOutPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;

        DB.getInstance().setCartListener(this);
        radioButtonSetup();
        updateTotalPrice();
    }


    private void radioButtonSetup() {
        morning.setToggleGroup(timeToggleGroup);
        day.setToggleGroup(timeToggleGroup);
        evening.setToggleGroup(timeToggleGroup);

        timeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (timeToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) timeToggleGroup.getSelectedToggle();

                    switch (selected.getText().charAt(0)){
                        case 'F' -> time = "10 - 13";
                        case 'E' -> time = "13 - 17";
                        case 'K' -> time = "18 - 21";
                    }

                    if (!haveSetUpCardPane){
                        setupCardPane();
                        haveSetUpCardPane = true;
                    }

                }
            }
        });
    }

    private void setupCardPane(){
        cardInformationPanel.getChildren().clear();
        cardInformationPanel.getChildren().add(enterCardDetails);
    }

    private void updateTotalPrice(){
        totalPriceOfCart.setText("Totalt: " + String.format("%.2f",DB.getInstance().getTotalCartPrice()) + " kr");
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateTotalPrice();
    }
}
