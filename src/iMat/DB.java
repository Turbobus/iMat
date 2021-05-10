package iMat;

import se.chalmers.cse.dat216.project.IMatDataHandler;


// Connects the program to the backend
public class DB {


    private static DB instance = null;
    private IMatDataHandler iMatDataHandler;

    private DB() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
            instance.init();
        }
        return instance;
    }

    private void init() {
        iMatDataHandler = IMatDataHandler.getInstance();
    }



    public void shutDown() {
        iMatDataHandler.shutDown();
    }
}
