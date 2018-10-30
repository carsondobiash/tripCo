package com.tripco.t18.planner;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Distance {

    //Inisitalizing class variables
    public String type;
    public Integer version;
    public Place origin;
    public Place destination;
    public String units;
    public Integer distance;
    public String unitName;
    public Integer unitRadius;

    public Distance(){}

    public Distance(Place origin, Place destination, String units){
        this.origin = origin;
        this.destination = destination;
        this.units = units;
    }

    public Distance(Place origin, Place destination, String units, String unitName, int unitRadius){
        this.origin = origin;
        this.destination = destination;
        this.units = units;
        this.unitRadius = unitRadius;
        this.unitName = unitName;
    }

    public int calculatedistance() {
        //Seting up latitude and longitude in radians
        double latitude_origin = Math.toRadians(this.origin.latitude);
        double longitude_origin = Math.toRadians(this.origin.longitude);
        double latitude_destination = Math.toRadians(this.destination.latitude);
        double longitude_destination = Math.toRadians(this.destination.longitude);

        //Vincenty formula
        double vincenty = vincenty(latitude_origin, longitude_origin, latitude_destination, longitude_destination);

        //Calling unitChoice to assign the units and unitChoice will also update distance
        unitChoice(units, vincenty);

        return distance;
    }


    public double vincenty(double latitude_origin, double longitude_origin, double latitude_destination, double longitude_destination){
        //Set up the difference in longitude
        double difflon = Math.abs(longitude_origin - longitude_destination);


        //Vincenty formula
        double vincenty = Math.atan2(Math.sqrt(Math.pow(Math.cos(latitude_destination) * Math.sin(difflon), 2) + (Math.pow(Math.cos(latitude_origin) * Math.sin(latitude_destination) -
                Math.sin(latitude_origin) * Math.cos(latitude_destination) * Math.cos(difflon), 2))), (Math.sin(latitude_origin) * Math.sin(latitude_destination) + Math.cos(latitude_origin) *
                Math.cos(latitude_destination) * Math.cos(difflon)));

        return vincenty;
    }

    public void unitChoice(String units, double vincenty){
        //Variables created for calculation
        int radius_miles = 3959;
        int radius_nautical_miles = 3440;
        int radius_kilometers = 6371;
        double result;

        //Switch statement to determine units
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
    }
}