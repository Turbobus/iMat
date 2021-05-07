package iMat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LogInView {

    private final Controller parentController;

    @FXML private Button nextButton;

    public LogInView(Controller parentController) {
        this.parentController = parentController;
    }

}
