package controller;

import fxapp.WaterQualityApplication;

/**
 * Created by Ashwin on 11/5/2016.
 */
public class Controller {

    protected WaterQualityApplication mainApp;

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }
}
