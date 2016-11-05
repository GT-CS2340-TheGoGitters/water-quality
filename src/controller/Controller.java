package controller;

import fxapp.WaterQualityApplication;

/**
 * Created by Ashwin on 11/5/2016.
 */
public interface Controller {

    /**
     * Give the controller access to the WaterQualityApplication
     *
     * @param mainApp the main app that the controller can access
     */
    public void setApp(WaterQualityApplication mainApp);
}
