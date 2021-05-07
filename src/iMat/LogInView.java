package iMat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.Customer;

public class LogInView {

    private final Controller parentController;
    private Customer customer;

    @FXML private TextField nameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField postalCodeTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField mailTextField;
    @FXML private TextField telephoneTextField;
    @FXML private TextField mobileTextField;

    @FXML private Button nextButton;

    public LogInView(Controller parentController) {
        this.parentController = parentController;
    }
}
