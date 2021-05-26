package iMat.CheckOutSide;

import iMat.Controller;
import iMat.DB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;

public class CheckOutPanel extends AnchorPane implements ShoppingCartListener {

    private final Controller pController;

    private ToggleGroup timeToggleGroup = new ToggleGroup();
    private boolean haveSetUpCardPane = false;
    private boolean isButtonsActive = false;
    private boolean haveAccount = false;
    private String time;
    private final EnterCardDetails enterCardDetails = new EnterCardDetails(this);
    private final NoAccountCard noAccountCard;

    @FXML AnchorPane cardInformationPanel;


    @FXML private RadioButton morning;
    @FXML private RadioButton day;
    @FXML private RadioButton evening;

    @FXML private Label totalPriceOfCart;
    @FXML private Button buyButton;
    @FXML private Pane payArrow;


    @FXML public void placeOrder(){
        if (isButtonsActive) {
            pController.openPayConfirmation(time, enterCardDetails.getCardType());
        }
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
        noAccountCard = new NoAccountCard(pController, this);

        DB.getInstance().setCartListener(this);
        DB.getInstance().setCheckOutUpdater(this);
        radioButtonSetup();
        updateTotalPrice();
        updateButtonState();
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

                        if(DB.getInstance().getFirstName() == null || DB.getInstance().getFirstName().matches("")){
                            setupNoInfo();
                            haveAccount = false;
                        } else {
                            setupCardPane();
                            haveAccount = true;
                        }
                        haveSetUpCardPane = true;
                        updateButtonState();
                    }

                }
            }
        });
    }

    public void setupCardPane(){
        cardInformationPanel.getChildren().clear();
        cardInformationPanel.getChildren().add(enterCardDetails);
    }

    public void haveAccount(){
        noAccountCard.haveAccount();
        haveAccount = true;
    }

    private void setupNoInfo(){
        cardInformationPanel.getChildren().clear();
        cardInformationPanel.getChildren().add(noAccountCard);
    }

    private void updateTotalPrice(){
        totalPriceOfCart.setText("Totalt: " + String.format("%.2f",DB.getInstance().getTotalCartPrice()) + " kr");
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateButtonState();
        updateTotalPrice();
    }

    public void updateButtonState(){
        if (DB.getInstance().getTotalAmountInCart() <= 0 || !enterCardDetails.correctCardInfo() || !haveSetUpCardPane || !haveAccount){
            isButtonsActive = false;
            buyButton.setId("green_button_disabled");
            payArrow.setId("check_out_svg_disabled");
        } else {
            isButtonsActive = true;
            buyButton.setId("green_button");
            payArrow.setId("check_out_svg");
        }
    }
}
