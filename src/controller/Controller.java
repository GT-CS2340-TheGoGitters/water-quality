package controller;

import fxapp.WaterQualityApplication;

public class Controller {

    WaterQualityApplication mainApp;

    /**
     * Gives the controller access to the main application
     * @param newApp the new application
     */
    public void setApp(WaterQualityApplication newApp) {
        mainApp = newApp;
    }
}
