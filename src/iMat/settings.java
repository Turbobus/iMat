package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class settings extends AnchorPane {

    private final Controller pController;
    DB db = DB.getInstance();

    //Buttons
    @FXML private Button closeAccount;
    @FXML private Button settings1;
    @FXML private Button settings2;
    @FXML private Button save1;
    @FXML private Button abort1;
    @FXML private Button save2;
    @FXML private Button abort2;


    //Anchor panes
    @FXML private AnchorPane settingsdefault;
    @FXML private AnchorPane settingschanged;

    //Textfields for account window default

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField postAddressTextField;

    @FXML private TextField mailTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField mobileTextField;

    //Textfields for account window changed

    @FXML private TextField firstNameTextField1;
    @FXML private TextField lastNameTextField1;
    @FXML private TextField addressTextField1;
    @FXML private TextField postalCodeTextField1;
    @FXML private TextField postAddressTextField1;

    @FXML private TextField mailTextField1;
    @FXML private TextField telephoneTextField1;
    @FXML private TextField mobileTextField1;


    public settings(Controller pController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pController = pController;
        settingsdefault.toFront();
        setupSettings();
    }

    @FXML
    public void change1pressed(ActionEvent event){

        settingschanged.toFront();
    }

    @FXML
    public void abort1pressed(ActionEvent event){

        settingsdefault.toFront();
    }
    @FXML
    public void save1pressed(ActionEvent event){

        updatesettings();
        settingsdefault.toFront();
    }

    @FXML
    public void closeSettings(ActionEvent event){
        pController.closeOverlay();
    }

    public void updatesettings() {

        firstNameTextField.setText(firstNameTextField.getText());
        lastNameTextField.setText(db.getLastName());
        addressTextField.setText(db.getAddress());
        postAddressTextField.setText(db.getPostAddress());
        postalCodeTextField.setText(db.getPostCode());
        mailTextField.setText(db.getEmail());
        telephoneTextField.setText(db.getPhoneNumber());
        mobileTextField.setText(db.getMobilePhoneNumber());
    }
    public void setupSettings(){

        firstNameTextField.setText(db.getFirstName());
        lastNameTextField.setText(db.getLastName());
        addressTextField.setText(db.getAddress());
        postAddressTextField.setText(db.getPostAddress());
        postalCodeTextField.setText(db.getPostCode());
        mailTextField.setText(db.getEmail());
        telephoneTextField.setText(db.getPhoneNumber());
        mobileTextField.setText(db.getMobilePhoneNumber());

    }


}
