package com.tripco.t18.planner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {

    public Place[] places;

    public Map(Place[] places){
        this.places = places;
    }

    public String kml(){
        String KML_as_String = "";

        BufferedReader reader;

        try {

            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/BaseKML.kml")));

        } catch (Exception e) {

            return "";

        }

        String line_of_KMLfile = "";

        try {

            while ((line_of_KMLfile = reader.readLine()) != null) {

                KML_as_String += line_of_KMLfile;

                if (line_of_KMLfile.equals("                <coordinates>")) {
                    String splice_add = spliceKML();
                    KML_as_String += splice_add;
                }

            }

        } catch (Exception e) {
            // TODO
        }

        //Return String of the KML File

        return KML_as_String;
    }

    private String spliceKML(){

        Place place;
        String coordinates = "                     ";

        for (int i = 0; i < places.length; i++) {
            place = places[i % places.length];

            coordinates += String.valueOf(place.longitude) + ", " + String.valueOf(place.latitude) + " ";

        }

        return coordinates;
    }

    public String svg() {


        String SVG_as_String = "";

        //Try to load the SVG file located in resources and throw an error if not found.
        BufferedReader reader;

        try {

            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/World_map_with_nations.svg")));

        } catch (Exception e) {

            return "";

        }

        String line_of_SVGfile = "";

        try {

            while ((line_of_SVGfile = reader.readLine()) != null) {

                SVG_as_String += line_of_SVGfile;

                if (line_of_SVGfile.equals("       id=\"Antarctique\" />")) {
                    String splice_add = spliceSVG();
                    SVG_as_String += splice_add;
                }

            }

        } catch (Exception e) {
            // TODO
        }

        //Return String of the SVG File

        return SVG_as_String;

    }

    private String spliceSVG() {

        String dots;

        String splice_add;

        Place origin;
        Place destination;

        ArrayList<Double> originList_latitude = new ArrayList<>();
        ArrayList<Double> originList_longitude = new ArrayList<>();
        ArrayList<Double> destinationList_latitude = new ArrayList<>();
        ArrayList<Double> destinationList_longitude = new ArrayList<>();

        for (int i = 0; i < places.length; i++) {

            origin = places[i % places.length];
            destination = places[(i + 1) % places.length];

            originList_longitude.add(degreesToPixel(origin.longitude, "longitude"));
            destinationList_longitude.add(degreesToPixel(destination.longitude, "longitude"));

            originList_latitude.add(degreesToPixel(origin.latitude, "latitude"));
            destinationList_latitude.add(degreesToPixel(destination.latitude, "latitude"));

        }



        splice_add = drawLines(originList_latitude, originList_longitude, destinationList_latitude, destinationList_longitude);

        for (int i = 0; i < places.length; i++) {

            dots = "<circle cx=\"";

            dots += originList_longitude.get(i).toString()
                    + "\" cy=\"" + originList_latitude.get(i).toString()
                    + "\" r=\"2\" stroke=\"green\" stroke-width=\"1\" fill=\"pink\" />";

            splice_add += dots;

        }

        return splice_add + "</svg>";
    }

    private String drawLines(ArrayList originLat, ArrayList originLong, ArrayList destinationLat, ArrayList destinationLong){

        String splice = "\n<svg\n" + "        xmlns:svg=\"http://www.w3.org/2000/svg\"\n"
                + "        xmlns=\"http://www.w3.org/2000/svg\"\n" + "        version=\"1.0\"\n"
                + "        width=\"1024\"\n" + "        height=\"512\"\n" + "        id=\"svg2339\">";
        String line;
        Place origin;
        Place destination;

        for (int i = 0; i < places.length; i++) {

            origin = places[i % places.length];
            destination = places[(i + 1) % places.length];

            if(checkForWrap(origin.longitude, destination.longitude) == false) {
                line = "<line x1=\"" + originLong.get(i).toString() + "\" y1=\""
                        + originLat.get(i).toString() + "\" x2=\"" + destinationLong.get(i).toString() + "\" y2=\""
                        + destinationLat.get(i).toString() + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                splice += line;
            }
            else{

                if(origin.longitude <= 0) {

                    line = "<line x1=\"" + String.valueOf(degreesToPixel(origin.longitude, "longitude")) + "\" y1=\""
                            + String.valueOf(degreesToPixel(origin.latitude, "latitude")) + "\" x2=\"" + String.valueOf(degreesToPixel(destination.longitude - 360, "longitude")) + "\" y2=\""
                            + String.valueOf(degreesToPixel(destination.latitude, "latitude")) + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                    splice += line;

                    line = "<line x1=\"" + String.valueOf(degreesToPixel(origin.longitude + 360, "longitude")) + "\" y1=\""
                            + String.valueOf(degreesToPixel(origin.latitude, "latitude")) + "\" x2=\"" + String.valueOf(degreesToPixel(destination.longitude, "longitude")) + "\" y2=\""
                            + String.valueOf(degreesToPixel(destination.latitude, "latitude")) + "\" style=\"stroke:rgb(128,0,128);stroke-width:2\" />";
                    splice += line;

                }
            }

        }

        return splice;

    }

    private double degreesToPixel(double deg, String type) {

        if (type.equals("longitude")) {

            return pixelLongitude(deg);

        } else if (type.equals("latitude")) {

            return pixelLatitude(deg);

        } else {
            return -1;
        }

    }

    private double pixelLongitude(double deg){

        return ((800.00 / 360.00) * (180.00 + deg));

    }

    private double pixelLatitude(double deg){

        if (0 >= deg) {
            return Math.abs(((400.00 / 180.00) * (90.00 + Math.abs(deg))));
        }
        else{
            return Math.abs(((400.00 / 180.00) * (-90.00 + deg)));
        }

    }

    private boolean checkForWrap(double originLong, double destLong){

        if (Math.abs((originLong-destLong)) > 180){
            return true;
        }
        else{
            return false;
        }

    }

}
