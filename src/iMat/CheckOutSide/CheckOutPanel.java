package iMat.CheckOutSide;

import iMat.Controller;
import iMat.DB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CheckOutPanel extends AnchorPane {

    private final Controller pController;

    private ToggleGroup timeToggleGroup = new ToggleGroup();
    private boolean haveSetUpCardPane = false;

    @FXML AnchorPane cardInformationPanel;


    @FXML private RadioButton morning;
    @FXML private RadioButton day;
    @FXML private RadioButton evening;

    @FXML private Label totalPriceOfCart;
    @FXML private Button buyButton;


    @FXML public void placeOrder(){

        // Behöver kolla så kreditkort är inskrivet samt hämta tid från radiobutton

        pController.openPayConfirmation("TID");
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
        radioButtonSetup();
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

                    if (!haveSetUpCardPane){
                        setupCardPane();
                    }

                }
            }
        });
    }

    private void setupCardPane(){
        cardInformationPanel.getChildren().clear();
        cardInformationPanel.getChildren().add(new EnterCardDetails());
    }

}
