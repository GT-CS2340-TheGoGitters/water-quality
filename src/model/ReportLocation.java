package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

import java.io.Serializable;

public class ReportLocation implements Serializable{
    private static final int EARTH_RADIUS_MILES = 3959;

    private double latitude;
    private double longitude;

    public ReportLocation(double lat, double lng){
        this.latitude = lat;
        this.longitude = lng;
    }

    // This is public because future development may want to access the latitude.
    @SuppressWarnings("WeakerAccess")
    public double getLatitude() {
        return latitude;
    }

    // This is public because future development may want to access the longitude
    @SuppressWarnings("WeakerAccess")
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

    /**
     * Gets the distance in miles between 2 locations
     *
     * @param loc2 The second location
     * @return The distance in miles between this location and the other location.
     */
    public double distFrom(ReportLocation loc2) {
        double dLat = Math.toRadians(loc2.getLatitude() - latitude);
        double dLng = Math.toRadians(loc2.getLongitude() - longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(loc2.getLatitude())) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS_MILES * c);
    }
}
