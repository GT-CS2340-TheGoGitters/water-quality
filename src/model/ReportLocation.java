package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

/**
 * Created by Joshua on 10/4/16.
 */
public class ReportLocation {
    private double latitude;
    private double longitude;

    public ReportLocation(double lat, double lng){
        this.latitude = lat;
        this.longitude = lng;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }

    public LatLong toLatLong(){
        return new LatLong(latitude, longitude);
    }
}
