package iMat.CategoryMenu;

import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.List;

public interface CategoryListener {

    void populateCards(List<Product> products);

    void updateBreadCrumbs(ProductCategory pc, String showAll);

    void bringToFront();
}
