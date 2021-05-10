package iMat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller extends AnchorPane implements Initializable {

    private final DB db = DB.getInstance();

    @FXML AnchorPane window;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        test();
    }

    private void test(){
        window.getChildren().clear();
        window.getChildren().add(new logIn(this));
    }
}
