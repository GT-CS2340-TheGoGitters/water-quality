package model;

/**
 * Created by Joshua on 10/4/16.
 */
public class ReportLocation {
    private double lattitude;
    private double longitude;

    public ReportLocation(double lat, double lng){
        this.lattitude = lat;
        this.longitude = lng;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
