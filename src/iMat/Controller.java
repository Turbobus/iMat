package iMat;

import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Controller {


    private static Controller instance = null;
    private IMatDataHandler iMatDataHandler;

    private Controller() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
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
