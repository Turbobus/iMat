package iMat.CategoryMenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class SubcategoryHolder extends AnchorPane {

    @FXML FlowPane subcategoryPane;
    @FXML AnchorPane mainPane;

    public SubcategoryHolder() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategoryHolder.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public void addSubcategoryItem(SubcategoryItem item) {
        subcategoryPane.getChildren().add(0, item);
        mainPane.setPrefHeight(mainPane.getPrefHeight() + (item.getHeight() + subcategoryPane.getVgap()));
    }
}
