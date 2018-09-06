package com.tripco.t18.planner;

public class Distance {

    //Inisitalizing class variables
    private Place origin;
    private Place destination;
    private String type;
    private int version;
    private String units;
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

        //Creates top half of the vincenty formula
        double tophav = Math.sqrt(Math.pow(2, (Math.cos(latitude_destination) * Math.sin(difflon))) +
        Math.pow(2, (Math.cos(latitude_origin) * Math.sin(latitude_destination) - (Math.sin(latitude_origin) * Math.cos(latitude_destination) * Math.cos(difflon)))));

        //Creates bottom half of the vincenty formula
        double bothav = (Math.sin(latitude_origin) * Math.sin(latitude_destination)) + (Math.cos(latitude_origin) * Math.cos(latitude_destination) * Math.cos(difflon));

        //Gets the vincenty number to be multiplied by the radius of the earth
        double vincenty = Math.atan2(tophav, bothav);
        vincenty = Math.round(vincenty);

        //Result will store the value before going into the class variable distance
        int result = 0;
        switch (units) { //Switch statement to determine units, right now it only has miles and defaults to miles
            case "miles":
                result = (int)(radius_miles * vincenty);
                distance = result;
            case "nautical miles":
                result = (int)(radius_nautical_miles * vincenty);
                distance = result;
            case "kilometers":
                result = (int)(radius_kilometers * vincenty);
                distance = result;
            default:
                result = (int)(radius_miles * vincenty);
                distance = result;
            }
        return distance;
    }
}
