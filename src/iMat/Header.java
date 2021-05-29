package iMat;

import iMat.CategoryMenu.CategoryMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Header extends AnchorPane {

    private Controller pController;


    @FXML private Button earlierPurchaseButton;

    @FXML private Button searchButton;
    @FXML private TextField searchTextField;


    @FXML
    private void earlierPurchaseButtonPressed(){
        pController.openEarlierPurchases();
    }

    @FXML
    private void helpButtonpressed() {
        pController.openHelp();
    }

    @FXML
    private void accountButtonPressed() {
        pController.openAccountView(true);
    }

    @FXML
    private void searchPressed(){
        if (!searchTextField.getText().matches("")) {
            pController.search(searchTextField.getText());
            searchTextField.setText("");
        }
    }

    @FXML
    private void favouritesButtonpressed(ActionEvent event){
        pController.openFavourites();

    }

    @FXML private void logoPressed() { CategoryMenu.getInstance().toHomePage(); }

    public Header(Controller pController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("header.fxml"));
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

    private void setupTextField(){

        // force the field to be numeric only and updates the amount in shopping cart
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("")){
                searchButton.setId("search_button");
            } else {
                searchButton.setId("search_button_active");
            }
        });

        // Clears the field when focused and sets a default value if the field is empty when focus is lost
//        searchTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (newValue) {
//                // Focus gained
//                searchTextField.setText("");
//
//            } else {
//                // Focus lost
//                if(searchTextField.getText().matches("")){
//                    searchTextField.setText("1");
//                }
//            }
//        });
    }

}
