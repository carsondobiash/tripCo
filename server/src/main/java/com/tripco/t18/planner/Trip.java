package com.tripco.t18.planner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tripco.t18.server.HTTP;
import spark.Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The Trip class supports TFFI so it can easily be converted to/from Json by Gson.
 */
public class Trip {
    // The variables in this class should reflect TFFI.
    public int version;
    public String type;
    public String title;
    public ArrayList<Place> places = new ArrayList<>();
    public Option options;
    public ArrayList<Integer> distances = new ArrayList<>();
    public String map;

    /**
     * The top level method that does planning.
     * At this point it just adds the map and distances for the places in order.
     * It might need to reorder the places in the future.
     */
    public void plan() {
        this.map = svg();
        this.distances = legDistances();
    }

    /**
     * Returns an SVG containing the background and the legs of the trip.
     *
     * @return
     */
    private String svg() {


        String SVG_as_String = "";

        //Try to load the SVG file located in resources and throw an error if not found.
        BufferedReader reader;

        try {

            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/CObackground.svg")));

        } catch(Exception e){

            return "";

        }

        String line_of_SVGfile = "";

        try{

            while((line_of_SVGfile = reader.readLine()) != null) {

                SVG_as_String += line_of_SVGfile;

                if(line_of_SVGfile.equals("                    id=\"path181\"/>")){
                    String splice_add = spliceSVG();
                    SVG_as_String += splice_add;
                }

            }

        } catch (Exception e){
            // TODO
        }

        //Return String of the SVG File

        return SVG_as_String;

    }

    private String spliceSVG(){

        String polygon = "<polyline points=\"";

        String splice_add = "\n<svg\n" +
                "        xmlns:svg=\"http://www.w3.org/2000/svg\"\n" +
                "        xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "        version=\"1.0\"\n" +
                "        width=\"1066.6073\"\n" +
                "        height=\"783.0824\"\n" +
                "        id=\"svg2339\">";

        Place origin;
        Place destination;

        ArrayList<Double> originList_latitude = new ArrayList<>();
        ArrayList<Double> originList_longitude = new ArrayList<>();
        ArrayList<Double> destinationList_latitude = new ArrayList<>();
        ArrayList<Double> destinationList_longitude = new ArrayList<>();

        for (int i = 0; i < places.size(); i++){

            origin = places.get(i % places.size());
            destination = places.get((i+1) % places.size());
            originList_latitude.add(degreesToPixel(origin.latitude, "latitude"));
            originList_longitude.add(degreesToPixel(origin.longitude, "longitude"));
            destinationList_latitude.add(degreesToPixel(destination.latitude, "latitude"));
            destinationList_longitude.add(degreesToPixel(destination.longitude, "longitude"));

        }

        System.out.println(originList_latitude);
        System.out.println(originList_longitude);
        System.out.println(destinationList_latitude);
        System.out.println(destinationList_longitude);



        for (int i = 0; i < places.size(); i++){
            polygon += originList_longitude.get(i).toString() + "," + originList_latitude.get(i).toString() + " ";
            polygon += destinationList_longitude.get(i).toString() + "," + destinationList_latitude.get(i).toString() + " ";
        }

        polygon += "\"\nfill=\"none\" stroke-width=\"4\" stroke=\"blue\" id=\"s7\"/>";

        return splice_add + polygon + "</svg>";
    }

    private double degreesToPixel(double deg, String type){

        if(type.equals("longitude")){

            return Math.abs((1066.6073/7.5)*(-109.3-deg));
        }
        else if(type.equals("latitude")){

            return Math.abs((783.0824/4.4)*(41.2-deg));
        }
        else{
            return -1;
        }

    }

    /**
     * Returns the distances between consecutive places,
     * including the return to the starting point to make a round trip.
     *
     * @return
     */
    private ArrayList<Integer> legDistances() {

        ArrayList<Integer> dist = new ArrayList<Integer>();

        int store;

        Place origin;
        Place destination;

        for (int i = 0; i < places.size(); i++) {
            origin = places.get(i % places.size());
            destination = places.get((i + 1) % places.size());

            if(options.units.equals("user defined")) {
                Distance distance = new Distance(origin, destination, options.units, options.unitName, options.unitRadius);
                store = distance.calculatedistance();
            }
            else {
                Distance distance = new Distance(origin, destination, options.units);
                store = distance.calculatedistance();
            }
            dist.add(store);
        }

        return dist;
    }

}
