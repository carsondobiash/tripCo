package com.tripco.t18.planner;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Distance {

    //Inisitalizing class variables
    private Place origin;
    private Place destination;
    private String type;
    private int version;
    private String units;
    private String unitName;
    private int unitRadius;
    public int distance;

    public int  calculatedistance() {
        //Seting up latitude and longitude in radians
        double latitude_origin = Math.toRadians(this.origin.latitude);
        double longitdue_origin = Math.toRadians(this.origin.longitude);
        double latitude_destination = Math.toRadians(this.destination.latitude);
        double longitude_destination = Math.toRadians(this.destination.longitude);

        //Variables created for calculations
        double difflat = Math.abs(latitude_origin - latitude_destination);
        double difflon = Math.abs(longitdue_origin - longitude_destination);
        int radius_miles = 3959;
        int radius_nautical_miles = 3440;
        int radius_kilometers = 6371;

        //Vincenty formula
        double vincenty = Math.atan2(Math.sqrt(Math.pow(Math.cos(latitude_destination) * Math.sin(difflon), 2) + (Math.pow(Math.cos(latitude_origin) * Math.sin(latitude_destination) -
                Math.sin(latitude_origin) * Math.cos(latitude_destination) * Math.cos(difflon), 2))), (Math.sin(latitude_origin) * Math.sin(latitude_destination) + Math.cos(latitude_origin) *
                Math.cos(latitude_destination) * Math.cos(difflon)));

        //Result will store the value before going into the class variable distance
        double result;

        //Switch statement to determine units, right now it only has miles and defaults to miles
        switch (units) {
            case "miles":
                result = (radius_miles * vincenty);
                distance = (int) Math.round(result);
                break;

            case "nautical miles":
                result = (radius_nautical_miles * vincenty);
                distance = (int) Math.round(result);
                break;

            case "kilometers":
                result = (radius_kilometers * vincenty);
                distance = (int) Math.round(result);
                break;
            case "user defined":
                result = (unitRadius * vincenty);
                distance = (int) Math.round(result);
                break;
            default:
                result = (radius_miles * vincenty);
                distance = (int) Math.round(result);
                break;

            }
        return distance;
    }
}
